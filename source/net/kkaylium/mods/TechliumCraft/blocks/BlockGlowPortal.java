package net.kkaylium.mods.TechliumCraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.kkaylium.mods.TechliumCraft.TechliumCraft;
import net.kkaylium.mods.TechliumCraft.gen.glowdimention.GDTeleporter;
import net.kkaylium.mods.TechliumCraft.lib.ModInfo;
import net.kkaylium.mods.TechliumCraft.lib.TCNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 8/10/14.
 */
public class BlockGlowPortal extends Block {

    public BlockGlowPortal(){
        super(Material.portal);
        this.setBlockName(TCNames.glowPortalName);
        this.setLightLevel(1.0F);
        this.setBlockBounds(0.0F, 0.4F, 0.0F, 1.0F, 0.6F, 1.0F);
        this.setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir){
        blockIcon = ir.registerIcon(ModInfo.MOD_ID + ":" + "glowportal");
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean renderAsNormalBlock(){
        return false;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess iBlockAccess, int par2, int par3_, int par4, int par5)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_){
        return AxisAlignedBB.getBoundingBox(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayerMP) {

            EntityPlayerMP thePlayer = (EntityPlayerMP)entity;

            if (thePlayer.dimension != TechliumCraft.glowDimensionID)
            {
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, TechliumCraft.glowDimensionID, new GDTeleporter(thePlayer.mcServer.worldServerForDimension(TechliumCraft.glowDimensionID)));
                //SoundHandling.onEntityPlay("tZTheme", world, entity, 1.0F, 1.0F);
            }
            else
            {
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new GDTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
            }
        }
    }
}
