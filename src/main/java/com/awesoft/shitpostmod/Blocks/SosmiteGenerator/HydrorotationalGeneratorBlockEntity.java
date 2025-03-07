package com.awesoft.shitpostmod.Blocks.SosmiteGenerator;

import com.awesoft.shitpostmod.Registration.SBlockRegistration;
import com.awesoft.shitpostmod.Shitpostmod;
import com.simibubi.create.content.kinetics.KineticNetwork;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.IRotate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

public class HydrorotationalGeneratorBlockEntity extends GeneratingKineticBlockEntity {

    protected BlockState blkstate = null;

    public HydrorotationalGeneratorBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
        blkstate = state;
    }



    @Override
    public float getGeneratedSpeed() {
        if (!SBlockRegistration.HYDROROTATIONAL_GENERATOR.has(getBlockState()))
            return 0;
        return convertToDirection(128, blkstate.getValue(HydrorotationalGenerator.FACING));
    }

    @Override
    public float calculateAddedStressCapacity() {
        if (!fluidTank.isEmpty() & fluidTank.getFluid().equals(new FluidStack(Fluids.WATER, fluidTank.getFluidAmount()))) {
            fluidTank.drain(50, IFluidHandler.FluidAction.EXECUTE);
            Shitpostmod.LOGGER.info("yummy fluid");
            float capacity = 16f;
            this.lastCapacityProvided = capacity;
            return capacity;
        } else {
            return 0;
        }
    }



    @Override
    protected void notifyStressCapacityChange(float capacity) {
        this.getOrCreateNetwork().updateCapacityFor(this, capacity);
        calculateAddedStressCapacity();
    }

    @Override
    public void initialize() {
        super.initialize();
        if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed())
            updateGeneratedRotation();
    }

    public void onDestroyed() {
        if (hasNetwork()) {
            KineticNetwork network = getOrCreateNetwork();
            network.calculateCapacity();
            network.calculateStress();
            this.destroy();
        }
    }

    private final FluidTank fluidTank = new FluidTank(8000) {
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            setChanged();
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
            calculateAddedStressCapacity();

        }
    };



    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER)
            return LazyOptional.of(() -> fluidTank).cast();
        return super.getCapability(capability, facing);
    }



}
