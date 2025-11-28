package io.github.mxpea.fadingshadow;

import io.github.mxpea.fadingshadow.block.entity.ModBlockEntities;
import io.github.mxpea.fadingshadow.block.entity.client.AnimatedBlockRender;
import io.github.mxpea.fadingshadow.entity.ModEntity;
import io.github.mxpea.fadingshadow.item.ModItem;
import io.github.mxpea.fadingshadow.item.ModPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = FadingShadow.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = FadingShadow.MODID, value = Dist.CLIENT)
public class FadingShadowClient {
    public FadingShadowClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // 注册让投掷闪电瓶子用标准的“物品飞出去”渲染
        event.registerEntityRenderer(ModEntity.LIGHTNING_BOTTLE.get(), ThrownItemRenderer::new);
    }



    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        FadingShadow.LOGGER.info("hi from the void");
        FadingShadow.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        BlockEntityRenderers.register(ModBlockEntities.SRA.get(), AnimatedBlockRender::new);
    }

    //这个是用来添加酿造台配方的
   @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(
                Potions.AWKWARD,
                ModItem.red_chili.get(),
                ModPotion.red_chili_juice
        );
    }
}



