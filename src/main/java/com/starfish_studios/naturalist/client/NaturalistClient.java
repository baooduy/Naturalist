package com.starfish_studios.naturalist.client;

import com.starfish_studios.naturalist.client.renderer.*;
import com.starfish_studios.naturalist.registry.NaturalistBlocks;
import com.starfish_studios.naturalist.registry.NaturalistEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class NaturalistClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(NaturalistBlocks.CHRYSALIS, RenderLayer.getCutout());

        EntityRendererRegistry.register(NaturalistEntityTypes.SNAIL, SnailRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.BEAR, BearRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.BUTTERFLY, ButterflyRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.FIREFLY, FireflyRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.SNAKE, SnakeRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.CORAL_SNAKE, SnakeRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.RATTLESNAKE, SnakeRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.DEER, DeerRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.BLUEJAY, BirdRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.CARDINAL, BirdRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.CANARY, BirdRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.ROBIN, BirdRenderer::new);
        EntityRendererRegistry.register(NaturalistEntityTypes.CATERPILLAR, CaterpillarRenderer::new);
    }
}
