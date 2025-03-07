package com.awesoft.shitpostmod.Registration;

import com.awesoft.shitpostmod.Blocks.SosmiteGenerator.HydrorotationalGeneratorBlockEntity;
import com.awesoft.shitpostmod.Peripherals.test.Test_TileEntity;
import com.awesoft.shitpostmod.Shitpostmod;
import com.google.common.collect.Sets;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.level.block.entity.BlockEntity;

import static com.awesoft.shitpostmod.Shitpostmod.REGISTRATE;

public class SBlockEntityRegistration {

    public static final Registrate REGISTRATE = Registrate.create(Shitpostmod.MODID);

    public static final BlockEntityEntry<Test_TileEntity> TEST_BLOCKENTITY = REGISTRATE
            .blockEntity("test_block", Test_TileEntity::new)
            .register();

    public static final BlockEntityEntry<HydrorotationalGeneratorBlockEntity> HYDROROTATIONAL_GENERATOR_BLOCKENTITY = REGISTRATE
            .blockEntity("hydrorotational_generator", HydrorotationalGeneratorBlockEntity::new)
            .register();

    public static void register() {}
}
