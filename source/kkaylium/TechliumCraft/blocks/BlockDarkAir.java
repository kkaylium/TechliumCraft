package kkaylium.TechliumCraft.blocks;

import kkaylium.TechliumCraft.TechliumCraft;
import net.minecraft.block.BlockAir;

public class BlockDarkAir extends BlockAir{
	
	public BlockDarkAir(){
		super();
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setLightLevel(0.0F);
		this.setLightOpacity(50);
	}
}
