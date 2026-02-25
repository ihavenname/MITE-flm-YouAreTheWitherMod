package com.ZXiaosMod.mixin;

import com.ZXiaosMod.render.RenderPlayerWither;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.Render;
import net.minecraft.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RenderManager.class)
public class MixinRenderManager {

    private Map<Class<? extends Entity>, Render> entityRenderMap;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        RenderManager self = (RenderManager)(Object)this;
        RenderPlayerWither witherRenderer = new RenderPlayerWither();
        witherRenderer.setRenderManager(self);
        this.entityRenderMap.put(EntityPlayer.class, witherRenderer);
    }
}