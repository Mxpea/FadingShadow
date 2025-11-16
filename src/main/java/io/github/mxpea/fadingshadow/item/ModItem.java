package io.github.mxpea.fadingshadow.item;

import io.github.mxpea.fadingshadow.effects.AppleJuiceItem;
import io.github.mxpea.fadingshadow.effects.ChiliPepperItem;
import io.github.mxpea.fadingshadow.FadingShadow;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItem {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(FadingShadow.MODID);
//在这里注册物品，复制就行
    public static final DeferredItem<Item> reality_fabric =
            ITEMS.register("reality_fabric", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> apple_juice =
            ITEMS.register("apple_juice", () -> new AppleJuiceItem(new Item.Properties()));

    public static final DeferredItem<Item> test =
            ITEMS.register("test", () -> new Item(new Item.Properties()));//be deleted later

    public static final DeferredItem<Item> red_chili =
            ITEMS.register("red_chili", () -> new ChiliPepperItem(new Item.Properties()));

    //下面是初始化的，不要动
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
