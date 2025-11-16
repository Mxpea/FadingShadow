//这个类可以注册新的药水
package io.github.mxpea.fadingshadow.item;
import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.effects.ModEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModPotion {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, FadingShadow.MODID);
    //复制这个就可以注册新的药水了
    public static final Holder<Potion> red_chili_juice = POTIONS.register("red_chili_juice",
                    () -> new Potion(new MobEffectInstance(ModEffect.ChiliEffect, 400,0)));

    //下面是初始化的，不要动
    public static void register(IEventBus eventBus) {POTIONS.register(eventBus);
    }

}
