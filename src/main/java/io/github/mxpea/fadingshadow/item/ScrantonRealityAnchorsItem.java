package io.github.mxpea.fadingshadow.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class ScrantonRealityAnchorsItem extends BlockItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public ScrantonRealityAnchorsItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        state.getController().setAnimation(
                RawAnimation.begin().thenLoop("animation.scranton_reality_anchors.new")
        );
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}