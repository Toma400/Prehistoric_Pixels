package net.prehistoric_pixels.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.entity.StegosaurusEntity;

public class PrehistoricPixelsEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, PrehistoricPixels.MOD_ID);

    public static final RegistryObject<EntityType<StegosaurusEntity>> STEGOSAURUS = ENTITY_TYPES.register("stegosaurus", () -> EntityType.Builder.of(StegosaurusEntity::new, MobCategory.AMBIENT).sized(2.5f, 2.2f).build(new ResourceLocation(PrehistoricPixels.MOD_ID, "stegosaurus").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
