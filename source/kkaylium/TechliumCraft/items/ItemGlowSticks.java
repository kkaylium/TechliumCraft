package kkaylium.TechliumCraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemGlowSticks extends Item{
	
	public ItemGlowSticks(){
		super ();
		this.setCreativeTab(TechliumCraft.GGTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
			itemIcon = iconRegister.registerIcon(Strings.MOD_ID + ":glowSticks");
	}

}
