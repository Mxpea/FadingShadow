package io.github.mxpea.fadingshadow.block;

import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.item.ModItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(FadingShadow.MODID);

    public static final DeferredBlock<Block> scranton_reality_anchors =
            registerBlocks("scranton_reality_anchors", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));


    //注册方块物品
    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredBlock<T> registerBlocks(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}