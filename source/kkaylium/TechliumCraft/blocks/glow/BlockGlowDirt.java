package kkaylium.TechliumCraft.blocks.glow;

import java.util.List;
import java.util.Random;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author kkaylium
 * @version 1.0.1a
 *
 */
public class BlockGlowDirt extends Block{

	public static final String[] dirtNames = new String[]{"Red", "Orange", "Yellow", "Lime", "Green", "Sky", "Blue", "Lilac", "Purple", "Pink"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[dirtNames.length];
	
	public BlockGlowDirt() {
		super(Material.ground);
		this.setLightLevel(0.5F);
	this.setCreativeTab(TechliumCraft.GGTab);
		this.setBlockTextureName("glowDirt");
		this.setHardness(0.8F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeGrass);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int id, int meta){
		if(meta < 0 || meta >= this.icons.length){
			meta = 0;
		}
		return this.icons[meta];
	}
	
	@Override
	public int damageDropped(int damage){
		return damage;
	}
	
	@Override
	public int quantityDropped(Random rand){
		return 1;
	}
	
	@Override
	public boolean canSilkHarvest(){
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs creatveTabs, List list){
		list.add(new ItemStack(par1, 1, 0)); //White
		list.add(new ItemStack(par1, 1, 1)); //Black
		list.add(new ItemStack(par1, 1, 2)); //Red
		list.add(new ItemStack(par1, 1, 3)); //Orange
		list.add(new ItemStack(par1, 1, 4)); //Yellow
		list.add(new ItemStack(par1, 1, 5)); //Lime
		list.add(new ItemStack(par1, 1, 6)); //Green
		list.add(new ItemStack(par1, 1, 7)); //Sky
		list.add(new ItemStack(par1, 1, 8)); //Blue
		list.add(new ItemStack(par1, 1, 9)); //Lilac
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconRegister){
            this.icons = new IIcon[dirtNames.length];
            
            for(int i = 0; i < this.icons.length; i++) {
                    this.icons[i] = iconRegister.registerIcon(Strings.MOD_ID + ":" + this.getTextureName() + dirtNames[i]);
            }
    }
}
