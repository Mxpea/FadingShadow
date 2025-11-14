package io.github.mxpea.fadingshadow.item;

import io.github.mxpea.fadingshadow.FadingShadow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


public class ModItem {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(FadingShadow.MODID);

    public static final DeferredItem<Item> reality_fabric =
            ITEMS.register("reality_fabric", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> apple_juice =
            ITEMS.register("apple_juice", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
