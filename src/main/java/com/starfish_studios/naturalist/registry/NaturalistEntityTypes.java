package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class NaturalistEntityTypes {
    public static void init(){
    }

    public static final EntityType<Snail> SNAIL = registerEntityType("snail", () -> EntityType.Builder.create(Snail::new, SpawnGroup.CREATURE).setDimensions(0.4F, 0.4F).maxTrackingRange(10).build(new Identifier(Naturalist.MOD_ID, "snail").toString()));
    public static final EntityType<Bear> BEAR = registerEntityType("bear", () -> EntityType.Builder.create(Bear::new, SpawnGroup.CREATURE).setDimensions(1.4F, 1.7F).maxTrackingRange(10).build(new Identifier(Naturalist.MOD_ID, "bear").toString()));
    public static final EntityType<Butterfly> BUTTERFLY = registerEntityType("butterfly", () -> EntityType.Builder.create(Butterfly::new, SpawnGroup.CREATURE).setDimensions(0.7F, 0.6F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "butterfly").toString()));
    public static final EntityType<Firefly> FIREFLY = registerEntityType("firefly", () -> EntityType.Builder.create(Firefly::new, NaturalistMobCategories.FIREFLIES).setDimensions(0.7F, 0.6F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "firefly").toString()));
    public static final EntityType<Snake> SNAKE = registerEntityType("snake", () -> EntityType.Builder.create(Snake::new, SpawnGroup.CREATURE).setDimensions(0.6F, 0.7F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "snake").toString()));
    public static final EntityType<Snake> CORAL_SNAKE = registerEntityType("coral_snake", () -> EntityType.Builder.create(Snake::new, SpawnGroup.CREATURE).setDimensions(0.6F, 0.7F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "coral_snake").toString()));
    public static final EntityType<Snake> RATTLESNAKE = registerEntityType("rattlesnake", () -> EntityType.Builder.create(Snake::new, SpawnGroup.CREATURE).setDimensions(0.6F, 0.7F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "rattlesnake").toString()));
    public static final EntityType<Deer> DEER = registerEntityType("deer", () -> EntityType.Builder.create(Deer::new, SpawnGroup.CREATURE).setDimensions(1.3F, 1.6F).maxTrackingRange(10).build(new Identifier(Naturalist.MOD_ID, "deer").toString()));
    public static final EntityType<Bird> BLUEJAY = registerEntityType("bluejay", () -> EntityType.Builder.create(Bird::new, SpawnGroup.CREATURE).setDimensions(0.5F, 0.9F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "bluejay").toString()));
    public static final EntityType<Bird> CANARY = registerEntityType("canary", () -> EntityType.Builder.create(Bird::new, SpawnGroup.CREATURE).setDimensions(0.5F, 0.9F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "canary").toString()));
    public static final EntityType<Bird> CARDINAL = registerEntityType("cardinal", () -> EntityType.Builder.create(Bird::new, SpawnGroup.CREATURE).setDimensions(0.5F, 0.9F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "cardinal").toString()));
    public static final EntityType<Bird> ROBIN = registerEntityType("robin", () -> EntityType.Builder.create(Bird::new, SpawnGroup.CREATURE).setDimensions(0.5F, 0.9F).maxTrackingRange(8).build(new Identifier(Naturalist.MOD_ID, "robin").toString()));
    public static final EntityType<Caterpillar> CATERPILLAR = registerEntityType("caterpillar", () -> EntityType.Builder.create(Caterpillar::new, SpawnGroup.CREATURE).setDimensions(0.4F, 0.4F).maxTrackingRange(10).build(new Identifier(Naturalist.MOD_ID, "caterpillar").toString()));

    private static <T extends EntityType<?>> T registerEntityType(String name, Supplier<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Naturalist.MOD_ID, name), entityType.get());
    }
}
