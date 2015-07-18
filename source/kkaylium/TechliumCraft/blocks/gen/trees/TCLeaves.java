package kkaylium.TechliumCraft.blocks.gen.trees;

import java.util.ArrayList;
import java.util.Random;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowBlock;
import kkaylium.TechliumCraft.tileentities.TileEntityGlowLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCLeaves extends BlockLeavesBase implements IShearable, ITileEntityProvider{

    public int[] iconColor = new int[]{0xFFFFFF, 0x363638, 0xB81A1A, 0xF0760C, 0xF2D94B, 0x21ED2F, 0x097A25, 0x4CECF5, 0x0010C4, 0xC977FC, 0x6F05B0, 0xFF57C4, 0xEBF2FA};

	public TCLeaves() {
		super(Material.leaves, false);
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setStepSound(soundTypeGrass);
		this.setHardness(0.2F);
		this.setLightLevel(0.1F);
	}

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        int color = 12;
        TileEntityGlowLeaves tile = (TileEntityGlowLeaves) p_149720_1_.getTileEntity(p_149720_2_, p_149720_3_, p_149720_4_);
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
            if (t instanceof TileEntityGlowLeaves && ((TileEntityGlowLeaves)t).color == 12)
            {
                if (player.inventory.getCurrentItem() != null)
                {
                    if ((player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 1)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 2)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 3)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 4)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 5)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 6)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 7)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 8)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 9)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 10)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 11)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 12)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 13))))
                    {
                        ((TileEntityGlowLeaves) t).getCrystalUsed(player.inventory.getCurrentItem().getItemDamage());
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


    public boolean isOpaqueCube() {
		return false;
	}

	public Block itemDropped(int par1, Random rand, int par3) {
		if (rand.nextInt(3) == 2) {
			return TCInits.glowSapling;
		}
		return null;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityGlowLeaves();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Strings.MOD_ID + ":glowLeaves");
	}
	   
	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
	}

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6){
        TileEntity t = par1World.getTileEntity(par2, par3, par4);
        EntityItem entityCrystal = new EntityItem(par1World, (double)(par2), (double)(par3), (double)(par4), new ItemStack(TCInits.glowCrystals, 1, ((TileEntityGlowLeaves)t).color));
        if (t instanceof TileEntityGlowLeaves && ((TileEntityGlowLeaves)t).color != 12){
            par1World.spawnEntityInWorld(entityCrystal);
        }
        par1World.removeTileEntity(par2, par3, par4);
    }
}
