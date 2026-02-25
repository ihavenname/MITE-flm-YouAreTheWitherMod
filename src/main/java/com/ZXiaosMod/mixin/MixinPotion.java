package com.ZXiaosMod.mixin;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Potion.class)
public class MixinPotion {

    @Inject(
            method = "performEffect(Lnet/minecraft/EntityLivingBase;I)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventWitherDamageForPlayer(EntityLivingBase entity, int amplifier, CallbackInfo ci) {
        if (((Potion)(Object)this).getId() == Potion.wither.getId() && entity instanceof EntityPlayer) {
            ci.cancel();
        }
    }
}