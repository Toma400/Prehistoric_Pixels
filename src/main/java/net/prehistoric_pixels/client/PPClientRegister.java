package net.prehistoric_pixels.client;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.client.entity.render.StegosaurusRenderer;
import net.prehistoric_pixels.core.PrehistoricPixelsEntityTypes;

@Mod.EventBusSubscriber(modid = PrehistoricPixels.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PPClientRegister {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PrehistoricPixelsEntityTypes.STEGOSAURUS.get(), StegosaurusRenderer::new);
    }
}
