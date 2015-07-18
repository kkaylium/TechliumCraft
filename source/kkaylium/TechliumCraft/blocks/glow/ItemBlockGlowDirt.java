package kkaylium.TechliumCraft.blocks.glow;

import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemBlockGlowDirt extends ItemBlockWithMetadata{
	
	public ItemBlockGlowDirt(Block par1) {
		super(par1, par1);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		StringBuilder unlocalizedName = new StringBuilder();
		int meta = MathHelper.clamp_int(itemstack.getItemDamage(), 0, 9);
		
		unlocalizedName.append("item.");
		unlocalizedName.append(Strings.MOD_ID + ":");
		unlocalizedName.append("glowDirt");
		unlocalizedName.append(BlockGlowDirt.dirtNames[meta]);
		
		return unlocalizedName.toString();
	}
}
