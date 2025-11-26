package io.github.mxpea.fadingshadow.block;

import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.item.ModItem;
import io.github.mxpea.fadingshadow.item.ScrantonRealityAnchorsItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/*
author:Mxpea
Edited:25.11.16
 */
public class ModBlock {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(FadingShadow.MODID);

    public static final DeferredBlock<Block> scranton_reality_anchors =
            registerBlocks("scranton_reality_anchors",
                    () -> new AnimatedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredBlock<Block> netherreactor =
            registerBlocks("netherreactor",
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // 默认情况下注册普通方块物品
    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredBlock<T> registerBlocks(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name, block);

        if ("scranton_reality_anchors".equals(name)) {
            // SRA 使用自定义的 Geckolib BlockItem
            ModItem.ITEMS.register(name,
                    () -> new ScrantonRealityAnchorsItem(blocks.get(), new Item.Properties()));
        } else {
            registerBlockItems(name, blocks);
        }

        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}