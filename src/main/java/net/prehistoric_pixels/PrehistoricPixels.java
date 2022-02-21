package net.prehistoric_pixels;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.prehistoric_pixels.core.PrehistoricPixelsBlocks;
import net.prehistoric_pixels.core.PrehistoricPixelsItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(PrehistoricPixels.MOD_ID)
public class PrehistoricPixels
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "prehistoric_pixels";

    public PrehistoricPixels() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PrehistoricPixelsItems.register(eventBus);
        PrehistoricPixelsBlocks.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::setupClient);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupClient(final FMLCommonSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PrehistoricPixelsBlocks.METAL_BARS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PrehistoricPixelsBlocks.METAL_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PrehistoricPixelsBlocks.METAL_DOOR.get(), RenderType.cutout());
    }

    private void setup(final FMLCommonSetupEvent event) {
    //    event.enqueueWork(() -> {
    //        AxeItem.STRIPPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPPABLES)
    //                .put(PrehistoricPixelsBlocks.RUSTED_METAL_BLOCK.get(), PrehistoricPixelsBlocks.HALF_RUSTED_METAL_BLOCK.get())
    //                .put(PrehistoricPixelsBlocks.HALF_RUSTED_METAL_BLOCK.get(), PrehistoricPixelsBlocks.SLIGHTLY_RUSTED_METAL_BLOCK.get())
    //                .put(PrehistoricPixelsBlocks.SLIGHTLY_RUSTED_METAL_BLOCK.get(), PrehistoricPixelsBlocks.BLOCK_OF_METAL.get())
    //                .put(PrehistoricPixelsBlocks.WAXED_BLOCK_OF_METAL.get(), PrehistoricPixelsBlocks.BLOCK_OF_METAL.get())
    //                .put(PrehistoricPixelsBlocks.WAXED_SLIGHTLY_RUSTED_METAL_BLOCK.get(), PrehistoricPixelsBlocks.SLIGHTLY_RUSTED_METAL_BLOCK.get())
    //                .put(PrehistoricPixelsBlocks.WAXED_HALF_RUSTED_METAL_BLOCK.get(), PrehistoricPixelsBlocks.HALF_RUSTED_METAL_BLOCK.get());
    //            }
    //    );
    }

}
