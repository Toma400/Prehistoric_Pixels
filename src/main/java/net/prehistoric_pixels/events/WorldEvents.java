package net.prehistoric_pixels.events;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.generation.OreGen;

@Mod.EventBusSubscriber(modid = PrehistoricPixels.MOD_ID)
public class WorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        OreGen.generatingOres(event);
    }

}
