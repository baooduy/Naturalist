package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class NaturalistSoundEvents {
    public static void init(){
    }

    public static final SoundEvent SNAKE_HISS = registerSoundEvent("snake_hiss", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.snake.hiss")));
    public static final SoundEvent SNAKE_HURT = registerSoundEvent("snake_hurt", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.snake.hurt")));
    public static final SoundEvent SNAKE_RATTLE = registerSoundEvent("snake_rattle", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.snake.rattle")));
    public static final SoundEvent SNAIL_CRUSH = registerSoundEvent("snail_crush", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.snail.crush")));
    public static final SoundEvent SNAIL_FORWARD = registerSoundEvent("snail_forward", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.snail.forward")));
    public static final SoundEvent SNAIL_BACK = registerSoundEvent("snail_back", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.snail.back")));
    public static final SoundEvent BEAR_HURT = registerSoundEvent("bear_hurt", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.hurt")));
    public static final SoundEvent BEAR_DEATH = registerSoundEvent("bear_death", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.death")));
    public static final SoundEvent BEAR_AMBIENT = registerSoundEvent("bear_ambient", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.ambient")));
    public static final SoundEvent BEAR_AMBIENT_BABY = registerSoundEvent("bear_ambient_baby", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.ambient_baby")));
    public static final SoundEvent BEAR_SLEEP = registerSoundEvent("bear_sleep", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.sleep")));
    public static final SoundEvent BEAR_SNIFF = registerSoundEvent("bear_sniff", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.sniff")));
    public static final SoundEvent BEAR_SPIT = registerSoundEvent("bear_spit", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.spit")));
    public static final SoundEvent BEAR_EAT = registerSoundEvent("bear_eat", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bear.eat")));
    public static final SoundEvent BIRD_HURT = registerSoundEvent("bird_hurt", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.hurt")));
    public static final SoundEvent BIRD_DEATH = registerSoundEvent("bird_death", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.death")));
    public static final SoundEvent BIRD_EAT = registerSoundEvent("bird_eat", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.eat")));
    public static final SoundEvent BIRD_FLY = registerSoundEvent("bird_fly", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.fly")));
    public static final SoundEvent BIRD_AMBIENT_BLUEJAY = registerSoundEvent("bird_ambient_bluejay", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.ambient_bluejay")));
    public static final SoundEvent BIRD_AMBIENT_CANARY = registerSoundEvent("bird_ambient_canary", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.ambient_canary")));
    public static final SoundEvent BIRD_AMBIENT_ROBIN = registerSoundEvent("bird_ambient_robin", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.ambient_robin")));
    public static final SoundEvent BIRD_AMBIENT_CARDINAL = registerSoundEvent("bird_ambient_cardinal", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.bird.ambient_cardinal")));
    public static final SoundEvent FIREFLY_HURT = registerSoundEvent("firefly_hurt", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.firefly.hurt")));
    public static final SoundEvent FIREFLY_DEATH = registerSoundEvent("firefly_death", () -> new SoundEvent(new Identifier(Naturalist.MOD_ID, "entity.firefly.death")));

    private static <T extends SoundEvent> T registerSoundEvent(String name, Supplier<T> soundEvent) {
        return Registry.register(Registry.SOUND_EVENT, new Identifier(Naturalist.MOD_ID, name), soundEvent.get());
    }
}
