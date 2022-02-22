package net.prehistoric_pixels.item;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PPSpawnEggItem extends SpawnEggItem {

    protected static final List<PPSpawnEggItem> UNADDED_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;

    public PPSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColour, int secondaryColour, Properties properties) {
        super(null, primaryColour, secondaryColour, properties);
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier);
        UNADDED_EGGS.add(this);
    }

    public static void initUnaddedEggs() {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
        DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior() {

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> entitytype = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction),
                        MobSpawnType.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };
        for (final SpawnEggItem egg : UNADDED_EGGS) {
            EGGS.put(egg.getType(null), egg);
            DispenserBlock.registerBehavior(egg, defaultDispenseItemBehavior);
        }
        UNADDED_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable final CompoundTag p_208076_1_) {
        return entityTypeSupplier.get();
    }
}
