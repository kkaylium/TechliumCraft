package kkaylium.TechliumCraft.blocks.gen.trees;

import java.util.Random;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowBlock;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCTreeBlock extends BlockContainer{
	
    public static final String[] woodType = new String[] {"Glow", "dark"};
	public int darkLight;
    @SideOnly(Side.CLIENT)
    private IIcon iconLogTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconLogBottom;
    public int[] iconColor = new int[]{0xFFFFFF, 0x363638, 0xB81A1A, 0xF0760C, 0xF2D94B, 0x21ED2F, 0x097A25, 0x4CECF5, 0x0010C4, 0xC977FC, 0x6F05B0, 0xFF57C4, 0xEBF2FA};

	public TCTreeBlock(int dl) {
		super(Material.wood);
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setStepSound(soundTypeWood);
		this.setHardness(2.0F);
		this.setLightLevel(1.0F);
		darkLight = dl;
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6){
        TileEntity t = par1World.getTileEntity(par2, par3, par4);
        EntityItem entityCrystal = new EntityItem(par1World, (double)(par2), (double)(par3), (double)(par4), new ItemStack(TCInits.glowCrystals, 1, ((TileEntityGlowLog)t).color));
        if (t instanceof TileEntityGlowLog && ((TileEntityGlowLog)t).color != 12){
            par1World.spawnEntityInWorld(entityCrystal);
        }
        par1World.removeTileEntity(par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        int color = 12;
        TileEntityGlowLog tile = (TileEntityGlowLog) p_149720_1_.getTileEntity(p_149720_2_, p_149720_3_, p_149720_4_);
        if (tile != null)
        {
            color = tile.color;
        }
        return iconColor[color];
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!par1World.isRemote)
        {
            TileEntity t = par1World.getTileEntity(par2, par3, par4);
            if (t instanceof TileEntityGlowLog && ((TileEntityGlowLog)t).color == 12)
            {
                if (player.inventory.getCurrentItem() != null)
                {
                    if ((player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 1)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 2)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 3)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 4)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 5)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 6)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 7)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 8)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 9)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 10)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 11)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 12)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 13))))
                    {
                        ((TileEntityGlowLog) t).getCrystalUsed(player.inventory.getCurrentItem().getItemDamage());
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
                        this.setLightLevel(1.0F);
                        player.inventory.getCurrentItem().stackSize--;
                        par1World.markBlockForUpdate(par2, par3, par4);
                        return true;
                    }
                }
            }
            return false;
        }
        else
        {
            return false;
        }
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
//     public IIcon getIcon(int par1, int par2)
//     {
//             return par1 == 1 ? this.iconLogTop : (par1 == 0 ? this.iconLogBottom : this.blockIcon);
//
//     }

     @SideOnly(Side.CLIENT)
     public void registerBlockIcons(IIconRegister par1IconRegister)
     {
             /** change the "Tutorial:LogSide,LogTop to your texture names **/
             this.blockIcon = par1IconRegister.registerIcon(Strings.MOD_ID + ":" + woodType[darkLight] + "Log");
             this.iconLogTop = par1IconRegister.registerIcon(Strings.MOD_ID + ":" + woodType[darkLight] + "LogTop");
             this.iconLogBottom = par1IconRegister.registerIcon(Strings.MOD_ID + ":" + woodType[darkLight] + "LogTop");
     }

     @Override
     public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
     {
             return true;
     }

     @Override
     public boolean isWood(IBlockAccess world, int x, int y, int z) {
             return true;
     }

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityGlowLog();
	}

}
