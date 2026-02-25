package com.ZXiaosMod.mixin;

import net.minecraft.AbstractClientPlayer;
import net.minecraft.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class MixinAbstractClientPlayer {

    @Inject(method = "getLocationSkin", at = @At("HEAD"), cancellable = true)
    private void getCustomSkin(CallbackInfoReturnable<ResourceLocation> cir) {
        ResourceLocation witherSkin = new ResourceLocation("textures/entity/wither/wither.png");
        cir.setReturnValue(witherSkin);
    }
}
