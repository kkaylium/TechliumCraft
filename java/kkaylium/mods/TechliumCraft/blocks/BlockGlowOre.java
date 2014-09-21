package kkaylium.mods.TechliumCraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.mods.TechliumCraft.TechliumCraft;
import kkaylium.mods.TechliumCraft.init.TCInits;
import kkaylium.mods.TechliumCraft.lib.TCInfo;
import kkaylium.mods.TechliumCraft.lib.TCNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Kayla Marie on 7/28/14.
 */
public class BlockGlowOre extends Block {

    public int theColor;

    public BlockGlowOre(int color) {
        super(Material.rock);
        this.setCreativeTab(TechliumCraft.GGTab);
        this.setBlockName(TCNames.glowOreName + "_" + TCInfo.COLOR_NAMES[color]);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setLightLevel(0.5F);

        theColor = color;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        blockIcon = iconregister.registerIcon(TCInfo.MOD_ID + ":" + "GO" + TCInfo.COLOR_NAMES_CAPS[theColor]);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        switch (theColor) {
            case 0:
                return TCInits.glowCrystal_WHITE;
            case 1:
                return TCInits.glowCrystal_BLACK;
            case 2:
                return TCInits.glowCrystal_RED;
            case 3:
                return TCInits.glowCrystal_ORANGE;
            case 4:
                return TCInits.glowCrystal_YELLOW;
            case 5:
                return TCInits.glowCrystal_LIME;
            case 6:
                return TCInits.glowCrystal_GREEN;
            case 7:
                return TCInits.glowCrystal_SKY;
            case 8:
                return TCInits.glowCrystal_BLUE;
            case 9:
                return TCInits.glowCrystal_LILAC;
            case 10:
                return TCInits.glowCrystal_PURPLE;
            case 11:
                return TCInits.glowCrystal_PINK;
            default:
                return TCInits.glowCrystal_WHITE;
        }
    }

    @Override
    public int quantityDropped(Random rand){
        return 1 + rand.nextInt(2);
    }

    public int quantityDroppedWithBonus(int par1, Random rand){
        return this.quantityDropped(rand) + rand.nextInt(par1 + 1);
    }

    @Override
    public boolean canSilkHarvest(){
        return true;
    }
}