package kkaylium.TechliumCraft.blocks.liquids;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSerumDirtyFlowing extends BlockDynamicLiquid{

	private IIcon[] icons = new IIcon[1];
	public BlockSerumDirtyFlowing(Material par2Material) {
		super(par2Material);
		//this.setCreativeTab(TechliumCraft.GGTab);
		this.setHardness(100.0F);
		this.setLightOpacity(3);
		this.setLightLevel(1.0F);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[] {
                iconRegister.registerIcon(Strings.MOD_ID + ":SerumDirtyStill"),
                iconRegister.registerIcon(Strings.MOD_ID + ":SerumDirtyFlowing") };
    }

	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ != 0 && p_149691_1_ != 1 ? this.icons[1] : this.icons[0];
    }
}
