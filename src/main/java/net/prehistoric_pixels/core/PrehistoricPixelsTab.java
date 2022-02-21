package net.prehistoric_pixels.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PrehistoricPixelsTab {
    public static final CreativeModeTab PREHISTORIC_PIXELS_TAB = new CreativeModeTab("prehistoric_pixels_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PrehistoricPixelsItems.RAW_METAL.get());
        }
    };
}
