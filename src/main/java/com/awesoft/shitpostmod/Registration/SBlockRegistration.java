package com.awesoft.shitpostmod.Registration;

import com.awesoft.shitpostmod.Blocks.SomeoneCallingBlock;
import com.awesoft.shitpostmod.Blocks.SosmiteGenerator.HydrorotationalGenerator;
import com.awesoft.shitpostmod.Peripherals.test.TestBlock;
import com.google.common.collect.Sets;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.level.block.Block;

import static com.awesoft.shitpostmod.Shitpostmod.REGISTRATE;

public class SBlockRegistration {

    public static final BlockEntry<Block> BONER_BLOCK = REGISTRATE
            .block("boner_block",Block::new)
            .item()
                .build()
            .register();

    public static final BlockEntry<SomeoneCallingBlock> SOMEONE_CALLING = REGISTRATE
            .block("someone_calling", SomeoneCallingBlock::new)
            .item()
                .build()
            .register();

    public static final BlockEntry<TestBlock> TEST_BLOCK = REGISTRATE
            .block("test_block",TestBlock::new)
            .item()
                .build()
            .register();

    public static final BlockEntry<HydrorotationalGenerator> HYDROROTATIONAL_GENERATOR = REGISTRATE
            .block("hydrorotational_generator", HydrorotationalGenerator::new)
            .item()
                .build()
            .register();



    public static void register() {}
}
