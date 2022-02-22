package net.prehistoric_pixels.entity.ai.goals;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.prehistoric_pixels.entity.helpers.PPTamableEntity;

import java.util.EnumSet;
import java.util.Random;

public class PPMoveToTargetGoal extends Goal {

    protected static final Random RANDOM = new Random();
    public PPTamableEntity entity;
    protected Path path;

    private final double speedMultiplier;
    private final int checkRate;

    public PPMoveToTargetGoal(PPTamableEntity entity, double speedMultiplier, int checkRate) {
        this.entity = entity;
        this.speedMultiplier = speedMultiplier;
        this.checkRate = checkRate;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (RANDOM.nextInt(this.checkRate) == 0) return false;

        return this.isExecutable(this, this.entity, this.entity.getTarget());
    }

    @Override
    public boolean canContinueToUse() {
        if (RANDOM.nextInt(this.checkRate) == 0) return true;

        return this.isExecutable(this, this.entity, this.entity.getTarget());
    }

    @Override
    public void start() {
        this.entity.setAggressive(true);
        this.entity.setMoving(true);
        this.entity.getNavigation().moveTo(this.path, this.speedMultiplier);
    }

    @Override
    public void stop() {
        LivingEntity target = this.entity.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.entity.setTarget(null);
        }
        this.entity.setAggressive(false);
        this.entity.setMoving(false);
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = this.entity.getTarget();
        if (target == null) return;

        this.entity.getLookControl().setLookAt(target, 30F, 30F);
        this.entity.getNavigation().moveTo(target, this.speedMultiplier);
    }

    protected boolean isExecutable(PPMoveToTargetGoal goal, PPTamableEntity attacker, LivingEntity target) {
        if (target == null) return false;
        if (target.isAlive() && !target.isSpectator()) {
            if (target instanceof Player && ((Player) target).isCreative()) return false;

            double distance = goal.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
            goal.path = attacker.getNavigation().createPath(target, 0);

            return attacker.getSensing().hasLineOfSight(target) && distance >= PPTameableAttackGoal.getAttackReachSq(attacker, target);
        }
        return false;
    }
}
