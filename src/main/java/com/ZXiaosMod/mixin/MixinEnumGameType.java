package com.ZXiaosMod.mixin;

import net.minecraft.EnumGameType;
import net.minecraft.PlayerCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnumGameType.class)
public class MixinEnumGameType {

    @Inject(
            method = "configurePlayerCapabilities",
            at = @At("TAIL")
    )
    private void allowFlyInSurvival(PlayerCapabilities caps, CallbackInfo ci) {
        EnumGameType self = (EnumGameType)(Object)this;

        if (self == EnumGameType.SURVIVAL) {
            caps.allowFlying = true;
            caps.isFlying = false;
            caps.isCreativeMode = false;
            caps.disableDamage = false;
        }
    }
}
