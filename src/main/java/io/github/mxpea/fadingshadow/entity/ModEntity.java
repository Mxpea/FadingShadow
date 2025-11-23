package io.github.mxpea.fadingshadow.entity;

//这个类可以允许注册新的实体
import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.effects.LightningBottleEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FadingShadow.MODID);

    //下面可以注册新的实体，复制然后改个名字就好了
    public static final Supplier<EntityType<LightningBottleEntity>> LIGHTNING_BOTTLE =
            ENTITY_TYPE.register("lightning_bottle",() -> EntityType.Builder.<LightningBottleEntity>of(LightningBottleEntity::new, MobCategory.MISC)
                    .sized(0.5f,1.15f).build("lightning_bottle"));

    //初始化内容，保留别动
    public static void register(IEventBus eventBus){
        ENTITY_TYPE.register(eventBus);
    }
}


