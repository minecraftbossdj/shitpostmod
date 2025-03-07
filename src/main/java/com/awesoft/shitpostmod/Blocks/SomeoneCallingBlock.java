package com.awesoft.shitpostmod.Blocks;

import com.awesoft.shitpostmod.Registration.SSoundRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SomeoneCallingBlock extends Block {

    public SomeoneCallingBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            MinecraftServer server = world.getServer();
            server.getCommands().performPrefixedCommand(server.createCommandSourceStack(),"playsound shitpostmod:someone_calling_sound master @a "+pos.getX()+" "+pos.getY()+" "+pos.getZ());
        }
        return InteractionResult.SUCCESS; // SUCCESS means the interaction happened
    }

}