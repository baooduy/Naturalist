package com.starfish_studios.naturalist.registry;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.block.ChrysalisBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class NaturalistBlocks {
    public static void init(){
    }

    public static final Block CHRYSALIS = Registry.register(Registry.BLOCK, "chrysalis", new ChrysalisBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().strength(0.2F, 3.0F).sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision()));

    private static <T extends Block> T registerBlock(String name, Supplier<T> block) {
        T toReturn = Registry.register(Registry.BLOCK, new Identifier(Naturalist.MOD_ID, name), block.get());
        Registry.register(Registry.ITEM, new Identifier(Naturalist.MOD_ID, name), new BlockItem(toReturn, new Item.Settings().group(Naturalist.TAB)));
        return toReturn;
    }
}
