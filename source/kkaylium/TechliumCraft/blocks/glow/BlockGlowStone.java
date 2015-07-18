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

public class BlockGlowStone extends Block{

	public static final String[] stoneNames = new String[]{"White", "Black"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[stoneNames.length];
	
	public BlockGlowStone() {
		super(Material.rock);
		this.lightValue = 10;
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setBlockTextureName("glowStone");
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
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
	@Override
	public void getSubBlocks(Item par1, CreativeTabs creatveTabs, List list){
		list.add(new ItemStack(par1, 1, 0)); //White
		list.add(new ItemStack(par1, 1, 1)); //Black
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconRegister){
            this.icons = new IIcon[stoneNames.length];
            
            for(int i = 0; i < this.icons.length; i++) {
                    this.icons[i] = iconRegister.registerIcon(Strings.MOD_ID + ":" + this.getTextureName() + stoneNames[i]);
            }
    }

}
