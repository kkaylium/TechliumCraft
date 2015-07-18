package kkaylium.TechliumCraft.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.TechliumCraft.TechliumCraft;
import kkaylium.TechliumCraft.lib.Misc;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.machines.TileEntityGlowFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGlowFurnace extends BlockContainer implements IMachineBlock{

    @SideOnly(Side.CLIENT)
    protected IIcon top;
    @SideOnly(Side.CLIENT)
    protected IIcon side;

	protected BlockGlowFurnace() {
		super(Material.iron);
		this.setCreativeTab(TechliumCraft.TCTab);
        setBlockName("glowfurnace");
        setBlockTextureName(Strings.MOD_ID + ":glowfurnace");
        setHarvestLevel("pickaxe", 1);
        setHardness(5);
		this.setLightLevel(0.5F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGlowFurnace();
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entity, int par6, float par7, float par8, float par9){
        super.onBlockActivated(world, x, y, z, entity, par6, par7, par8, par9);
        if(entity.isSneaking()) return false;
        else{
            if(world.isRemote){
                TileEntityGlowFurnace logic = (TileEntityGlowFurnace) world.getTileEntity(x, y, z);
                if(logic != null){
                    entity.openGui(TechliumCraft.instance, Misc.glowfurnace_gui, world, x, y ,z);
                }
            }

            return true;
        }
    }

//    @Override
//    public void breakBlock(World world, int x, int y, int z, Block block, int meta){
//        TileEntity te = world.getTileEntity(x, y, z);
//        if(te instanceof TileEntityGlowFurnace){
//            ((TileEntityGlowFurnace)te).dropContents();
//        }
//    }

}
