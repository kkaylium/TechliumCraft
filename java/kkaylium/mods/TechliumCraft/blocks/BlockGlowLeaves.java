package kkaylium.mods.TechliumCraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.mods.TechliumCraft.TechliumCraft;
import kkaylium.mods.TechliumCraft.lib.TCInfo;
import kkaylium.mods.TechliumCraft.lib.TCNames;
import kkaylium.mods.TechliumCraft.tileentities.TEGlowColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 8/4/14.
 */
public class BlockGlowLeaves extends BlockContainer {

    public BlockGlowLeaves() {
        super(Material.leaves);
        this.setCreativeTab(TechliumCraft.GGTab);
        this.setBlockName(TCNames.glowLeavesName);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        blockIcon = iconregister.registerIcon(TCInfo.MOD_ID + ":" + "glowLeaves");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if(!world.isRemote){
            TileEntity te = world.getTileEntity(x, y, z);
            ItemStack item = player.inventory.getCurrentItem();
            if(te instanceof TEGlowColor && ((TEGlowColor) te).color == TCInfo.GLOW_COLORS.length - 1){
                if(item != null){
                    if(item.isItemEqual(TCInfo.GLOW_CRYSTALS[0])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[1])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[2])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[3])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[4])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[5])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[6])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[7])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[8])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[9])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[10])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[11])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[12])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[13])||item.isItemEqual(TCInfo.GLOW_CRYSTALS[14])){
                        ((TEGlowColor) te).setColor(item.getItem());
                        world.setBlockMetadataWithNotify(x, y, z, 1, 0);
                        this.setLightLevel(1.0F);
                        world.markBlockForUpdate(x, y, z);
                        if(!player.capabilities.isCreativeMode){
                            player.inventory.getCurrentItem().stackSize--;
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }else{
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z) {
        int crystalColor;
        TEGlowColor te = (TEGlowColor) iBlockAccess.getTileEntity(x, y, z);
        crystalColor = te.color;
        return TCInfo.GLOW_COLORS[crystalColor];
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6){
        TileEntity te = world.getTileEntity(x, y, z);
        EntityItem entityCrystal;
        if(te instanceof TEGlowColor && ((TEGlowColor) te).color != TCInfo.GLOW_COLORS.length - 1){
            entityCrystal = new EntityItem(world, (double)x, (double)y, (double)z, TCInfo.GLOW_CRYSTALS[((TEGlowColor) te).color]);
            world.spawnEntityInWorld(entityCrystal);
        }
        world.removeTileEntity(x, y, z);
    }

    @Override
    public int getRenderBlockPass() {
        return 0;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEGlowColor();
    }
}
