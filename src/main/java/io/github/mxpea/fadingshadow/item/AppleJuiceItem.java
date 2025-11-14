package io.github.mxpea.fadingshadow.item; // ← 确保和文件夹路径一致

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class AppleJuiceItem extends Item {

    public AppleJuiceItem(Properties props) {
        super(props
                .food(new FoodProperties.Builder()
                        .nutrition(2)   //回复饱食度
                        .saturationModifier(0.3F)
                        .alwaysEdible()
                        // 在你的 mappings 中 canAlwaysEat() / alwaysEat() 可能不可见，先不要调用它
                        .build())
        );
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    // 这个签名与 IDE 中父类匹配（如果你之前写的就是这个，就继续用它）
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    // 这是关键：父类在你当前环境中期望 (ItemStack, Level, LivingEntity)
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // 先让父类处理食物效果（比如回血、效果、消耗）
        ItemStack result = super.finishUsingItem(stack, level, entity);

        // 只在服务端给玩家空瓶（并且非创造模式）
        if (!level.isClientSide() && entity instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);

                if (stack.isEmpty()) {
                    // 原始栈为空（物品被完全消耗），返回空瓶
                    return bottle;
                } else {
                    // 否则尝试把瓶子放回背包，放不下就扔地上
                    if (!player.getInventory().add(bottle)) {
                        player.drop(bottle, false);
                    }
                }
            }
        }

        return result;
    }
}
