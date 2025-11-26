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

    private final double randomOffset = this.worldPosition.asLong() % 1000 / 20.0D; // 根据坐标生成稳定随机偏移


    // 对应 block_scranton_reality_anchors.animation.json 中的
    // "animation.scranton_reality_anchors.new"
    private static final RawAnimation SRA_LOOP_ANIM = RawAnimation.begin()
            .thenLoop("animation.scranton_reality_anchors.new");

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public AnimatedBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SRA.get(), pos, blockState);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // tickDelay = 0 表示每 tick 都更新
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    // 这里的 T 可以直接写成 GeoAnimatable
    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        // 只要方块存在，就让它一直播放 SRA_LOOP_ANIM
        state.getController().setAnimation(SRA_LOOP_ANIM);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        // 用渲染 tick 保证客户端动画流畅
        return RenderUtil.getCurrentTick() + randomOffset;
    }
}