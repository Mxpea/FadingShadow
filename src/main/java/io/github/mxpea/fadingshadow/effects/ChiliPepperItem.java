//这个类定义了red_chili可以吃，并且可以让玩家着火
package io.github.mxpea.fadingshadow.effects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;



public class ChiliPepperItem extends Item {

    public ChiliPepperItem(Properties props) {
        super(props.food(new FoodProperties.Builder()
                .alwaysEdible()  //一直能吃
                .build())//不知道是干什么的，ai给的，先留着吧……
                //ber哥们，这是构造器的结尾，看到上面的FoodProperties.Builder()了吗 -Mxpea
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // 先做默认食用逻辑（减少物品、恢复饱食度等，如果你加了 food component）
        ItemStack result = super.finishUsingItem(stack, level, entity);

        // 让实体着火：给它 igniteForTicks
        // 这里我们让玩家和生物着火 2 秒（2 * 20 ticks = 40 ticks）
        if (!level.isClientSide()) {
            entity.igniteForTicks(40); //着火时间

        }


        return result;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {return UseAnim.EAT;}  //设置让这个物品是可以吃的
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }   //设置这个物品吃的时间
    // 其他代码（如果你需要 food 恢复饱食度的话）省略或自己加
}
