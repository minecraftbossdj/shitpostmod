package com.awesoft.shitpostmod.Peripherals.test;

import com.awesoft.shitpostmod.Registration.SBlockEntityRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class TestBlock extends Block implements EntityBlock {


    public TestBlock(Properties properties) {
        super(properties);
    }



    /**
     * This is the method from {@link EntityBlock} to create a new block entity for our block
     *
     * @return A new block entity from our registry object
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return SBlockEntityRegistration.TEST_BLOCKENTITY.get().create(pos, state);
    }
}