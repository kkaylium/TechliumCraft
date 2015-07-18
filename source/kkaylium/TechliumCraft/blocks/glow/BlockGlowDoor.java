package kkaylium.TechliumCraft.blocks.glow;

import java.util.Random;

import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowDoor;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author kkaylium
 * @version 1.0.1a
 *
 */
public class BlockGlowDoor extends BlockDoor implements ITileEntityProvider{

	@SideOnly(Side.CLIENT)
    private IIcon[] field_150017_a;
    @SideOnly(Side.CLIENT)
    private IIcon[] field_150016_b;
    private IIcon topDoorIcon;
//	@SideOnly(Side.CLIENT)
//    private IIcon[] textures = new IIcon[13];
   // public String[] iconNames = new String[] {"GDWhite", "GDBlack", "GDRed", "GDOrange", "GDYellow", "GDLime", "GDGreen", "GDSky", "GDBlue", "GDLilac", "GDPurple", "GDPink", "GDSpecial", "GDBase"};
    public int[] iconColor = new int[]{0xFFFFFF, 0x363638, 0xB81A1A, 0xF0760C, 0xF2D94B, 0x21ED2F, 0x097A25, 0x4CECF5, 0x0010C4, 0xC977FC, 0x6F05B0, 0xFF57C4, 0xEBF2FA};

	public BlockGlowDoor() {
		super(Material.glass);
		//this.setCreativeTab(TechliumCraft.GGTab);
		this.setLightLevel(1.0F);
		float f = 0.5F;
		float f1 = 1.0F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		
	}
	
	public IIcon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
	  if (par5 == 1 || par5 == 0)
	  {
	   return this.blockIcon;
	  }
	  int meta = func_150012_g(par1IBlockAccess, par2, par3, par4);
	  boolean flag = (meta & 4) != 0;
	  int halfMeta = meta & 3;
	  boolean flipped = false;

	  if (flag)
	    {
	    if (halfMeta == 0 && par5 == 2) flipped = !flipped;
	    else if (halfMeta == 1 && par5 == 5) flipped = !flipped;
	    else if (halfMeta == 2 && par5 == 3) flipped = !flipped;
	    else if (halfMeta == 3 && par5 == 4) flipped = !flipped;
	    }
	    else
	    {
	    if (halfMeta == 0 && par5 == 5) flipped = !flipped;
	    else if (halfMeta == 1 && par5 == 3) flipped = !flipped;
	    else if (halfMeta == 2 && par5 == 4) flipped = !flipped;
	    else if (halfMeta == 3 && par5 == 2)flipped = !flipped;
	    if ((meta & 16) != 0)flipped = !flipped;
	    }

	  if (flipped) return field_150016_b[(meta & 8) != 0 ? 1 : 0];
	  else return (meta & 8) != 0 ? this.topDoorIcon : this.blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
    	int color = 12;
    	TileEntityGlowDoor tile = (TileEntityGlowDoor) p_149720_1_.getTileEntity(p_149720_2_, p_149720_3_, p_149720_4_);
    	if (tile != null)
    	{
    		color = tile.color;
    	}
   		return iconColor[color];
    }

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return new ItemStack(TCInits.glowDoorItem);
	}

	@Override 
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return TCInits.glowDoorItem;
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntity t = par1World.getTileEntity(par2, par3, par4);
		if(!par1World.isRemote){
				if (t instanceof TileEntityGlowDoor && ((TileEntityGlowDoor)t).color == 12)
	            {
	                if (player.inventory.getCurrentItem() != null)
	                {
	                    if ((player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 1)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 2)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 3)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 4)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 5)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 6)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 7)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 8)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 9)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 10)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 11)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 12)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 13))))
	                    {
	                    	((TileEntityGlowDoor) t).getCrystalUsed(player.inventory.getCurrentItem().getItemDamage());
	                    	 par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
	                         this.setLightLevel(1.0F);
	                         player.inventory.getCurrentItem().stackSize--;
	                         par1World.markBlockForUpdate(par2, par3, par4);
	                         return true;
	                         
	                    
			}else{
				int i1 = this.func_150012_g(par1World, par2, par3,
						par4);
				int j1 = i1 & 7;
				j1 ^= 4;

				if ((i1 & 8) == 0) {
					par1World.setBlockMetadataWithNotify(par2,
							par3, par4, j1, 2);
					par1World.markBlockRangeForRenderUpdate(par2,
							par3, par4, par2, par3, par4);
				} else {
					par1World.setBlockMetadataWithNotify(par2,
							par3 - 1, par4, j1, 2);
					par1World.markBlockRangeForRenderUpdate(par2,
							par3 - 1, par4, par2, par3, par4);
				}
				par1World.playAuxSFXAtEntity(player, 1003, par2,
						par3, par4, 0);
				par1World.markBlockForUpdate(par2, par3, par4);
				return true;				
			}
			
		} else {
			int i1 = this.func_150012_g(par1World, par2, par3,
					par4);
			int j1 = i1 & 7;
			j1 ^= 4;

			if ((i1 & 8) == 0) {
				par1World.setBlockMetadataWithNotify(par2,
						par3, par4, j1, 2);
				par1World.markBlockRangeForRenderUpdate(par2,
						par3, par4, par2, par3, par4);
			} else {
				par1World.setBlockMetadataWithNotify(par2,
						par3 - 1, par4, j1, 2);
				par1World.markBlockRangeForRenderUpdate(par2,
						par3 - 1, par4, par2, par3, par4);
			}
			par1World.playAuxSFXAtEntity(player, 1003, par2,
					par3, par4, 0);
			par1World.markBlockForUpdate(par2, par3, par4);
			return true;	
		}
		}
		else{
			return false;
			}
		}
		return true;
	}
//			int i1 = this.func_150012_g(par1World, par2, par3,
//					par4);
//			int j1 = i1 & 7;
//			j1 ^= 4;
//
//			if ((i1 & 8) == 0) {
//				par1World.setBlockMetadataWithNotify(par2,
//						par3, par4, j1, 2);
//				par1World.markBlockRangeForRenderUpdate(par2,
//						par3, par4, par2, par3, par4);
//			} else {
//				par1World.setBlockMetadataWithNotify(par2,
//						par3 - 1, par4, j1, 2);
//				par1World.markBlockRangeForRenderUpdate(par2,
//						par3 - 1, par4, par2, par3, par4);
//			}
//			par1World.playAuxSFXAtEntity(player, 1003, par2,
//					par3, par4, 0);
	
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return TCInits.glowDoorItem;
    }
    

	  @SideOnly(Side.CLIENT)
	  @Override
	  public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int s) 
	  {
	        if(s==0 && iBlockAccess.getBlock(x, y-1, z) == this)
	        {
	            return false;
	        }
	        else if(s==1 && iBlockAccess.getBlock(x, y+1, z) == this)
	        {
	            return false;
	        }

	        return super.shouldSideBeRendered(iBlockAccess, x,y,z,s);
	    }
	  
	  
	  @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	    {
	        return this.field_150016_b[0];
	    }

	    @SideOnly(Side.CLIENT)
	    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
	    {
	        if (p_149673_5_ != 1 && p_149673_5_ != 0)
	        {
	            int i1 = this.func_150012_g(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_);
	            int j1 = i1 & 3;
	            boolean flag = (i1 & 4) != 0;
	            boolean flag1 = false;
	            boolean flag2 = (i1 & 8) != 0;

	            if (flag)
	            {
	                if (j1 == 0 && p_149673_5_ == 2)
	                {
	                    flag1 = !flag1;
	                }
	                else if (j1 == 1 && p_149673_5_ == 5)
	                {
	                    flag1 = !flag1;
	                }
	                else if (j1 == 2 && p_149673_5_ == 3)
	                {
	                    flag1 = !flag1;
	                }
	                else if (j1 == 3 && p_149673_5_ == 4)
	                {
	                    flag1 = !flag1;
	                }
	            }
	            else
	            {
	                if (j1 == 0 && p_149673_5_ == 5)
	                {
	                    flag1 = !flag1;
	                }
	                else if (j1 == 1 && p_149673_5_ == 3)
	                {
	                    flag1 = !flag1;
	                }
	                else if (j1 == 2 && p_149673_5_ == 4)
	                {
	                    flag1 = !flag1;
	                }
	                else if (j1 == 3 && p_149673_5_ == 2)
	                {
	                    flag1 = !flag1;
	                }

	                if ((i1 & 16) != 0)
	                {
	                    flag1 = !flag1;
	                }
	            }

	            return flag2 ? this.field_150017_a[flag1?1:0] : this.field_150016_b[flag1?1:0];
	        }
	        else
	        {
	            return this.field_150016_b[0];
	        }
	    }

	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister p_149651_1_)
	    {
	    	this.topDoorIcon = p_149651_1_.registerIcon("techliumcraft:GDRed_upper");
	        this.field_150017_a = new IIcon[2];
	        this.field_150016_b = new IIcon[2];
	        this.field_150017_a[0] = p_149651_1_.registerIcon(Strings.MOD_ID + ":GDWhite" + "_upper");
	        this.field_150016_b[0] = p_149651_1_.registerIcon(Strings.MOD_ID + ":GDWhite" + "_lower");
	        this.field_150017_a[1] = new IconFlipped(this.field_150017_a[0], true, false);
	        this.field_150016_b[1] = new IconFlipped(this.field_150016_b[0], true, false);
	    }

	  
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityGlowDoor();
	}
}
