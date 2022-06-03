package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.item.NoFluidMobBucketItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class NaturalistItems {
    public static void init(){
    }

    public static final Item SNAIL_SPAWN_EGG = registerItem("snail_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.SNAIL, 5457209, 8811878, new Item.Settings().group(Naturalist.TAB)));
    public static final Item BEAR_SPAWN_EGG = registerItem("bear_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.BEAR, 3745047, 7228984, new Item.Settings().group(Naturalist.TAB)));
    public static final Item BUTTERFLY_SPAWN_EGG = registerItem("butterfly_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.BUTTERFLY, 16742912, 4727321, new Item.Settings().group(Naturalist.TAB)));
    public static final Item FIREFLY_SPAWN_EGG = registerItem("firefly_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.FIREFLY, 10567424, 16764416, new Item.Settings().group(Naturalist.TAB)));
    public static final Item SNAKE_SPAWN_EGG = registerItem("snake_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.SNAKE, 9076003, 12954734, new Item.Settings().group(Naturalist.TAB)));
    public static final Item CORAL_SNAKE_SPAWN_EGG = registerItem("coral_snake_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.CORAL_SNAKE, 3485226, 12261376, new Item.Settings().group(Naturalist.TAB)));
    public static final Item RATTLESNAKE_SPAWN_EGG = registerItem("rattlesnake_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.RATTLESNAKE, 16039772, 7293214, new Item.Settings().group(Naturalist.TAB)));
    public static final Item DEER_SPAWN_EGG = registerItem("deer_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.DEER, 10318165, 14531208, new Item.Settings().group(Naturalist.TAB)));
    public static final Item BLUEJAY_SPAWN_EGG = registerItem("bluejay_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.BLUEJAY, 3960484, 38835, new Item.Settings().group(Naturalist.TAB)));
    public static final Item CARDINAL_SPAWN_EGG = registerItem("cardinal_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.CARDINAL, 9765900, 16739600, new Item.Settings().group(Naturalist.TAB)));
    public static final Item CANARY_SPAWN_EGG = registerItem("canary_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.CANARY, 14979584, 16769792, new Item.Settings().group(Naturalist.TAB)));
    public static final Item ROBIN_SPAWN_EGG = registerItem("robin_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.ROBIN, 5327440, 16746770, new Item.Settings().group(Naturalist.TAB)));
    public static final Item CATERPILLAR_SPAWN_EGG = registerItem("caterpillar_spawn_egg", () -> new SpawnEggItem(NaturalistEntityTypes.CATERPILLAR, 3815473, 15647488, new Item.Settings().group(Naturalist.TAB)));
    public static final Item VENISON = registerItem("venison", () -> new Item(new Item.Settings().group(Naturalist.TAB).food(FoodComponents.MUTTON)));
    public static final Item COOKED_VENISON = registerItem("cooked_venison", () -> new Item(new Item.Settings().group(Naturalist.TAB).food(FoodComponents.COOKED_MUTTON)));
    public static final Item ANTLER = registerItem("antler", () -> new Item(new Item.Settings().group(Naturalist.TAB)));
    public static final Item GLOW_GOOP = registerItem("glow_goop", () -> new Item(new Item.Settings().group(Naturalist.TAB)));
    public static final Item SNAIL_SHELL = registerItem("snail_shell", () -> new Item(new Item.Settings().group(Naturalist.TAB)));
    public static final Item CHRYSALIS = registerItem("chrysalis", () -> new BlockItem(NaturalistBlocks.CHRYSALIS, new Item.Settings().group(Naturalist.TAB).maxCount(1)));
    public static final Item SNAIL_BUCKET = registerItem("snail_bucket", () -> new NoFluidMobBucketItem(() -> NaturalistEntityTypes.SNAIL, Fluids.EMPTY, SoundEvents.ITEM_BUCKET_EMPTY, new Item.Settings().group(Naturalist.TAB).maxCount(1)));

    public static <T extends Item> T registerItem(String name, Supplier<T> item) {
        return Registry.register(Registry.ITEM, new Identifier(Naturalist.MOD_ID, name), item.get());
    }
}
