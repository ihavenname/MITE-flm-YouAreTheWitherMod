package com.ZXiaosMod.model;

import net.minecraft.*;
import java.lang.reflect.Field;

public class ModelPlayerWitherOverlay extends ModelBiped {

    private ModelWither wither;
    private ModelRenderer[] witherHeads;
    private ModelRenderer[] witherBodies;

    private static final float SCALE = 4.0F / 1.8F;

    public ModelPlayerWitherOverlay() {
        super(0.0F);
        wither = new ModelWither();

        try {
            Field headsField = ModelWither.class.getDeclaredField("field_82904_b");
            headsField.setAccessible(true);
            witherHeads = (ModelRenderer[]) headsField.get(wither);

            Field bodiesField = ModelWither.class.getDeclaredField("field_82905_a");
            bodiesField.setAccessible(true);
            witherBodies = (ModelRenderer[]) bodiesField.get(wither);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
        wither.setRotationAngles(f, f1, f2, f3, f4, scale, entity);

        float scaled = scale * 1;

        if (witherHeads != null) {
            for (ModelRenderer head : witherHeads) {
                head.showModel = true;
                head.render(scaled);
            }
        }

        if (witherBodies != null) {
            for (ModelRenderer body : witherBodies) {
                body.showModel = true;
                body.render(scaled);
            }
        }
    }
}