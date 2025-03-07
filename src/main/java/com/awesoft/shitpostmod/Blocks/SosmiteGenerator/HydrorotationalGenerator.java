package com.awesoft.shitpostmod.Blocks.SosmiteGenerator;

import com.awesoft.shitpostmod.Registration.SBlockEntityRegistration;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import static dan200.computercraft.shared.util.BlockEntityHelpers.createTickerHelper;

public class HydrorotationalGenerator extends DirectionalKineticBlock implements IBE<HydrorotationalGeneratorBlockEntity> {

    public HydrorotationalGenerator(Properties properties) {
        super(properties);
    }


    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HydrorotationalGeneratorBlockEntity BE) {
                BE.onDestroyed();
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferred = getPreferredFacing(context);
        if ((context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown()) || preferred == null)
            return super.getStateForPlacement(context);
        return defaultBlockState().setValue(FACING, preferred);
    }

    @Override
    public Class<HydrorotationalGeneratorBlockEntity> getBlockEntityClass() {
        return HydrorotationalGeneratorBlockEntity.class;
    }





    @Override
    public BlockEntityType<? extends HydrorotationalGeneratorBlockEntity> getBlockEntityType() {
        return SBlockEntityRegistration.HYDROROTATIONAL_GENERATOR_BLOCKENTITY.get();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(FACING);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState blockState) {
        return blockState.getValue(FACING).getAxis();
    }

    @Override
    public boolean hideStressImpact() {
        return true;
    }
}
