package kkaylium.mods.TechliumCraft.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import kkaylium.mods.TechliumCraft.mobs.*;
import kkaylium.mods.TechliumCraft.mobs.DarkSlime.ModelDarkSlime;
import kkaylium.mods.TechliumCraft.mobs.DarkSlime.RenderDarkSlime;
import kkaylium.mods.TechliumCraft.mobs.DarkTurtle.ModelDarkTurtle;
import kkaylium.mods.TechliumCraft.mobs.DarkTurtle.RenderDarkTurtle;
import kkaylium.mods.TechliumCraft.mobs.PinkSnowman.ModelPinkSnowman;
import kkaylium.mods.TechliumCraft.mobs.PinkSnowman.RenderPinkSnowman;
import kkaylium.mods.TechliumCraft.mobs.RainbowSlime.ModelRainbowSlime;
import kkaylium.mods.TechliumCraft.mobs.RainbowSlime.RenderRainbowSlime;
import kkaylium.mods.TechliumCraft.mobs.RainbowTurtle.ModelRainbowTurtle;
import kkaylium.mods.TechliumCraft.mobs.RainbowTurtle.RenderRainbowTurtle;

/**
 * Created by Kayla Marie on 7/26/14.
 */
public class ClientProxy extends CommonProxy {
    public void registerRenderInformation()
    {
        super.registerRenderInformation();
        RenderingRegistry.registerEntityRenderingHandler(EntityRainbowSlime.class, new RenderRainbowSlime(new ModelRainbowSlime(16), new ModelRainbowSlime(0), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkSlime.class, new RenderDarkSlime(new ModelDarkSlime(16), new ModelDarkSlime(0), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityRainbowTurtle.class, new RenderRainbowTurtle(new ModelRainbowTurtle(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkTurtle.class, new RenderDarkTurtle(new ModelDarkTurtle(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityPinkSnowman.class, new RenderPinkSnowman(new ModelPinkSnowman(), 0.5F));
    }
}
