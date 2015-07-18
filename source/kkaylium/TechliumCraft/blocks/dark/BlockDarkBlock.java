package kkaylium.TechliumCraft.blocks.dark;

import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.TileEntityDarkBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkBlock extends Block implements ITileEntityProvider{

	@SideOnly(Side.CLIENT)
    private IIcon[] textures = new IIcon[13];
    public String[] iconNames = new String[] {"GBWhite", "GBBlack", "GBRed", "GBOrange", "GBYellow", "GBLime", "GBGreen", "GBSky", "GBBlue", "GBLilac", "GBPurple", "GBPink", "GBSpecial", "GBBase"};

	public BlockDarkBlock() {
		super(Material.rock);
		this.setCreativeTab(TechliumCraft.GGTab);
		this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setLightOpacity(1000);
        this.setBlockTextureName(Strings.MOD_ID + ":DBBlack");
	}

	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!par1World.isRemote)
        {
            TileEntity t = par1World.getTileEntity(par2, par3, par4);
            if (t instanceof TileEntityDarkBlock && ((TileEntityDarkBlock)t).color == 14)
            {
                if (player.inventory.getCurrentItem() != null)
                {
                    if ((player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 1)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 2)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 3)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 4)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 5)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 6)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 7)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 8)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 9)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 10)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 11)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 12)) || player.inventory.getCurrentItem().isItemEqual(new ItemStack(TCInits.glowCrystals, 0, 13))))
                    {
                        ((TileEntityDarkBlock) t).getCrystalUsed(player.inventory.getCurrentItem().getItemDamage());
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);                    
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
	    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6){       
	       // TileEntity t = par1World.getTileEntity(par2, par3, par4);
	       // EntityItem entityCrystal = new EntityItem(par1World, (double)(par2), (double)(par3), (double)(par4), new ItemStack(TCInits.glowCrystals, 1, ((TileEntityGlowBlock)t).color));
//	        if (t instanceof TileEntityDarkBlock && ((TileEntityDarkBlock)t).color != 12){
//	        	par1World.spawnEntityInWorld(entityCrystal);
//	        }
	        par1World.removeTileEntity(par2, par3, par4);
	    }
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityDarkBlock();
	}

}
