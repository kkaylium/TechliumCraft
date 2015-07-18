package kkaylium.TechliumCraft.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

/**
 * 
 * @author kkaylium
 * @version 1.0.1a
 *
 */
public class ItemSmallCrystal extends Item{
	
	private String[] crystalColors = new String[]{"White", "Black", "Red", "Orange", "Yellow", "Lime", "Green", "Sky", "Blue", "Lilac", "Purple", "Pink", "Special"};
	//public int[] iconColor = new int[]{0xE1E4F7, 0x363638, 0xB81A1A, 0xF0760C, 0xF2D94B, 0x21ED2F, 0x097A25, 0x4CECF5, 0x0010C4, 0xC977FC, 0x6F05B0, 0xFF57C4, 0x7A7B80};
	private String textureName;
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[crystalColors.length];
	
	public ItemSmallCrystal(String texture){
		super();
		textureName = texture;
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		StringBuilder unlocalizedName = new StringBuilder();
        int meta = MathHelper.clamp_int(itemstack.getItemDamage(), 0, 12);       
        unlocalizedName.append("item.");
        unlocalizedName.append(Strings.MOD_ID + ":");
        unlocalizedName.append(textureName);
        unlocalizedName.append(crystalColors[meta]);    
        return unlocalizedName.toString();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		int i = MathHelper.clamp_int(damage, 0, 12);
		return this.icons[i];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs creativetabs, List list) {
		for(int i = 0; i < 13; i++){
			list.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.icons = new IIcon[crystalColors.length];
		for(int i = 0; i < crystalColors.length; i++){
			icons[i] = iconRegister.registerIcon(Strings.MOD_ID + ":" + textureName + crystalColors[i]);
		}
	}

//	@SideOnly(Side.CLIENT)
//    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
//		
//	}
	
}
