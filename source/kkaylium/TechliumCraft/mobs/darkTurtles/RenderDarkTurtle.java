package kkaylium.TechliumCraft.mobs.darkTurtles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class RenderDarkTurtle extends RenderLiving
{

    public static ResourceLocation texture = new ResourceLocation("techliumcraft:textures/entity/rainbowTurtle/texture2.png");

    public RenderDarkTurtle(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(EntityDarkTurtle par1Entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityDarkTurtle) par1Entity);
    }
}
