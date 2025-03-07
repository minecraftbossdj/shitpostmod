package com.awesoft.shitpostmod.Registration;

import com.awesoft.shitpostmod.Items.Draconic.ModularTestChestpiece;
import com.awesoft.shitpostmod.Shitpostmod;
import com.brandon3055.draconicevolution.init.DEContent;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SDraconicRegistration {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Shitpostmod.MODID);

    public static final RegistryObject<ModularTestChestpiece> modularTestChestpiece  = ITEMS.register("test_chestpiece",  () -> new ModularTestChestpiece(DEContent.CHAOTIC_TOOLS));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
