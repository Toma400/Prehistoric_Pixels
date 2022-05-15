package net.prehistoric_pixels.core.datagen;

import net.minecraft.resources.ResourceLocation;
import net.prehistoric_pixels.PrehistoricPixels;

public class Helpers {

    //Reference manager for blocks
    public static final ResourceLocation BlockPathRef(Boolean is_mod, String item) {
        String id = "minecraft";
        if (is_mod) {
            id = PrehistoricPixels.MOD_ID;
        }
        ResourceLocation Path = new ResourceLocation(id + ":block/" + item);
        return Path;
    }

}
