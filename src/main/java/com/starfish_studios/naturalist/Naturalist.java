package com.starfish_studios.naturalist;

import com.starfish_studios.naturalist.entity.*;
import com.starfish_studios.naturalist.registry.*;
import com.starfish_studios.naturalist.util.CustomItemGroupBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;

public class Naturalist implements ModInitializer {
    public static final String MOD_ID = "naturalist";
    public static final ItemGroup TAB = CustomItemGroupBuilder.create(MOD_ID).icon(() -> new ItemStack(Items.FERN)).build();

    @Override
    public void onInitialize() {
        AutoConfig.register(NaturalistConfig.class, GsonConfigSerializer::new);

        NaturalistBlocks.init();
        NaturalistItems.init();
        NaturalistSoundEvents.init();
        NaturalistEntityTypes.init();
        NaturalistPotions.init();

        GeckoLib.initialize();

        addSpawns();
        registerEntityAttributes();
        registerBrewingRecipes();
        registerSpawnRestrictions();
    }

    public void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.SNAIL, Snail.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.BEAR, Bear.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.BUTTERFLY, Butterfly.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.FIREFLY, Firefly.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.SNAKE, Snake.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.CORAL_SNAKE, Snake.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.RATTLESNAKE, Snake.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.DEER, Deer.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.BLUEJAY, Bird.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.CANARY, Bird.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.CARDINAL, Bird.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.ROBIN, Bird.createAttributes());
        FabricDefaultAttributeRegistry.register(NaturalistEntityTypes.CATERPILLAR, Caterpillar.createAttributes());
    }

    public void registerBrewingRecipes() {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, NaturalistItems.ANTLER, NaturalistPotions.FOREST_DASHER);
        BrewingRecipeRegistry.registerPotionRecipe(NaturalistPotions.FOREST_DASHER, Items.REDSTONE, NaturalistPotions.LONG_FOREST_DASHER);
        BrewingRecipeRegistry.registerPotionRecipe(NaturalistPotions.FOREST_DASHER, Items.GLOWSTONE_DUST, NaturalistPotions.STRONG_FOREST_DASHER);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, NaturalistItems.GLOW_GOOP, NaturalistPotions.GLOWING);
        BrewingRecipeRegistry.registerPotionRecipe(NaturalistPotions.GLOWING, Items.REDSTONE, NaturalistPotions.LONG_GLOWING);
    }

    public void registerSpawnRestrictions() {
        SpawnRestriction.register(NaturalistEntityTypes.SNAIL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
        SpawnRestriction.register(NaturalistEntityTypes.BEAR, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(NaturalistEntityTypes.BUTTERFLY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(NaturalistEntityTypes.FIREFLY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Firefly::checkFireflySpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.SNAKE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Snake::checkSnakeSpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.CORAL_SNAKE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Snake::checkSnakeSpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.RATTLESNAKE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Snake::checkSnakeSpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.DEER, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(NaturalistEntityTypes.BLUEJAY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Bird::checkBirdSpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.CANARY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Bird::checkBirdSpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.CARDINAL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Bird::checkBirdSpawnRules);
        SpawnRestriction.register(NaturalistEntityTypes.ROBIN, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, Bird::checkBirdSpawnRules);
    }

    public void addSpawns() {
        NaturalistConfig config = AutoConfig.getConfigHolder(NaturalistConfig.class).getConfig();
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.TAIGA), SpawnGroup.CREATURE, NaturalistEntityTypes.BEAR, config.bearSpawnWeight, 1, 2);
        addMobSpawn(List.of(Biome.Category.FOREST), SpawnGroup.CREATURE, NaturalistEntityTypes.DEER, config.deerSpawnWeight, 1, 3);
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.RIVER, Biome.Category.EXTREME_HILLS, Biome.Category.MUSHROOM, Biome.Category.UNDERGROUND, Biome.Category.MOUNTAIN), SpawnGroup.CREATURE, NaturalistEntityTypes.SNAIL, config.snailSpawnWeight, 1, 3);
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.PLAINS, Biome.Category.SWAMP, Biome.Category.MUSHROOM), NaturalistMobCategories.FIREFLIES, NaturalistEntityTypes.FIREFLY, config.fireflySpawnWeight, 2, 3);
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.PLAINS), SpawnGroup.CREATURE, NaturalistEntityTypes.BUTTERFLY, config.butterflySpawnWeight, 1, 3);
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.PLAINS, Biome.Category.SWAMP), SpawnGroup.CREATURE, NaturalistEntityTypes.SNAKE, config.snakeSpawnWeight, 1, 1);
        addMobSpawn(List.of(Biome.Category.MESA, Biome.Category.SAVANNA, Biome.Category.DESERT), SpawnGroup.CREATURE, NaturalistEntityTypes.RATTLESNAKE, config.rattlesnakeSpawnWeight, 1, 1);
        addMobSpawn(List.of(Biome.Category.JUNGLE, Biome.Category.BEACH, Biome.Category.RIVER), SpawnGroup.CREATURE, NaturalistEntityTypes.CORAL_SNAKE, config.coralSnakeSpawnWeight, 1, 1);
        addMobSpawn(List.of(Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS, Biome.Category.ICY), SpawnGroup.CREATURE, NaturalistEntityTypes.BLUEJAY, config.birdSpawnWeight, 1, 4);
        addMobSpawn(List.of(Biome.Category.EXTREME_HILLS, Biome.Category.MOUNTAIN), SpawnGroup.CREATURE, NaturalistEntityTypes.CANARY, config.birdSpawnWeight, 1, 4);
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.SWAMP, Biome.Category.SAVANNA, Biome.Category.DESERT), SpawnGroup.CREATURE, NaturalistEntityTypes.CARDINAL, config.birdSpawnWeight, 1, 4);
        addMobSpawn(List.of(Biome.Category.FOREST, Biome.Category.PLAINS, Biome.Category.MOUNTAIN), SpawnGroup.CREATURE, NaturalistEntityTypes.ROBIN, config.birdSpawnWeight, 1, 4);
        addMobSpawn(List.of(Biome.Category.FOREST), SpawnGroup.CREATURE, EntityType.RABBIT,  config.forestRabbitSpawnWeight, 2, 3);
        addMobSpawn(List.of(Biome.Category.FOREST), SpawnGroup.CREATURE, EntityType.FOX, config.forestFoxSpawnWeight, 2, 4);
    }

    private void addMobSpawn(List<Biome.Category> categories, SpawnGroup spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        BiomeModifications.addSpawn((biomeSelector) -> categories.contains(Biome.getCategory(biomeSelector.getBiomeRegistryEntry())), spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
    }

}
