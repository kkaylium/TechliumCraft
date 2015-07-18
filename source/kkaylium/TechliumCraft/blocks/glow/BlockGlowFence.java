package kkaylium.TechliumCraft.blocks.glow;

import java.util.List;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowFence;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
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
public class BlockGlowFence extends BlockFence implements ITileEntityProvider{

	private final String field_94464_a;
	@SideOnly(Side.CLIENT)
    private IIcon[] textures = new IIcon[13];
    public String[] iconNames = new String[] {"GFWhite", "GFBlack", "GFRed", "GFOrange", "GFYellow", "GFLime", "GFGreen", "GFSky", "GFBlue", "GFLilac", "GFPurple", "GFPink", "GFSpecial", Strings.GLOW_FENCE_KEY};

	public BlockGlowFence(String par2String) 
	{
		super(par2String, Material.glass);
		this.field_94464_a = par2String;
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setLightLevel(0.5F);
		this.setHardness(4.0F);
		this.setResistance(10.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister)
    {
		 for (int i = 0; i < textures.length; i++)
	        {
	            textures[i] = iconregister.registerIcon(Strings.MOD_ID + ":" + iconNames[i]);
	        }
        this.blockIcon = iconregister.registerIcon(Strings.MOD_ID + ":" + this.field_94464_a);
    }
	
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!par1World.isRemote)
        {
            TileEntity t = par1World.getTileEntity(par2, par3, par4);
            if (t instanceof TileEntityGlowFence && ((TileEntityGlowFence)t).color == 14)
            {
                if (player.inventory.getCurrentItem() != null)
                {
                    if ((player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 1)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 2)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 3)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 4)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 5)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 6)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 7)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 8)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 9)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 10)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 11)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 12)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 13))))
                    {
                        ((TileEntityGlowFence) t).getCrystalUsed(player.inventory.getCurrentItem().getItemDamage());
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
                        this.setLightLevel(1.0F);
                        player.inventory.getCurrentItem().stackSize--;
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

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        TileEntity t = par1IBlockAccess.getTileEntity(par2, par3, par4);
        if (t instanceof TileEntityGlowFence)
        {
            if (((TileEntityGlowFence)t).color <= 13) return textures[((TileEntityGlowFence)t).color];
            else return this.blockIcon;
        }
        else
        {
            return this.blockIcon;
        }
    }
	
	@SuppressWarnings({"rawtypes"})
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		boolean flag = this.canConnectFenceTo(par1World, par2, par3, par4 - 1);
		boolean flag1 = this.canConnectFenceTo(par1World, par2, par3, par4 + 1);
		boolean flag2 = this.canConnectFenceTo(par1World, par2 - 1, par3, par4);
		boolean flag3 = this.canConnectFenceTo(par1World, par2 + 1, par3, par4);
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.375F;
		float f3 = 0.625F;

		if (flag)
		{
			f2 = 0.0F;
		}

		if (flag1)
		{
			f3 = 1.0F;
		}

		if (flag || flag1)
		{
			this.setBlockBounds(f, 0.0F, f2, f1, 1.5F, f3);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}

		f2 = 0.375F;
		f3 = 0.625F;

		if (flag2)
		{
			f = 0.0F;
		}

		if (flag3)
		{
			f1 = 1.0F;
		}

		if (flag2 || flag3 || !flag && !flag1)
		{
			this.setBlockBounds(f, 0.0F, f2, f1, 1.5F, f3);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}

		if (flag)
		{
			f2 = 0.0F;
		}

		if(flag1)
		{
			f3 = 1.0F;
		}

		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1BlockAccess, int par2, int par3, int par4)
	{
		boolean flag = this.canConnectFenceTo(par1BlockAccess, par2, par3, par4 - 1);
		boolean flag1 = this.canConnectFenceTo(par1BlockAccess, par2, par3, par4 + 1);
		boolean flag2 = this.canConnectFenceTo(par1BlockAccess, par2 - 1, par3, par4);
		boolean flag3 = this.canConnectFenceTo(par1BlockAccess, par2 + 1, par3, par4);
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.375F;
		float f3 = 0.625F;

		if (flag)
		{
			f2 = 0.0F;
		}

		if (flag1)
		{
			f3 = 1.0F;
		}

		if (flag2)
		{
			f = 0.0F;
		}

		if (flag3)
		{
			f1 = 1.0F;
		}

		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

	public boolean getBlocksMovement(IBlockAccess par1BlockAccess, int par2, int par3, int par4)
	{
		return false;
	}

	public int getRenderType()
	{
		return 11;
	}

	@Override
	public boolean canConnectFenceTo(IBlockAccess par1BlockAccess, int par2, int par3, int par4)
	{
	    Block block = par1BlockAccess.getBlock(par2, par3, par4);
	    return block != this && block != Blocks.fence_gate ? block.isOpaqueCube() && block.renderAsNormalBlock() : true;   
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6){       
        TileEntity t = par1World.getTileEntity(par2, par3, par4);
        EntityItem entityCrystal = new EntityItem(par1World, (double)(par2), (double)(par3), (double)(par4), new ItemStack(TCInits.glowCrystals, 1, ((TileEntityGlowFence)t).color));
        if (t instanceof TileEntityGlowFence && ((TileEntityGlowFence)t).color != 14){
        	par1World.spawnEntityInWorld(entityCrystal);
        }
        par1World.removeTileEntity(par2, par3, par4);
    }

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {	
		return new TileEntityGlowFence();
	}

}
