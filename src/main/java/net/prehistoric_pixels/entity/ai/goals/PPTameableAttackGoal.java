package net.prehistoric_pixels.entity.ai.goals;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.prehistoric_pixels.entity.helpers.PPTamableEntity;

public class PPTameableAttackGoal extends Goal {

    private final PPTamableEntity tamable;

    public double animationDuration;
    public boolean hasHit;

    public double maxAnimationDurationTicks;
    public double attackTickTime;

    /**
     * @param entity - the entity that uses the attack goal
     * @param maxAnimationDurationTicks - the total amount of ticks the animation takes to complete
     * @param attackTickTime - the tick number when the entity hits the target
     */
    public PPTameableAttackGoal(PPTamableEntity entity, double maxAnimationDurationTicks, double attackTickTime) {
        this.tamable = entity;
        this.maxAnimationDurationTicks = maxAnimationDurationTicks;
        this.attackTickTime = attackTickTime;
    }

    @Override
    public boolean canUse() {
        return PPTameableAttackGoal.checkIfValid(this, tamable, this.tamable.getTarget());
    }

    @Override
    public boolean canContinueToUse() {
        return PPTameableAttackGoal.checkIfValid(this, tamable, this.tamable.getTarget());
    }

    @Override
    public void start() {
        this.tamable.setAttacking(true);
        this.tamable.setAggressive(true);
        this.animationDuration = 0;
    }

    @Override
    public void stop() {
        LivingEntity target = this.tamable.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.tamable.setTarget(null);
        }
        this.tamable.setAttacking(false);
        this.tamable.setAggressive(false);
        this.hasHit = false;
        this.animationDuration = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = this.tamable.getTarget();
        if (target != null) {
            ++animationDuration;
            if (this.animationDuration == this.attackTickTime && !this.hasHit) {
                this.tamable.lookAt(target, 30.0F, 30.0F);
                this.tamable.swing(InteractionHand.MAIN_HAND);
                this.tamable.doHurtTarget(target);
                this.hasHit = true;
            }
            if (this.animationDuration >= this.maxAnimationDurationTicks) {
                this.animationDuration = 0;
                this.hasHit = false;
            }
        }
    }

    public static boolean checkIfValid(PPTameableAttackGoal goal, PPTamableEntity attacker, LivingEntity target) {
        if (target == null) return false;
        if (target.isAlive() && !target.isSpectator()) {
            if (target instanceof Player && ((Player) target).isCreative()) {
                attacker.setAttacking(false);
                return false;
            }
            double distance = goal.tamable.distanceToSqr(target.getX(), target.getY(), target.getZ());
            if (distance <= PPTameableAttackGoal.getAttackReachSq(attacker, target)) return true;
        }
        attacker.setAttacking(false);
        return false;
    }

    protected static double getAttackReachSq(PPTamableEntity attacker, LivingEntity target) {
        return attacker.getBbWidth() * 2F * attacker.getBbWidth() * 2F + target.getBbWidth();
    }
}
