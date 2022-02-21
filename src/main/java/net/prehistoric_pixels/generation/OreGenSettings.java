package net.prehistoric_pixels.generation;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.prehistoric_pixels.core.PrehistoricPixelsBlocks;

import java.util.List;


public class OreGenSettings {

    public static final List<OreConfiguration.TargetBlockState> METAL_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PrehistoricPixelsBlocks.METAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PrehistoricPixelsBlocks.DEEPSLATE_METAL_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> EXPOSED_FOSSILS = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PrehistoricPixelsBlocks.EXPOSED_FOSSIL.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PrehistoricPixelsBlocks.DEEPSLATE_EXPOSED_FOSSIL.get().defaultBlockState()));

    public static final ConfiguredFeature<?, ?> METAL_ORE = FeatureUtils.register("metal_ore",
            Feature.ORE.configured(new OreConfiguration(METAL_ORES, 9)));
    public static final ConfiguredFeature<?, ?> EXPOSED_FOSSIL = FeatureUtils.register("exposed_fossil",
            Feature.ORE.configured(new OreConfiguration(EXPOSED_FOSSILS, 5)));

    //public static void generateOres(final BiomeLoadingEvent event) {
    //    spawnOreInAllBiomes(OreType.METAL_ORE, event, LevelStem.OVERWORLD.toString());
    //    spawnOreInAllBiomes(OreType.DEEPSLATE_METAL_ORE, event, LevelStem.OVERWORLD.toString());
    //    spawnOreInAllBiomes(OreType.EXPOSED_FOSSIL, event, LevelStem.OVERWORLD.toString());
    //    spawnOreInAllBiomes(OreType.DEEPSLATE_EXPOSED_FOSSIL, event, LevelStem.OVERWORLD.toString());
    //}

}
