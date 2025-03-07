package com.awesoft.shitpostmod.Registration;

import com.awesoft.shitpostmod.Items.misc.CursedChain;
import com.awesoft.shitpostmod.Items.sword.SlimeSword;
import com.awesoft.shitpostmod.Shitpostmod;
import com.brandon3055.draconicevolution.init.DEContent;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import static com.awesoft.shitpostmod.Shitpostmod.REGISTRATE;

public class SItemRegistration {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Shitpostmod.MODID);


    public static final RegistryObject<SlimeSword> SLIME_SWORD  = ITEMS.register("slime_sword",  () -> new SlimeSword(Tiers.IRON,1,1,new Item.Properties()));

    public static final RegistryObject<CursedChain> CURSED_CHAIN  = ITEMS.register("cursed_chain",  () -> new CursedChain(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
