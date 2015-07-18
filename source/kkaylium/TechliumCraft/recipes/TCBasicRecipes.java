package kkaylium.TechliumCraft.recipes;

import kkaylium.TechliumCraft.inits.TCInits;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCBasicRecipes {

	public static ItemStack glass = new ItemStack(Blocks.glass);
	public static ItemStack glowSticks = new ItemStack(TCInits.glowSticks);
	public static ItemStack glowPlanks = new ItemStack(TCInits.glowPlanks);
	public static ItemStack glowLog = new ItemStack(TCInits.glowLog);
	
	public static void initBasicRecipes() {
		
		//GameRegistry.addShapelessRecipe(new ItemStack(BlocksInit.glowPlanks, 4), "#", '#', glowLog);
		GameRegistry.addRecipe(new ItemStack(TCInits.glowSticks, 4), "#", "#",'#', glowPlanks);
		
	}

}
