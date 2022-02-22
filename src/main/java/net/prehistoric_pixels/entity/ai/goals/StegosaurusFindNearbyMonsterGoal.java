package net.prehistoric_pixels.entity.ai.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.prehistoric_pixels.client.entity.render.StegosaurusRenderer;
import net.prehistoric_pixels.entity.StegosaurusEntity;
import net.prehistoric_pixels.entity.helpers.PPTamableEntity;

public class StegosaurusFindNearbyMonsterGoal extends TargetGoal {

    private final StegosaurusEntity stego;
    private static final TargetingConditions CONDITION = TargetingConditions.forCombat();

    public StegosaurusFindNearbyMonsterGoal(StegosaurusEntity stego) {
        super(stego, true, false);
        this.stego = stego;
    }

    @Override
    public boolean canUse() {
        return !stego.isBaby() && !stego.isTame() && stego.getTarget() == null;
    }

    @Override
    public boolean canContinueToUse() {
        return !stego.isBaby() && !stego.isTame() && stego.getTarget() == null;
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void tick() {
        LivingEntity target = this.stego.getTarget();
        if (target == null) {
            Monster targetCandidate = stego.getLevel().getNearestEntity(Monster.class, CONDITION,
                    stego, stego.getX(), stego.getY(), stego.getZ(), this.stego.getBoundingBox().inflate(12.0D, 6.0D, 12.0D));
               stego.setTarget(targetCandidate);
        }
    }
}
