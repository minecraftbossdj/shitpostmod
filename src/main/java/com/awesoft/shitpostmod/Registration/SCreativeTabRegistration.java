package com.awesoft.shitpostmod.Registration;

import com.awesoft.shitpostmod.Shitpostmod;
import com.brandon3055.draconicevolution.init.DEContent;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dan200.computercraft.shared.ModRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.awesoft.shitpostmod.Registration.SBlockEntityRegistration.REGISTRATE;

public class SCreativeTabRegistration {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Shitpostmod.MODID);

    //blocks
    public static final RegistryObject<CreativeModeTab> BLOCKS = CREATIVE_MODE_TABS.register("blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SBlockRegistration.BONER_BLOCK))
                    .title(Component.translatable("itemGroup.shitpostmod.blocks"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(SBlockRegistration.BONER_BLOCK.asItem());
                        output.accept(SBlockRegistration.SOMEONE_CALLING);
                    }))
                    .build()
    );

    //weapons
    public static final RegistryObject<CreativeModeTab> WEAPONS = CREATIVE_MODE_TABS.register("weapons_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SItemRegistration.SLIME_SWORD.get()))
                    .title(Component.translatable("itemGroup.shitpostmod.weapons"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(SItemRegistration.SLIME_SWORD.get());
                        output.accept(SItemRegistration.CURSED_CHAIN.get());
                    }))
                    .build()
    );

    //
    public static final RegistryObject<CreativeModeTab> MISC_ITEMS = CREATIVE_MODE_TABS.register("misc_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SBlockRegistration.BONER_BLOCK))
                    .title(Component.translatable("itemGroup.shitpostmod.misc_items"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(Items.STICK);
                    }))
                    .build()
    );

    //peripherals
    public static final RegistryObject<CreativeModeTab> PERIPHERALS = CREATIVE_MODE_TABS.register("peripherals_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SBlockRegistration.TEST_BLOCK))
                    .title(Component.translatable("itemGroup.shitpostmod.peripherals"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(SBlockRegistration.TEST_BLOCK.asItem());
                    }))
                    .build()
    );

    //create
    public static final RegistryObject<CreativeModeTab> CREATE = CREATIVE_MODE_TABS.register("create_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AllItems.WRENCH))
                    .title(Component.translatable("itemGroup.shitpostmod.create"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(SBlockRegistration.HYDROROTATIONAL_GENERATOR);
                    }))
                    .build()
    );

    //draconic
    public static final RegistryObject<CreativeModeTab> DRACONIC = CREATIVE_MODE_TABS.register("draconic_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(DEContent.CHESTPIECE_DRACONIC.get()))
                    .title(Component.translatable("itemGroup.shitpostmod.draconic"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(SDraconicRegistration.modularTestChestpiece.get());
                    }))
                    .build()
    );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
