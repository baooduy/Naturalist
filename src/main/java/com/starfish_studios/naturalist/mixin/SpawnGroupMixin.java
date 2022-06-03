package com.starfish_studios.naturalist.mixin;

import com.starfish_studios.naturalist.registry.NaturalistMobCategories;
import net.minecraft.entity.SpawnGroup;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(SpawnGroup.class)
public abstract class SpawnGroupMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static SpawnGroup newSpawnGroup(String internalName, int internalId, String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    SpawnGroup[] field_6301;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/entity/SpawnGroup;field_6301:[Lnet/minecraft/entity/SpawnGroup;", shift = At.Shift.AFTER))
    private static void addCustomSpawnGroup(CallbackInfo ci) {
        var spawnGroups = new ArrayList<>(Arrays.asList(field_6301));
        var last = spawnGroups.get(spawnGroups.size() - 1);
        // This means our code will still work if other mods or Mojang add more variants!
        var spawnGroup = newSpawnGroup("FIREFLIES", last.ordinal() + 1, "fireflies", 5, true, false, 128);
        NaturalistMobCategories.FIREFLIES = spawnGroup;
        spawnGroups.add(spawnGroup);
        field_6301 = spawnGroups.toArray(new SpawnGroup[0]);
    }
}
