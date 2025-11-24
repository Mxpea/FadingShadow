package io.github.mxpea.fadingshadow.block.entity.client;

import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.block.entity.AnimatedBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {
    @Override
    public ResourceLocation getModelResource(AnimatedBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(FadingShadow.MODID, "geo/scranton_reality_anchors.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(FadingShadow.MODID, "textures/block/scranton_reality_anchors.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(FadingShadow.MODID, "animations/block_scranton_reality_anchors.animation.json");
    }
}
