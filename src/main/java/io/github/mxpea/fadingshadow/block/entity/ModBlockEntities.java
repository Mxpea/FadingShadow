package io.github.mxpea.fadingshadow.block.entity;

import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.block.ModBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FadingShadow.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AnimatedBlockEntity>> SRA =
            BLOCK_ENTITIES.register("src", () ->
                    BlockEntityType.Builder.of(AnimatedBlockEntity::new,
                            ModBlock.scranton_reality_anchors.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

