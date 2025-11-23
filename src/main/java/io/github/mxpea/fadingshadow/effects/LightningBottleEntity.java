package io.github.mxpea.fadingshadow.effects;

import io.github.mxpea.fadingshadow.entity.ModEntity;
import io.github.mxpea.fadingshadow.item.ModItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LightningBottleEntity extends ThrowableItemProjectile {

    // 注册和刷怪蛋/世界生成用
    public LightningBottleEntity(EntityType<? extends LightningBottleEntity> type, Level world) {
        super(type, world);
    }

    // 投掷物的构造函数
    public LightningBottleEntity(Level world, LivingEntity owner) {
        super(ModEntity.LIGHTNING_BOTTLE.get(), owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItem.lightning_in_a_bottle.get();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            // 这里写击中时触发的逻辑
            int x,y,z;
            x=getBlockX();
            y=getBlockY();
            z=getBlockZ();
            level().explode(null,x,y,z,4.0f,false, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }
}