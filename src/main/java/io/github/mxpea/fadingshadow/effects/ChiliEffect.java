//这个类定义了喝辣椒水（red_chili_juice）可以饮用和相关的属性
package io.github.mxpea.fadingshadow.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class ChiliEffect extends MobEffect {
    public ChiliEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        livingEntity.igniteForSeconds(2);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
        return false;//super.applyEffectTick(livingEntity, amplifier);
    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}