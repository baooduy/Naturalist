package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NaturalistTags {
    public static class Items {
        public static final TagKey<Item> BEAR_TEMPT_ITEMS = tag("bear_tempt_items");
        public static final TagKey<Item> SNAKE_TEMPT_ITEMS = tag("snake_tempt_items");

        private static TagKey<Item> tag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Naturalist.MOD_ID, name));
        }
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> BEAR_HOSTILES = tag("bear_hostiles");
        public static final TagKey<EntityType<?>> SNAKE_HOSTILES = tag("snake_hostiles");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(Naturalist.MOD_ID, name));
        }
    }
}
