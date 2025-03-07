package com.awesoft.shitpostmod.Registration;

import com.awesoft.shitpostmod.Shitpostmod;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.awesoft.shitpostmod.Shitpostmod.REGISTRATE;

public class SSoundRegistration {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Shitpostmod.MODID);

    public static final RegistryObject<SoundEvent> SOMEONE_CALLING = SOUND_EVENTS.register("someone_calling_sound",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Shitpostmod.MODID, "someone_calling_sound")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
