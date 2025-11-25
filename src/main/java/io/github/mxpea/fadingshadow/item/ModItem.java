package io.github.mxpea.fadingshadow.item;

import io.github.mxpea.fadingshadow.effects.AppleJuiceItem;
import io.github.mxpea.fadingshadow.effects.ChiliPepperItem;
import io.github.mxpea.fadingshadow.FadingShadow;
import io.github.mxpea.fadingshadow.effects.LightThrowItem;
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

    public static final DeferredItem<Item> lightning_in_a_bottle =      //下面的代码可以设置最大堆叠数（maxStackSize）
            ITEMS.register("lightning_in_a_bottle", () -> new LightThrowItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> apple_juice =
            ITEMS.register("apple_juice", () -> new AppleJuiceItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> test =
            ITEMS.register("test", () -> new Item(new Item.Properties()));//be deleted later

    public static final DeferredItem<Item> red_chili =
            ITEMS.register("red_chili", () -> new ChiliPepperItem(new Item.Properties()));

    public static final DeferredItem<Item> advanced_geiger_counter =
            ITEMS.register("advanced_geiger_counter", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> geiger_counter =
            ITEMS.register("geiger_counter", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> firesalt =
            ITEMS.register("firesalt", () -> new Item(new Item.Properties()));



    //下面是初始化的，不要动
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
