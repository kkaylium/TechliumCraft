package kkaylium.mods.TechliumCraft.glowglass.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.mods.TechliumCraft.TechliumCraft;
import kkaylium.mods.TechliumCraft.lib.TCInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Created by kkaylium on 11/4/14.
 */
public class ItemRainbowCrystal extends Item {

    public ItemRainbowCrystal(){
        super();
        this.setCreativeTab(TechliumCraft.GGTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        itemIcon = iconregister.registerIcon(TCInfo.MOD_ID + ":" + "glowcrystal_RAINBOW");
    }
}