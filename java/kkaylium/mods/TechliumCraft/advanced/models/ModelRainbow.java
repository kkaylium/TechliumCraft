package kkaylium.mods.TechliumCraft.advanced.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Kayla Marie on 10/20/14.
 */

public class ModelRainbow extends ModelBase
{
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public ModelRainbow()
    {
        textureWidth = 128;
        textureHeight = 64;

        Shape1 = new ModelRenderer(this, 0, 52);
        Shape1.addBox(-7F, -5F, -3.5F, 1, 5, 7);
        Shape1.setRotationPoint(0F, 24F, 0F);
        Shape1.setTextureSize(128, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 43);
        Shape2.addBox(-6F, -7F, -3.5F, 1, 2, 7);
        Shape2.setRotationPoint(0F, 24F, 0F);
        Shape2.setTextureSize(128, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 35);
        Shape3.addBox(-5F, -8F, -3.5F, 1, 1, 7);
        Shape3.setRotationPoint(0F, 24F, 0F);
        Shape3.setTextureSize(128, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 27);
        Shape4.addBox(-4F, -9F, -3.5F, 2, 1, 7);
        Shape4.setRotationPoint(0F, 24F, 0F);
        Shape4.setTextureSize(128, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        //Shape5.mirror = true;
        Shape5 = new ModelRenderer(this, 0, 52);
        Shape5.addBox(6F, -5F, -3.5F, 1, 5, 7);
        Shape5.setRotationPoint(0F, 24F, 0F);
        Shape5.setTextureSize(128, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape5.mirror = false;
        //Shape6.mirror = true;
        Shape6 = new ModelRenderer(this, 0, 43);
        Shape6.addBox(5F, -7F, -3.5F, 1, 2, 7);
        Shape6.setRotationPoint(0F, 24F, 0F);
        Shape6.setTextureSize(128, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape6.mirror = false;
        //Shape7.mirror = true;
        Shape7 = new ModelRenderer(this, 0, 35);
        Shape7.addBox(4F, -8F, -3.5F, 1, 1, 7);
        Shape7.setRotationPoint(0F, 24F, 0F);
        Shape7.setTextureSize(128, 64);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape7.mirror = false;
        //Shape8.mirror = true;
        Shape8 = new ModelRenderer(this, 0, 27);
        Shape8.addBox(2F, -9F, -3.5F, 2, 1, 7);
        Shape8.setRotationPoint(0F, 24F, 0F);
        Shape8.setTextureSize(128, 64);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape8.mirror = false;
        Shape9 = new ModelRenderer(this, 0, 19);
        Shape9.addBox(-2F, -10F, -3.5F, 4, 1, 7);
        Shape9.setRotationPoint(0F, 24F, 0F);
        Shape9.setTextureSize(128, 64);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}

