package io.github.mxpea.fadingshadow.item;

import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.block.ModBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FadingShadow.MODID);

    public static final Supplier<CreativeModeTab> fading_shadow =
            CREATIVE_MODE_TABS.register("fs_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItem.reality_fabric.get()))
                    .title(Component.translatable("itemGroup.fs_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItem.reality_fabric);
                        output.accept(ModItem.apple_juice);
                        output.accept(ModItem.test);
                        output.accept(ModItem.lightning_in_a_bottle);
                        output.accept(ModBlock.scranton_reality_anchors);
                        output.accept(ModBlock.netherreactor);
                    }).build());

    //初始化
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
