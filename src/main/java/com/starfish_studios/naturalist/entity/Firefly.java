package com.starfish_studios.naturalist.entity;

import com.starfish_studios.naturalist.entity.ai.goal.FlyingWanderGoal;
import com.starfish_studios.naturalist.registry.NaturalistSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class Firefly extends AnimalEntity implements Flutterer, IAnimatable {
    private static final TrackedData<Integer> GLOW_TICKS_REMAINING = DataTracker.registerData(Firefly.class, TrackedDataHandlerRegistry.INTEGER);
    private final AnimationFactory factory = new AnimationFactory(this);

    public Firefly(EntityType<? extends AnimalEntity> entityType, World level) {
        super(entityType, level);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
        this.setPathfindingPenalty(PathNodeType.COCOA, -1.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
    }

    @Override
    protected EntityNavigation createNavigation(World pLevel) {
        BirdNavigation navigation = new BirdNavigation(this, pLevel) {
            public boolean isValidPosition(BlockPos pPos) {
                return !this.world.getBlockState(pPos.down()).isAir();
            }
        };
        navigation.setCanPathThroughDoors(false);
        navigation.setCanSwim(false);
        navigation.setCanEnterOpenDoors(true);
        return navigation;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pPose, EntityDimensions pSize) {
        return pSize.height * 0.5F;
    }

    @Override
    public boolean handleFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void fall(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FlyingWanderGoal(this));
        this.goalSelector.add(2, new SwimGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6F).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F);
    }

    public static boolean checkFireflySpawnRules(EntityType<? extends Firefly> pType, ServerWorldAccess pLevel, SpawnReason pReason, BlockPos pPos, Random pRandom) {
        return HostileEntity.isSpawnDark(pLevel, pPos, pRandom) && pLevel.getBlockState(pPos.down()).isIn(BlockTags.PARROTS_SPAWNABLE_ON);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld p_146743_, PassiveEntity p_146744_) {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack pStack) {
        return false;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(GLOW_TICKS_REMAINING, 0);
    }

    public boolean isTailGlowing() {
        return this.dataTracker.get(GLOW_TICKS_REMAINING) > 0;
    }

    public int getGlowTicksRemaining() {
        return this.dataTracker.get(GLOW_TICKS_REMAINING);
    }

    private void setGlowTicks(int ticks) {
        this.dataTracker.set(GLOW_TICKS_REMAINING, ticks);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        int ticks = this.getGlowTicksRemaining();
        if (ticks > 0) {
            this.setGlowTicks(ticks - 1);
        }
        if (this.canGlow()) {
            if (this.random.nextFloat() <= 0.01 && !this.isTailGlowing()) {
                this.setGlowTicks(40 + this.random.nextInt(20));
            }
        } else if (this.isAffectedByDaylight()) {
            this.setOnFireFor(8);
        }
    }

    private boolean canGlow() {
        if (!this.world.isClient) {
            return this.world.isNight() || this.world.getLightLevel(this.getBlockPos()) < 8;
        }
        return false;
    }

    @Override
    protected boolean isAffectedByDaylight() {
        if (this.world.isDay() && !this.hasCustomName() && !this.world.isClient) {
            float brightness = this.getBrightnessAtEyes();
            BlockPos pos = new BlockPos(this.getX(), this.getEyeY(), this.getZ());
            return brightness > 0.5F && this.random.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F && this.world.isSkyVisible(pos);
        }

        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean hasWings() {
        return this.isInAir() && this.age % MathHelper.ceil(1.4959966F) == 0;
    }

    @Override
    public boolean isInAir() {
        return true;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return NaturalistSoundEvents.FIREFLY_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return NaturalistSoundEvents.FIREFLY_DEATH;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isInAir()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("firefly.fly", true));
            return PlayState.CONTINUE;
        }
        event.getController().markNeedsReload();
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(5);
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

}
