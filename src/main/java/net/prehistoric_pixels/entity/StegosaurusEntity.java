package net.prehistoric_pixels.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.prehistoric_pixels.core.PrehistoricPixelsEntityTypes;
import net.prehistoric_pixels.entity.ai.goals.PPMoveToTargetGoal;
import net.prehistoric_pixels.entity.ai.goals.PPTameableAttackGoal;
import net.prehistoric_pixels.entity.ai.goals.StegosaurusFindNearbyMonsterGoal;
import net.prehistoric_pixels.entity.helpers.PPTamableEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;

public class StegosaurusEntity extends PPTamableEntity implements IAnimatable {

    protected static final EntityDataAccessor<Boolean> IS_FULL_ADULT = SynchedEntityData.defineId(StegosaurusEntity.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory factory = new AnimationFactory(this);
    public int timeBeforeFullAdultGrow = 0;

    public StegosaurusEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.noCulling = true;
    }

    // Attributes
    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createLivingAttributes()
                .add(Attributes.ARMOR, 4)
                .add(Attributes.ATTACK_SPEED, 69420)
                .add(Attributes.ATTACK_KNOCKBACK, 4D)
                .add(Attributes.ATTACK_DAMAGE, 8)
                .add(Attributes.MAX_HEALTH, 55)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 4D);
    }

    // AI Goals
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PPTameableAttackGoal(this, 19, 7));
        this.goalSelector.addGoal(1, new PPMoveToTargetGoal(this, 1.0F, 8));

        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 30.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FloatGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.2, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.2));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override
            public boolean canUse() {
                if (this.mob instanceof StegosaurusEntity) {
                    if (this.mob.isBaby()) {
                        return false;
                    }
                    if (((StegosaurusEntity) this.mob).isTame()) {
                        return false;
                    }
                }
                return super.canUse();
            }
        });
        this.targetSelector.addGoal(2, new StegosaurusFindNearbyMonsterGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    // Other Mob Stuff
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {

        if (p_146746_.getRandom().nextBoolean()) {
            this.turnIntoAdult();
        }

        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    /**
     * Use this method to turn the Stegosaurus into a full adult. Don't use setFullAdult, since it doesn't update the Stegosaurus' attributes.
     */
    public void turnIntoAdult() {
        this.setFullAdult(true);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(70.0D);
        this.setHealth(70.0F);

        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
    }

    /**
     * 24000 ticks = 20 minutes
     */
    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.isBaby() && !this.isFullAdult()) {
            ++this.timeBeforeFullAdultGrow;
            if (this.timeBeforeFullAdultGrow >= 24000) {
                this.timeBeforeFullAdultGrow = 0;
                this.turnIntoAdult();
            }
        }
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            Entity entity = pSource.getEntity();
            this.setOrderedToSit(false);
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                pAmount = (pAmount + 1.0F) / 2.0F;
            }

            return super.hurt(pSource, pAmount);
        }
    }

    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
        }

        return flag;
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!pPlayer.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    if (!this.isBaby() && !this.isFullAdult()) {
                        timeBeforeFullAdultGrow = timeBeforeFullAdultGrow + 20;
                    }

                    this.heal((float)item.getFoodProperties().getNutrition());
                    this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
                    return InteractionResult.SUCCESS;
                }

            } else if (this.isFood(itemstack) && !this.isBaby()) {
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    this.tame(pPlayer);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }

                return InteractionResult.SUCCESS;
            }

            return super.mobInteract(pPlayer, pHand);
        }
    }

    public boolean isFood(ItemStack pStack) {
        Item item = pStack.getItem();
        return ItemTags.getAllTags().getTagOrEmpty(ItemTags.LEAVES.getName()).contains(item);
    }

    public StegosaurusEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        StegosaurusEntity wolf = PrehistoricPixelsEntityTypes.STEGOSAURUS.get().create(p_149088_);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            wolf.setOwnerUUID(uuid);
            wolf.setTame(true);
        }

        return wolf;
    }

    public boolean canMate(Animal pOtherAnimal) {
        if (pOtherAnimal == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(pOtherAnimal instanceof StegosaurusEntity wolf)) {
            return false;
        } else {
            if (!wolf.isTame()) {
                return false;
            } else if (wolf.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && wolf.isInLove();
            }
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 4 + this.level.getRandom().nextInt(2);
    }

    // Sound Events
    public SoundEvent getAmbientSound() {
        return SoundEvents.RAVAGER_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.RAVAGER_STEP, 0.15f, 1);
    }

    // Data Managers
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_FULL_ADULT, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundNBT) {
        super.addAdditionalSaveData(compoundNBT);
        compoundNBT.putBoolean("isFullAdult", this.isFullAdult());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundNBT) {
        super.readAdditionalSaveData(compoundNBT);
        this.setFullAdult(compoundNBT.getBoolean("isFullAdult"));
    }

    public boolean isFullAdult() {
        return this.entityData.get(IS_FULL_ADULT);
    }

    public void setFullAdult(boolean fullAdult) {
        this.entityData.set(IS_FULL_ADULT, fullAdult);
    }


    // Geckolib Section
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.attack", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "stegosaurus_controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
