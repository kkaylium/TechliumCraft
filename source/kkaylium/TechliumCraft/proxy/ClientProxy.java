package kkaylium.TechliumCraft.proxy;

import kkaylium.TechliumCraft.mobs.darkSlimes.EntityDarkSlime;
import kkaylium.TechliumCraft.mobs.darkSlimes.ModelDarkSlime;
import kkaylium.TechliumCraft.mobs.darkSlimes.RenderDarkSlimes;
import kkaylium.TechliumCraft.mobs.darkTurtles.EntityDarkTurtle;
import kkaylium.TechliumCraft.mobs.darkTurtles.ModelDarkTurtle;
import kkaylium.TechliumCraft.mobs.darkTurtles.RenderDarkTurtle;
import kkaylium.TechliumCraft.mobs.entities.EntityRainbowSlime;
import kkaylium.TechliumCraft.mobs.models.ModelRainbowSlime;
import kkaylium.TechliumCraft.mobs.rainbowTurtles.EntityRainbowTurtle;
import kkaylium.TechliumCraft.mobs.rainbowTurtles.ModelRainbowTurtle;
import kkaylium.TechliumCraft.mobs.rainbowTurtles.RenderRainbowTurtle;
import kkaylium.TechliumCraft.mobs.renderers.RenderRainbowSlimes;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
//	@Override
//	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		switch (ID) {
//			case 0: return new GuiInfuserLevelOne();
//			default: return null;
//		}
//	}
	
	public void registerRenderInformation()
	{
		super.registerRenderInformation();
		//RenderingRegistry.registerEntityRenderingHandler(EntityDarkSlime.class, new RenderDarkSlimes(new ModelSlime(16), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRainbowSlime.class, new RenderRainbowSlimes(new ModelRainbowSlime(16), new ModelRainbowSlime(0), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkSlime.class, new RenderDarkSlimes(new ModelDarkSlime(16), new ModelDarkSlime(0), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityRainbowTurtle.class, new RenderRainbowTurtle(new ModelRainbowTurtle(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkTurtle.class, new RenderDarkTurtle(new ModelDarkTurtle(), 0.5F));
	}
}
