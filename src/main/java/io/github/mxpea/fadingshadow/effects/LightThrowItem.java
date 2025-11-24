package io.github.mxpea.fadingshadow.effects;

import io.github.mxpea.fadingshadow.effects.LightningBottleEntity;
import io.github.mxpea.fadingshadow.item.ModItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 这是你自定义的“被扔出去的瓶子”的物品类。
 * 玩家右键使用时，会扔出一个 LightningBottleEntity 实体。
 */
public class LightThrowItem extends Item {
    public LightThrowItem(Properties properties) {
        super(properties);
    }

    /**
     * 当玩家右键使用物品时触发。
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // 播放投掷声音
        world.playSound(null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.PLAYERS,
                0.5F, // 音量
                1.0F  // 音高
        );

        // 只在服务器端生成投掷物实体
        if (!world.isClientSide) {
            // 创建一个投掷物实体，参数为世界和玩家
            LightningBottleEntity entity = new LightningBottleEntity(world, player);

            // 设置投掷的角度和速度
            // 参数依次为：玩家对象、俯仰角、偏航角、无作用的z偏移、速度、散射
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

            // 把实体加到世界里
            world.addFreshEntity(entity);
        }

        // 给玩家统计“用了这个物品”次数
        player.awardStat(Stats.ITEM_USED.get(this));

        // 如果不是创造模式，消耗一个瓶子
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        // 返回结果，表示已经成功使用物品
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}