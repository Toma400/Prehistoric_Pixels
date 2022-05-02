package net.prehistoric_pixels.entity.ai.goals;

import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.prehistoric_pixels.entity.helpers.PPTamableEntity;

import javax.annotation.Nullable;
import java.util.List;

public class StegosaurusFollowParentGoal extends FollowParentGoal {
    public final PPTamableEntity PPTamableEntity;
    @Nullable
    public PPTamableEntity parent;
    public final double speedModifier;
    public int timeToRecalcPath;

    public StegosaurusFollowParentGoal(PPTamableEntity pPPTamableEntity, double pSpeedModifier) {
        super(pPPTamableEntity, pSpeedModifier);
        this.PPTamableEntity = pPPTamableEntity;
        this.speedModifier = pSpeedModifier;
    }
    
    public boolean canUse() {
        if (this.PPTamableEntity.getAge() >= 0) {
            return false;
        } else if (this.PPTamableEntity.getOwner() != null) {
            return false;
        } else {
            List<? extends PPTamableEntity> list = this.PPTamableEntity.level.getEntitiesOfClass(this.PPTamableEntity.getClass(), this.PPTamableEntity.getBoundingBox().inflate(8.0D, 4.0D, 8.0D));
            PPTamableEntity PPTamableEntity = null;
            double d0 = Double.MAX_VALUE;

            for(PPTamableEntity PPTamableEntity1 : list) {
                if (PPTamableEntity1.getAge() >= 0) {
                    double d1 = this.PPTamableEntity.distanceToSqr(PPTamableEntity1);
                    if (!(d1 > d0)) {
                        d0 = d1;
                        PPTamableEntity = PPTamableEntity1;
                    }
                }
            }

            if (PPTamableEntity == null) {
                return false;
            } else if (d0 < 9.0D) {
                return false;
            } else {
                this.parent = PPTamableEntity;
                return true;
            }
        }
    }
    
    public boolean canContinueToUse() {
        if (this.PPTamableEntity.getAge() >= 0) {
            return false;
        } else if (!this.parent.isAlive()) {
            return false;
        } else {
            double d0 = this.PPTamableEntity.distanceToSqr(this.parent);
            return !(d0 < 9.0D) && !(d0 > 256.0D);
        }
    }
    
    public void start() {
        this.timeToRecalcPath = 0;
    }
    
    public void stop() {
        this.parent = null;
    }
    
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.PPTamableEntity.getNavigation().moveTo(this.parent, this.speedModifier);
        }
    }
}
