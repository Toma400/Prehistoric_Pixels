package net.prehistoric_pixels.core;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.prehistoric_pixels.PrehistoricPixels;
import java.util.function.Supplier;

public class PrehistoricPixelsBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricPixels.MOD_ID);

    //METAL
    public static final RegistryObject<Block> BLOCK_OF_RAW_METAL = registerBlock("block_of_raw_metal",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f, 6f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> BLOCK_OF_METAL = registerBlock("block_of_metal",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops().randomTicks()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> SLIGHTLY_RUSTED_METAL_BLOCK = registerBlock("slightly_rusted_metal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops().randomTicks()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> HALF_RUSTED_METAL_BLOCK = registerBlock("half_rusted_metal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops().randomTicks()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> RUSTED_METAL_BLOCK = registerBlock("rusted_metal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> WAXED_BLOCK_OF_METAL = registerBlock("waxed_block_of_metal",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops().randomTicks()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> WAXED_SLIGHTLY_RUSTED_METAL_BLOCK = registerBlock("waxed_slightly_rusted_metal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops().randomTicks()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> WAXED_HALF_RUSTED_METAL_BLOCK = registerBlock("waxed_half_rusted_metal_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.COPPER).strength(3f, 6f).requiresCorrectToolForDrops().randomTicks()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_METAL_ORE = registerBlock("deepslate_metal_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f, 3f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> METAL_ORE = registerBlock("metal_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f, 3f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);

    //METAL VARIATIONS
    public static final RegistryObject<Block> METAL_BARS = registerBlock("metal_bars",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f, 6f).requiresCorrectToolForDrops().noOcclusion()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> METAL_BUTTON = registerBlock("metal_button",
            () -> new StoneButtonBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1f, 1f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> METAL_PRESSURE_PLATE = registerBlock("metal_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.METAL).strength(1f, 1f).requiresCorrectToolForDrops().noOcclusion()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> METAL_DOOR = registerBlock("metal_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f, 5f).requiresCorrectToolForDrops().noOcclusion()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> METAL_TRAPDOOR = registerBlock("metal_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f, 5f).requiresCorrectToolForDrops().noOcclusion()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> METAL_LAMP = registerBlock("metal_lamp",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3f, 6f).requiresCorrectToolForDrops().lightLevel(s -> 15)), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);

    //FOSSILS
    public static final RegistryObject<Block> DEEPSLATE_EXPOSED_FOSSIL = registerBlock("deepslate_exposed_fossil",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f, 3f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> EXPOSED_FOSSIL = registerBlock("exposed_fossil",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f, 3f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);
    public static final RegistryObject<Block> PILE_OF_FOSSILS = registerBlock("pile_of_fossils",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.BASALT).strength(2f, 3f).requiresCorrectToolForDrops()), PrehistoricPixelsTab.PREHISTORIC_PIXELS_TAB);


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return PrehistoricPixelsItems.ITEMS.register (name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
