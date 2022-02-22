package net.prehistoric_pixels.core;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.RegistryObject;

public class PrehistoricPixelsTab {
    public static final CreativeModeTab PREHISTORIC_PIXELS_TAB = new CreativeModeTab("prehistoric_pixels_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PrehistoricPixelsItems.RAW_METAL.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);

            /*
            for (RegistryObject<Enchantment> enchants : PrehistoricPixelsEnchantments.ENCHANTMENTS.getEntries()) {
                Enchantment enchantment = enchants.get();

                if (enchantment.isAllowedOnBooks()) {
                    items.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantment.getMaxLevel())));
                }
            }
            */
        }
    };
}
