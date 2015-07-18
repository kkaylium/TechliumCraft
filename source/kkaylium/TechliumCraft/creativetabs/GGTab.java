package kkaylium.TechliumCraft.creativetabs;

import kkaylium.TechliumCraft.inits.TCInits;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGTab extends CreativeTabs{
	
	public GGTab(int par1, String par2)
	{
		super(par1, par2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return TCInits.glowCrystals;
	}

}
