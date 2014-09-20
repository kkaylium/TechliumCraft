package net.kkaylium.mods.TechliumCraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.kkaylium.mods.TechliumCraft.TechliumCraft;
import net.kkaylium.mods.TechliumCraft.init.TCInits;
import net.kkaylium.mods.TechliumCraft.lib.ModInfo;
import net.kkaylium.mods.TechliumCraft.lib.TCNames;
import net.kkaylium.mods.TechliumCraft.tileentities.TEDarkColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 8/7/14.
 */
public class BlockDarkLog extends BlockLog implements ITileEntityProvider {

    public int[] blockColor = new int[]{0xE3E3E3, 0x252526, 0x941313, 0xC96208, 0xDBC232, 0x00D60F, 0x065C1C, 0x31AEB5, 0x07128C, 0xA25ECC, 0x5A078C, 0xC94099, 0xEBF2FA};

    @SideOnly(Side.CLIENT)
    protected IIcon topIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon sideIcon;

    public BlockDarkLog(){
        super();
        this.setCreativeTab(TechliumCraft.GGTab);
        this.setBlockName(TCNames.darkLogName);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if(!world.isRemote){
            TileEntity te = world.getTileEntity(x, y, z);
            ItemStack item = player.inventory.getCurrentItem();
            if(te instanceof TEDarkColor && ((TEDarkColor) te).color == 12){
                if(item != null){
                    if(item.isItemEqual(new ItemStack(TCInits.darkCrystal_WHITE)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_BLACK)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_RED)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_ORANGE)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_YELLOW)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_LIME)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_GREEN)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_SKY)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_BLUE)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_LILAC)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_PURPLE)) || item.isItemEqual(new ItemStack(TCInits.darkCrystal_PINK))){
                        ((TEDarkColor) te).setColor(item.getItem());
                        world.setBlockMetadataWithNotify(x, y, z, 1, 0);
                        this.setLightLevel(1.0F);
                        player.inventory.getCurrentItem().stackSize--;
                        world.markBlockForUpdate(x, y, z);
                        return true;
                    }
                }
            }
            return false;
        }else{
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z) {
        int crystalColor;
        TEDarkColor te = (TEDarkColor) iBlockAccess.getTileEntity(x, y, z);
        switch(te.color){
            case 0:
                crystalColor = te.color;
                break;
            case 1:
                crystalColor = te.color;
                break;
            case 2:
                crystalColor = te.color;
                break;
            case 3:
                crystalColor = te.color;
                break;
            case 4:
                crystalColor = te.color;
                break;
            case 5:
                crystalColor = te.color;
                break;
            case 6:
                crystalColor = te.color;
                break;
            case 7:
                crystalColor = te.color;
                break;
            case 8:
                crystalColor = te.color;
                break;
            case 9:
                crystalColor = te.color;
                break;
            case 10:
                crystalColor = te.color;
                break;
            case 11:
                crystalColor = te.color;
                break;
            case 12:
                crystalColor = te.color;
                break;
            default:
                crystalColor = te.color;
                break;
        }
        return blockColor[crystalColor];
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6){
        TileEntity te = world.getTileEntity(x, y, z);
        EntityItem entityCrystal;
        if(te instanceof TEDarkColor && ((TEDarkColor) te).color != 12){
            switch(((TEDarkColor) te).color){
                case 0:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_WHITE));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 1:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_BLACK));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 2:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_RED));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 3:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_ORANGE));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 4:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_YELLOW));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 5:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_LIME));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 6:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_GREEN));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 7:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_SKY));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 8:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_BLUE));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 9:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_LILAC));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 10:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_PURPLE));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 11:
                    entityCrystal = new EntityItem(world, (double)(x), (double)(y), (double)(z), new ItemStack(TCInits.darkCrystal_PINK));
                    world.spawnEntityInWorld(entityCrystal);
                    break;
                case 12:
                    break;
                default:
                    break;
            }
        }
        world.removeTileEntity(x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        blockIcon = iconregister.registerIcon(ModInfo.MOD_ID + ":" + "darkLog_side");
        sideIcon = iconregister.registerIcon(ModInfo.MOD_ID + ":" + "darkLog_side");
        topIcon = iconregister.registerIcon(ModInfo.MOD_ID + ":" + "darkLog_top");
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_)
    {
        return this.sideIcon;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return this.topIcon;
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
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEDarkColor();
    }
}