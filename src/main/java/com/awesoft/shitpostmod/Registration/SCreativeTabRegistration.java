package com.awesoft.shitpostmod.Registration;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

import static com.awesoft.shitpostmod.Registration.SBlockEntityRegistration.REGISTRATE;

public class SCreativeTabRegistration {
    public static final RegistryEntry<CreativeModeTab> BLOCKS = REGISTRATE.defaultCreativeTab("blocks")
            .register();
    public static final RegistryEntry<CreativeModeTab> PERIPHERALS = REGISTRATE.defaultCreativeTab("peripherals")
            .register();

    public static void register() {}
}
