package kkaylium.TechliumCraft.proxy;

import kkaylium.TechliumCraft.mobs.darkSlimes.EntityDarkSlime;
import kkaylium.TechliumCraft.mobs.darkTurtles.EntityDarkTurtle;
import kkaylium.TechliumCraft.mobs.entities.EntityRainbowSlime;
import cpw.mods.fml.common.registry.EntityRegistry;
import kkaylium.TechliumCraft.mobs.rainbowTurtles.EntityRainbowTurtle;


public class CommonProxy{

//	public CommonProxy() {
//		NetworkRegistry.instance().registerGuiHandler(TechliumCraft.instance, this);
//	}
//	
//	@Override
//	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		return null;
//	}
//
//	@Override
//	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		return null;
//	}

	public void registerRenderInformation()
	{
		EntityRegistry.registerGlobalEntityID(EntityRainbowSlime.class, "RainbowSlime", EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0x000000);
        EntityRegistry.registerGlobalEntityID(EntityDarkSlime.class, "DarkSlime", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xffffff);
        EntityRegistry.registerGlobalEntityID(EntityRainbowTurtle.class, "RainbowTurtle", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0xD13E87);
        EntityRegistry.registerGlobalEntityID(EntityDarkTurtle.class, "DarkTurtle", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xD13E87);
	}
	
}
