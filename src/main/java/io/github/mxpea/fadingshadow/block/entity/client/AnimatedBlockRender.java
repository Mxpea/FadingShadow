package io.github.mxpea.fadingshadow.block.entity.client;

import io.github.mxpea.fadingshadow.block.entity.AnimatedBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnimatedBlockRender extends GeoBlockRenderer<AnimatedBlockEntity> {
    public AnimatedBlockRender(BlockEntityRendererProvider.Context context) {
        super(new AnimatedBlockModel());
    }

}
