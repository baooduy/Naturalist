package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class NaturalistPotions {
    public static void init(){
    }

    public static final Potion FOREST_DASHER = registerPotion("forest_dasher", () -> new Potion(new StatusEffectInstance(StatusEffects.SPEED, 400, 1), new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 0)));
    public static final Potion LONG_FOREST_DASHER = registerPotion("long_forest_dasher", () -> new Potion("forest_dasher", new StatusEffectInstance(StatusEffects.SPEED, 800, 1), new StatusEffectInstance(StatusEffects.WEAKNESS, 800, 0)));
    public static final Potion STRONG_FOREST_DASHER = registerPotion("strong_forest_dasher", () -> new Potion("forest_dasher", new StatusEffectInstance(StatusEffects.SPEED, 400, 2), new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 1)));
    public static final Potion GLOWING = registerPotion("glowing", () -> new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 400)));
    public static final Potion LONG_GLOWING = registerPotion("long_glowing", () -> new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, 800)));

    public static <T extends Potion> T registerPotion(String name, Supplier<T> potion) {
        return Registry.register(Registry.POTION, new Identifier(Naturalist.MOD_ID, name), potion.get());
    }
}
