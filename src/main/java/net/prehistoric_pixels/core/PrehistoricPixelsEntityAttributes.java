package net.prehistoric_pixels.core;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.entity.StegosaurusEntity;

@Mod.EventBusSubscriber(modid = PrehistoricPixels.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PrehistoricPixelsEntityAttributes {

    @SubscribeEvent
    public static void registerRenderers(final EntityAttributeCreationEvent event) {

        event.put(PrehistoricPixelsEntityTypes.STEGOSAURUS.get(), StegosaurusEntity.setCustomAttributes().build());
    }
}
