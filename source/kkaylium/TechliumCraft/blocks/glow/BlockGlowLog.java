package kkaylium.TechliumCraft.blocks.glow;

import java.util.Random;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.lib.Strings;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowLog extends BlockContainer{

	 public static final String[] woodType = new String[] {"Glow"};
     @SideOnly(Side.CLIENT)
     private IIcon iconLogTop;
     @SideOnly(Side.CLIENT)
     private IIcon iconLogBottom;

     public BlockGlowLog()
     {
             super(Material.wood);
             this.setCreativeTab(TechliumCraft.GGTab);
             this.setStepSound(soundTypeWood);
             this.setHardness(2.0F);
             this.setLightLevel(1.0F);
     }

     /**
      * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
      */
     public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
     {
             int j1 = par9 & 3;
             byte b0 = 0;
             switch (par5)
             {
             case 0:
             case 1:
                     b0 = 0;
                     break;
             case 2:
             case 3:
                     b0 = 8;
                     break;
             case 4:
             case 5:
                     b0 = 4;
             }
             return j1 | b0;
     }
     
     /**
      * Returns the quantity of items to drop on block destruction.
      */
     public int quantityDropped(Random par1Random)
     {
             return 1;
     }

     /** gets the icon **/
     public IIcon getIcon(int par1, int par2)
     {
             return par1 == 1 ? this.iconLogTop : (par1 == 0 ? this.iconLogBottom : this.blockIcon);
             
     }

     @SideOnly(Side.CLIENT)
     public void registerBlockIcons(IIconRegister par1IconRegister)
     {
             /** change the "Tutorial:LogSide,LogTop to your texture names **/
             this.blockIcon = par1IconRegister.registerIcon(Strings.MOD_ID + ":GlowLog");
             this.iconLogTop = par1IconRegister.registerIcon(Strings.MOD_ID + ":GlowLogTop");
             this.iconLogBottom = par1IconRegister.registerIcon(Strings.MOD_ID + ":GlowLogTop");
     }

     @Override
     public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
     {
             return true;
     }

     @Override
     public boolean isWood(IBlockAccess world, int x, int y, int z)
     {
             return true;
     }

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return null;
	}

}
