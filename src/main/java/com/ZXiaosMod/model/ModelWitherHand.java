package com.ZXiaosMod.model;

import net.minecraft.Entity;
import net.minecraft.ModelBiped;
import net.minecraft.ModelRenderer;
import net.minecraft.ModelWither;

import java.lang.reflect.Field;

public class ModelWitherHand extends ModelBiped {
    private ModelRenderer[] leftArmParts;
    private ModelRenderer[] rightArmParts;

    public ModelWitherHand() {
        super(0.0F);

        try {
            ModelWither wither = new ModelWither();
            Field bodiesField = ModelWither.class.getDeclaredField("field_82905_a");
            bodiesField.setAccessible(true);
            ModelRenderer[] bodies = (ModelRenderer[]) bodiesField.get(wither);

            leftArmParts = new ModelRenderer[]{bodies[1]};
            rightArmParts = new ModelRenderer[]{bodies[2]};
        } catch (Exception e) {
            e.printStackTrace();
            leftArmParts = new ModelRenderer[0];
            rightArmParts = new ModelRenderer[0];
        }
    }

    public void renderLeftArm(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
        renderArm(leftArmParts, entity, f, f1, f2, f3, f4, scale);
    }

    public void renderRightArm(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
        renderArm(rightArmParts, entity, f, f1, f2, f3, f4, scale);
    }

    private void renderArm(ModelRenderer[] parts, Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
        if (parts == null || parts.length == 0) return;

        ModelWither tempWither = new ModelWither();
        tempWither.setRotationAngles(f, f1, f2, f3, f4, scale, entity);

        for (ModelRenderer part : parts) {
            part.showModel = true;
            part.render(scale);
        }
    }
}