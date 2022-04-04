package net.prehistoric_pixels.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.core.PrehistoricPixelsBlocks;

import java.util.Collection;

public class BlocksGen extends BlockModelProvider {
    public BlocksGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PrehistoricPixels.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        iterator (PrehistoricPixelsBlocks.BLOCKS.getEntries());
    }

    public void iterator(Collection<RegistryObject<Block>> blocks) {
        for (RegistryObject<Block> block : blocks) {
            String pathage = block.get().getRegistryName().getPath();

            if(block.get() instanceof OreBlock) {
                String base = "stone";
                if(block.get().asItem().getRegistryName().toString().contains("deepslate")) {
                    base = "deepslate";
                cubeColumnHorizontal(pathage,
                        Helpers.BlockPathRef(true, pathage),
                        Helpers.BlockPathRef(false, base));
                }
            }
        }
    }
}
