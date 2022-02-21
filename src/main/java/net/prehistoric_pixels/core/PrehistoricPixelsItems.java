package net.prehistoric_pixels.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.prehistoric_pixels.PrehistoricPixels;

public class PrehistoricPixelsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricPixels.MOD_ID);

    public static final RegistryObject<Item> RAW_METAL = ITEMS.register("raw_metal",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));
    public static final RegistryObject<Item> METAL_INGOT = ITEMS.register("metal_ingot",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));
    public static final RegistryObject<Item> FOSSIL = ITEMS.register("fossil",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));
    public static final RegistryObject<Item> SMILODON_FOSSIL = ITEMS.register("smilodon_fossil",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));
    public static final RegistryObject<Item> STEGOSAURUS_FOSSIL = ITEMS.register("stegosaurus_fossil",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));
    public static final RegistryObject<Item> PLANT_FOSSIL = ITEMS.register("plant_fossil",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));
    public static final RegistryObject<Item> CORDAITES_FOSSIL = ITEMS.register("cordaites_fossil",
            () -> new Item(new Item.Properties().tab(PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}