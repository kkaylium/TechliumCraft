package kkaylium.TechliumCraft.blocks.glow;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowGlass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowGlass extends BlockContainer{

	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[13];
    public int[] iconColor = new int[]{0xE1E4F7, 0x363638, 0xB81A1A, 0xF0760C, 0xF2D94B, 0x21ED2F, 0x097A25, 0x4CECF5, 0x0010C4, 0xC977FC, 0x6F05B0, 0xFF57C4, 0x7A7B80};
    
    protected IIcon[] icons = new IIcon[16];
    private boolean shouldRenderSelectionBox = true;
    protected String folder;
    private int renderPass;
	
	public BlockGlowGlass(String location, boolean hasAlpha) {
		super(Material.glass);
		
        folder = location;
        renderPass = hasAlpha ? 1 : 0;
		
		this.setCreativeTab(TechliumCraft.GGTab);
        this.setLightLevel(0.5F);
        this.setHardness(0.6F);
        this.setResistance(10.0F);
	}
	
	@Override
    public int getRenderBlockPass()
    {
        return renderPass;
    }
	
	public IIcon getIcons ()
    {
        return icons[0];
    }
	
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
    	int color = 12;
    	TileEntityGlowGlass tile = (TileEntityGlowGlass) p_149720_1_.getTileEntity(p_149720_2_, p_149720_3_, p_149720_4_);
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
            if (t instanceof TileEntityGlowGlass && ((TileEntityGlowGlass)t).color == 12)
            {
                if (player.inventory.getCurrentItem() != null)
                {
                    if (player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 1)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 2)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 3)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 4)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 5)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 6)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 7)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 8)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 9)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 10)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 11)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 12)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 13)))
                    {
                        ((TileEntityGlowGlass) t).getCrystalUsed(player.inventory.getCurrentItem().getItemDamage());
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
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
		 icons[0] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass");
	     icons[1] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_1_d");
	     icons[2] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_1_u");
	     icons[3] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_1_l");
	     icons[4] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_1_r");
	     icons[5] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_2_h");
	     icons[6] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_2_v");
	     icons[7] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_2_dl");
	     icons[8] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_2_dr");
	     icons[9] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_2_ul");
	     icons[10] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_2_ur");
	     icons[11] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_3_d");
	     icons[12] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_3_u");
	     icons[13] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_3_l");
	     icons[14] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_3_r");
	     icons[15] = par1IconRegister.registerIcon("techliumcraft:" + folder + "/glass_4");
         blockIcon = par1IconRegister.registerIcon(Strings.MOD_ID + ":GlowGlass");
    }
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6){       
        TileEntity t = par1World.getTileEntity(par2, par3, par4);
        EntityItem entityCrystal = new EntityItem(par1World, (double)(par2), (double)(par3), (double)(par4), new ItemStack(TCInits.glowCrystals, 1, ((TileEntityGlowGlass)t).color));
        if (t instanceof TileEntityGlowGlass && ((TileEntityGlowGlass)t).color != 12){
        	par1World.spawnEntityInWorld(entityCrystal);
        }
        par1World.removeTileEntity(par2, par3, par4);
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

    protected boolean canSilkHarvest()
    {
        return true;
    }
    
    @Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        //Block b1 = BlocksInit.glowGlass;
    	Block b1 = par1IBlockAccess.getBlock(par2, par3, par4);
        if (b1 == this || b1 == Blocks.glass)
        {
            return false;
        }
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        //return !true && b1 == BlocksInit.glowGlass ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityGlowGlass();
	}
	
	public boolean shouldConnectToBlock (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5, int par6)
    {
        return par5 == (Block) this;
    }

    @Override
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 15 ? icons[0] : getConnectedBlockTexture(par1IBlockAccess, par2, par3, par4, par5, icons);
    }
    
    @Override
    public boolean canPlaceTorchOnTop (World world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool (World par1World, int par2, int par3, int par4)
    {
        if (shouldRenderSelectionBox)
        {
            return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
        }
        else
        {
            //return AxisAlignedBB.getAABBPool().getAABB(0D, 0D, 0D, 0D, 0D, 0D);
            return AxisAlignedBB.getBoundingBox(0D, 0D, 0D, 0D, 0D, 0D);
        }
    }
    
    public IIcon getConnectedBlockTexture (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5, IIcon[] icons)
    {
//        if (PHConstruct.connectedTexturesMode == 0)
//        {
//            return icons[0];
//        }

        boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

        switch (par5)
        {
        case 0:
            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4)))
            {
                isOpenDown = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4)))
            {
                isOpenUp = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1)))
            {
                isOpenLeft = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1)))
            {
                isOpenRight = true;
            }

            if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[15];
            }
            else if (isOpenUp && isOpenDown && isOpenLeft)
            {
                return icons[11];
            }
            else if (isOpenUp && isOpenDown && isOpenRight)
            {
                return icons[12];
            }
            else if (isOpenUp && isOpenLeft && isOpenRight)
            {
                return icons[13];
            }
            else if (isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[14];
            }
            else if (isOpenDown && isOpenUp)
            {
                return icons[5];
            }
            else if (isOpenLeft && isOpenRight)
            {
                return icons[6];
            }
            else if (isOpenDown && isOpenLeft)
            {
                return icons[8];
            }
            else if (isOpenDown && isOpenRight)
            {
                return icons[10];
            }
            else if (isOpenUp && isOpenLeft)
            {
                return icons[7];
            }
            else if (isOpenUp && isOpenRight)
            {
                return icons[9];
            }
            else if (isOpenDown)
            {
                return icons[3];
            }
            else if (isOpenUp)
            {
                return icons[4];
            }
            else if (isOpenLeft)
            {
                return icons[2];
            }
            else if (isOpenRight)
            {
                return icons[1];
            }
            break;
        case 1:
            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4)))
            {
                isOpenDown = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4)))
            {
                isOpenUp = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1)))
            {
                isOpenLeft = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1)))
            {
                isOpenRight = true;
            }

            if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[15];
            }
            else if (isOpenUp && isOpenDown && isOpenLeft)
            {
                return icons[11];
            }
            else if (isOpenUp && isOpenDown && isOpenRight)
            {
                return icons[12];
            }
            else if (isOpenUp && isOpenLeft && isOpenRight)
            {
                return icons[13];
            }
            else if (isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[14];
            }
            else if (isOpenDown && isOpenUp)
            {
                return icons[5];
            }
            else if (isOpenLeft && isOpenRight)
            {
                return icons[6];
            }
            else if (isOpenDown && isOpenLeft)
            {
                return icons[8];
            }
            else if (isOpenDown && isOpenRight)
            {
                return icons[10];
            }
            else if (isOpenUp && isOpenLeft)
            {
                return icons[7];
            }
            else if (isOpenUp && isOpenRight)
            {
                return icons[9];
            }
            else if (isOpenDown)
            {
                return icons[3];
            }
            else if (isOpenUp)
            {
                return icons[4];
            }
            else if (isOpenLeft)
            {
                return icons[2];
            }
            else if (isOpenRight)
            {
                return icons[1];
            }
            break;
        case 2:
            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4)))
            {
                isOpenDown = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4)))
            {
                isOpenUp = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4)))
            {
                isOpenLeft = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4)))
            {
                isOpenRight = true;
            }

            if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[15];
            }
            else if (isOpenUp && isOpenDown && isOpenLeft)
            {
                return icons[13];
            }
            else if (isOpenUp && isOpenDown && isOpenRight)
            {
                return icons[14];
            }
            else if (isOpenUp && isOpenLeft && isOpenRight)
            {
                return icons[11];
            }
            else if (isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[12];
            }
            else if (isOpenDown && isOpenUp)
            {
                return icons[6];
            }
            else if (isOpenLeft && isOpenRight)
            {
                return icons[5];
            }
            else if (isOpenDown && isOpenLeft)
            {
                return icons[9];
            }
            else if (isOpenDown && isOpenRight)
            {
                return icons[10];
            }
            else if (isOpenUp && isOpenLeft)
            {
                return icons[7];
            }
            else if (isOpenUp && isOpenRight)
            {
                return icons[8];
            }
            else if (isOpenDown)
            {
                return icons[1];
            }
            else if (isOpenUp)
            {
                return icons[2];
            }
            else if (isOpenLeft)
            {
                return icons[4];
            }
            else if (isOpenRight)
            {
                return icons[3];
            }
            break;
        case 3:
            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4)))
            {
                isOpenDown = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4)))
            {
                isOpenUp = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4)))
            {
                isOpenLeft = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4)))
            {
                isOpenRight = true;
            }

            if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[15];
            }
            else if (isOpenUp && isOpenDown && isOpenLeft)
            {
                return icons[14];
            }
            else if (isOpenUp && isOpenDown && isOpenRight)
            {
                return icons[13];
            }
            else if (isOpenUp && isOpenLeft && isOpenRight)
            {
                return icons[11];
            }
            else if (isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[12];
            }
            else if (isOpenDown && isOpenUp)
            {
                return icons[6];
            }
            else if (isOpenLeft && isOpenRight)
            {
                return icons[5];
            }
            else if (isOpenDown && isOpenLeft)
            {
                return icons[10];
            }
            else if (isOpenDown && isOpenRight)
            {
                return icons[9];
            }
            else if (isOpenUp && isOpenLeft)
            {
                return icons[8];
            }
            else if (isOpenUp && isOpenRight)
            {
                return icons[7];
            }
            else if (isOpenDown)
            {
                return icons[1];
            }
            else if (isOpenUp)
            {
                return icons[2];
            }
            else if (isOpenLeft)
            {
                return icons[3];
            }
            else if (isOpenRight)
            {
                return icons[4];
            }
            break;
        case 4:
            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4)))
            {
                isOpenDown = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4)))
            {
                isOpenUp = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1)))
            {
                isOpenLeft = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1)))
            {
                isOpenRight = true;
            }

            if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[15];
            }
            else if (isOpenUp && isOpenDown && isOpenLeft)
            {
                return icons[14];
            }
            else if (isOpenUp && isOpenDown && isOpenRight)
            {
                return icons[13];
            }
            else if (isOpenUp && isOpenLeft && isOpenRight)
            {
                return icons[11];
            }
            else if (isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[12];
            }
            else if (isOpenDown && isOpenUp)
            {
                return icons[6];
            }
            else if (isOpenLeft && isOpenRight)
            {
                return icons[5];
            }
            else if (isOpenDown && isOpenLeft)
            {
                return icons[10];
            }
            else if (isOpenDown && isOpenRight)
            {
                return icons[9];
            }
            else if (isOpenUp && isOpenLeft)
            {
                return icons[8];
            }
            else if (isOpenUp && isOpenRight)
            {
                return icons[7];
            }
            else if (isOpenDown)
            {
                return icons[1];
            }
            else if (isOpenUp)
            {
                return icons[2];
            }
            else if (isOpenLeft)
            {
                return icons[3];
            }
            else if (isOpenRight)
            {
                return icons[4];
            }
            break;
        case 5:
            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4)))
            {
                isOpenDown = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4)))
            {
                isOpenUp = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1)))
            {
                isOpenLeft = true;
            }

            if (shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1)))
            {
                isOpenRight = true;
            }

            if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[15];
            }
            else if (isOpenUp && isOpenDown && isOpenLeft)
            {
                return icons[13];
            }
            else if (isOpenUp && isOpenDown && isOpenRight)
            {
                return icons[14];
            }
            else if (isOpenUp && isOpenLeft && isOpenRight)
            {
                return icons[11];
            }
            else if (isOpenDown && isOpenLeft && isOpenRight)
            {
                return icons[12];
            }
            else if (isOpenDown && isOpenUp)
            {
                return icons[6];
            }
            else if (isOpenLeft && isOpenRight)
            {
                return icons[5];
            }
            else if (isOpenDown && isOpenLeft)
            {
                return icons[9];
            }
            else if (isOpenDown && isOpenRight)
            {
                return icons[10];
            }
            else if (isOpenUp && isOpenLeft)
            {
                return icons[7];
            }
            else if (isOpenUp && isOpenRight)
            {
                return icons[8];
            }
            else if (isOpenDown)
            {
                return icons[1];
            }
            else if (isOpenUp)
            {
                return icons[2];
            }
            else if (isOpenLeft)
            {
                return icons[4];
            }
            else if (isOpenRight)
            {
                return icons[3];
            }
            break;
        }

        return icons[0];
    }


}
