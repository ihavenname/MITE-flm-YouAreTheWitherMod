package com.ZXiaosMod.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityItem.class)
public class MixinEntityItem {

    @Inject(method = "handleExplosion", at = @At("HEAD"), cancellable = true)
    private void injectHandleExplosion(Explosion explosion, CallbackInfoReturnable<Boolean> cir) {
        try {
            EntityItem self = (EntityItem)(Object)this;
            ItemStack stack = self.getEntityItem();
            if (stack == null) {
                cir.setReturnValue(false);
                return;
            }

            double dx = self.posX - explosion.explosionX;
            double dy = self.posY - explosion.explosionY;
            double dz = self.posZ - explosion.explosionZ;
            double distanceSq = dx * dx + dy * dy + dz * dz;
            if (distanceSq < 1.0) distanceSq = 1.0;

            float explosionSize = explosion.explosion_size_vs_blocks;
            float damage = explosionSize / (float)Math.pow(distanceSq, 0.75);

            self.attackEntityFrom(new Damage(DamageSource.setExplosionSource(explosion), damage * 20.0F));
            self.applyExplosionMotion(explosion);

            cir.setReturnValue(true);
        } catch (Exception e) {
            e.printStackTrace();
            cir.setReturnValue(false);
        }
    }
}