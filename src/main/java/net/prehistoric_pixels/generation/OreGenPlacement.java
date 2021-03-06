package net.prehistoric_pixels.generation;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class OreGenPlacement {

    public static final Holder<PlacedFeature> METAL_ORE_PLACED = PlacementUtils.register("metal_ore_placed",
            OreGenSettings.METAL_ORE, CustomOrePlacements.commonOrePlacement(18, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-40), VerticalAnchor.aboveBottom(90))));

    public static final Holder<PlacedFeature> EXPOSED_FOSSIL_PLACED = PlacementUtils.register("exposed_fossil_placed",
            OreGenSettings.EXPOSED_FOSSIL, CustomOrePlacements.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-10), VerticalAnchor.aboveBottom(120))));
}
