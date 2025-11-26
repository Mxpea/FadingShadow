package io.github.mxpea.fadingshadow.item.client;

import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.item.ScrantonRealityAnchorsItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ScrantonRealityAnchorsItemModel extends GeoModel<ScrantonRealityAnchorsItem> {
    @Override
    public ResourceLocation getModelResource(ScrantonRealityAnchorsItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(FadingShadow.MODID, "geo/scranton_reality_anchors.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScrantonRealityAnchorsItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(FadingShadow.MODID, "textures/block/scranton_reality_anchors.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ScrantonRealityAnchorsItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(FadingShadow.MODID, "animations/block_scranton_reality_anchors.animation.json");
    }
}
