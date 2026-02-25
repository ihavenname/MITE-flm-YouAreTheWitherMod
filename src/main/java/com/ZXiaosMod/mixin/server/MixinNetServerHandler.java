package com.ZXiaosMod.mixin.server;

import com.ZXiaosMod.api.IMySkillInvoker;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetServerHandler.class)
public abstract class MixinNetServerHandler {

    @Shadow
    public ServerPlayer playerEntity;

    @Inject(
            method = "handleEntityAction",
            at = @At("HEAD")
    )
    private void onHandleEntityAction(Packet19EntityAction packet, CallbackInfo ci) {
        IMySkillInvoker invoker = (IMySkillInvoker)(Object)this.playerEntity;

        if (packet.action == 10086) {
            ((IMySkillInvoker)(Object)this.playerEntity).skullSkill();
        }
        if (packet.action == 10085) {
            ((IMySkillInvoker)(Object)this.playerEntity).healSkill();
        }
        if (packet.action == 10084) {
            ((IMySkillInvoker)(Object)this.playerEntity).boomSkill();
        }
        if (packet.action == 10083) {
            ((IMySkillInvoker)(Object)this.playerEntity).nightVisionSkill();
            if (invoker.isNightVisionActive()) {
                playerEntity.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1200, 0));
            } else {
                if (playerEntity.isPotionActive(Potion.nightVision.id)) {
                    playerEntity.removePotionEffect(Potion.nightVision.id);
                }
            }
        }
    }
}