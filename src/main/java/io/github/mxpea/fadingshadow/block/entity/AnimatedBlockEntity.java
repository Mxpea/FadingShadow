package io.github.mxpea.fadingshadow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;


public class AnimatedBlockEntity extends BlockEntity implements GeoBlockEntity {

    // 这里需要你定义动画，把文件替换为Blockbench中导出的同名动画文件
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin()
            .thenLoop("animation.example_block.idle");

    private static final RawAnimation ACTIVE_ANIM = RawAnimation.begin()
            .thenPlay("animation.example_block.active");

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public AnimatedBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SRA.get(), pos, blockState);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }




    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtil.getCurrentTick();
    }
}

