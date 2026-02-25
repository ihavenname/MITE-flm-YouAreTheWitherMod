package com.ZXiaosMod.render;

import com.ZXiaosMod.model.ModelPlayerWitherOverlay;
import com.ZXiaosMod.model.ModelWitherHand;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderPlayerWither extends RenderPlayer {

    public RenderPlayerWither() {
        super();
        this.mainModel = new ModelPlayerWitherOverlay();
    }

    @Override
    protected void func_82408_c(net.minecraft.EntityLivingBase entity, int pass, float partialTicks) {
    }

    @Override
    protected int shouldRenderPass(net.minecraft.EntityLivingBase entity, int pass, float partialTicks) {
        return -1;
    }

    @Override
    public void func_130009_a(AbstractClientPlayer player, double x, double y, double z, float yaw, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 1.5F, 0.0F);
        GL11.glTranslatef(0.0F, (float)(player.height * 0.5F), 0.0F);
        GL11.glScalef(1.6F, 1.6F, 1.6F);
        GL11.glTranslatef(0.0F, -(float)(player.height * 0.5F), 0.0F);
        super.func_130009_a(player, x, y, z, yaw, partialTicks);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        super.doRender(entity, x, y, z, yaw, partialTicks);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player) {
        ModelWitherHand handModel = new ModelWitherHand();

        float limbSwing = player.limbSwing;
        float limbSwingAmount = player.limbSwingAmount;
        float ageInTicks = player.ticksExisted;
        float netHeadYaw = player.rotationYaw;
        float headPitch = player.rotationPitch;
        float scale = 0.08F;

        GL11.glPushMatrix();
        GL11.glTranslatef(0.3F, 0.32F, 0.3F);
        GL11.glRotatef(25F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);

        handModel.renderLeftArm(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        handModel.renderRightArm(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GL11.glPopMatrix();
    }
}