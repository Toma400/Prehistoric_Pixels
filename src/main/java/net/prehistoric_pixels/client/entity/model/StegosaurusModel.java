package net.prehistoric_pixels.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.entity.StegosaurusEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StegosaurusModel extends AnimatedGeoModel<StegosaurusEntity> {

    @Override
    public ResourceLocation getModelLocation(StegosaurusEntity entity) {
        if (entity.isFullAdult()) {
            return new ResourceLocation(PrehistoricPixels.MOD_ID, "geo/stegosaurus.geo.json");
        } else if (!entity.isBaby() && !entity.isFullAdult()) {
            return new ResourceLocation(PrehistoricPixels.MOD_ID, "geo/juvenile_stegosaurus.geo.json");
        } else {
            return new ResourceLocation(PrehistoricPixels.MOD_ID, "geo/baby_stegosaurus.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(StegosaurusEntity entity) {
        if (entity.isFullAdult()) {
            return new ResourceLocation(PrehistoricPixels.MOD_ID, "textures/entity/stegosaurus.png");
        } else if (!entity.isBaby() && !entity.isFullAdult()) {
        return new ResourceLocation(PrehistoricPixels.MOD_ID, "textures/entity/juvenile_stegosaurus.png");
        } else {
            return new ResourceLocation(PrehistoricPixels.MOD_ID, "textures/entity/baby_stegosaurus.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StegosaurusEntity entity) {
        return new ResourceLocation(PrehistoricPixels.MOD_ID, "animations/stegosaurus.animation.json");
    }
}
