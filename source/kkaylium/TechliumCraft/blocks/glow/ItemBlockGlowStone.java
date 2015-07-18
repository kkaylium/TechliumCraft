package kkaylium.TechliumCraft.blocks.glow;

import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemBlockGlowStone extends ItemBlock{
	
	public ItemBlockGlowStone(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		StringBuilder unlocalizedName = new StringBuilder();
		int meta = MathHelper.clamp_int(itemstack.getItemDamage(), 0, 1);
		
		unlocalizedName.append("item.");
		unlocalizedName.append(Strings.MOD_ID + ":");
		unlocalizedName.append("glowStone");
		unlocalizedName.append(BlockGlowStone.stoneNames[meta]);
		
		return unlocalizedName.toString();
	}

}
