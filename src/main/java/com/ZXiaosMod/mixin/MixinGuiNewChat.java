package com.ZXiaosMod.mixin;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

import static net.minecraft.Gui.drawRect;

@Mixin(GuiNewChat.class)
public abstract class MixinGuiNewChat extends Gui {

    @Shadow private Minecraft mc;
    @Shadow private List field_96134_d;
    @Shadow private int field_73768_d;
    @Shadow private boolean field_73769_e;
    @Shadow public abstract boolean getChatOpen();
    @Shadow public abstract int func_96127_i();
    @Shadow public abstract float func_96131_h();
    @Shadow public abstract int func_96126_f();

    @Overwrite
    public void drawChat(int par1) {
        if (this.mc.gameSettings.gui_mode == 0) {
            if (this.mc.gameSettings.chatVisibility != 2) {
                int var2 = this.func_96127_i();
                boolean var3 = false;
                int var4 = 0;
                int var5 = this.field_96134_d.size();
                float var6 = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;
                if (var5 > 0) {
                    if (this.getChatOpen()) {
                        var3 = true;
                    }

                    float var7 = this.func_96131_h();
                    int var8 = MathHelper.ceiling_float_int((float)this.func_96126_f() / var7);
                    GL11.glPushMatrix();
                    GL11.glTranslatef(2.0F, 20.0F, 0.0F);
                    GL11.glScalef(var7, var7, 1.0F);

                    for (int var9 = 0; var9 + this.field_73768_d < this.field_96134_d.size() && var9 < var2; ++var9) {
                        ChatLine var10 = (ChatLine)this.field_96134_d.get(var9 + this.field_73768_d);
                        if (var10 != null) {
                            int var11 = par1 - var10.getUpdatedCounter();
                            if (var11 < 200 || var3) {
                                double var12 = (double)var11 / 200.0;
                                var12 = 1.0 - var12;
                                var12 *= 10.0;
                                if (var12 < 0.0) var12 = 0.0;
                                if (var12 > 1.0) var12 = 1.0;
                                var12 *= var12;

                                int var14 = (int)(255.0 * var12);
                                if (var3) var14 = 255;
                                var14 = (int)(var14 * var6);
                                ++var4;

                                if (var14 > 3) {
                                    byte var15 = 0;
                                    int var16 = -var9 * 9;
                                    var16 -= 18;
                                    if (!(this.mc.currentScreen instanceof GuiMITEDS)) {
                                        drawRect(var15, var16 - 9, var15 + var8 + 4, var16, 0x00000000);
                                    }

                                    GL11.glEnable(3042);
                                    String var17 = var10.getChatLineString();
                                    if (!this.mc.gameSettings.chatColours) {
                                        var17 = StringUtils.stripControlCodes(var17);
                                    }

                                    this.mc.fontRenderer.drawStringWithShadow(var17, var15, var16 - 8, 16777215 + (var14 << 24));
                                }
                            }
                        }
                    }

                    if (var3) {
                        int var18 = this.mc.fontRenderer.FONT_HEIGHT;
                        GL11.glTranslatef(-3.0F, 0.0F, 0.0F);
                        var18 = var5 * var18 + var5;
                        int var11 = var4 * var18 + var4;
                        int var20 = this.field_73768_d * var11 / var5;
                        int var13 = var11 * var11 / var18;
                        var20 += var18 * 2;
                        var13 -= var18 * 2 + 2;
                        if (var18 != var11) {
                            int var14 = var20 > 0 ? 170 : 96;
                            int var19 = this.field_73769_e ? 13382451 : 3355562;
                            drawRect(0, -var20, 2, -var20 - var13, var19 + (var14 << 24));
                            drawRect(2, -var20, 1, -var20 - var13, 13421772 + (var14 << 24));
                        }
                    }

                    GL11.glPopMatrix();
                }
            }
        }
    }
}