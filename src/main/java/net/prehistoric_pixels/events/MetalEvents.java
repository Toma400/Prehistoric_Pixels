package net.prehistoric_pixels.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prehistoric_pixels.PrehistoricPixels;
import net.prehistoric_pixels.core.PrehistoricPixelsBlocks;

@Mod.EventBusSubscriber(modid = PrehistoricPixels.MOD_ID)
public class MetalEvents {

    @SubscribeEvent
    public static void waxingMetal(PlayerInteractEvent.RightClickBlock event) {
        if(!event.getPlayer().level.isClientSide()) {
            ItemStack itemstack = (event.getPlayer().getMainHandItem());
            BlockPos blockpos = (event.getPos());
            BlockState blockstate = (event.getWorld().getBlockState(blockpos));
            if(itemstack.getItem() == Items.HONEYCOMB) {
                if(blockstate.getBlock() == PrehistoricPixelsBlocks.BLOCK_OF_METAL.get()){
                    itemstack.shrink(1);
                    event.getWorld().setBlock(blockpos, PrehistoricPixelsBlocks.WAXED_BLOCK_OF_METAL.get().defaultBlockState(), 1);
                    event.getWorld().addParticle(ParticleTypes.WAX_ON, true, blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), 3, 3, 3);
                }
                if(blockstate.getBlock() == PrehistoricPixelsBlocks.SLIGHTLY_RUSTED_METAL_BLOCK.get()){
                    itemstack.shrink(1);
                    event.getWorld().setBlock(blockpos, PrehistoricPixelsBlocks.WAXED_SLIGHTLY_RUSTED_METAL_BLOCK.get().defaultBlockState(), 1);
                    event.getWorld().addParticle(ParticleTypes.WAX_ON, true, blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), 3, 3, 3);
                }
                if(blockstate.getBlock() == PrehistoricPixelsBlocks.HALF_RUSTED_METAL_BLOCK.get()){
                    itemstack.shrink(1);
                    event.getWorld().setBlock(blockpos, PrehistoricPixelsBlocks.WAXED_HALF_RUSTED_METAL_BLOCK.get().defaultBlockState(), 1);
                    event.getWorld().addParticle(ParticleTypes.WAX_ON, true, blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), 3, 3, 3);
                }
            }
        }
    }

}
