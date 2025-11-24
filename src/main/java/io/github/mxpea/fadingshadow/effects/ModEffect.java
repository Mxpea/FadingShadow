package io.github.mxpea.fadingshadow.effects;

import io.github.mxpea.fadingshadow.FadingShadow;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.neoforged.neoforge.common.damagesource.DamageContainer.Reduction.MOB_EFFECTS;

public class ModEffect {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
        DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, FadingShadow.MODID);
    //这里是注册效果的地方，复制它就行
    public static final Holder<MobEffect> ChiliEffect = MOB_EFFECTS.register("chilieffect",
            () ->new ChiliEffect(MobEffectCategory.NEUTRAL,0xd25c37));

    //下面是初始化的，不要动
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
