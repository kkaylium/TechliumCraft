package kkaylium.mods.TechliumCraft.glowglass.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kkaylium.mods.TechliumCraft.TechliumCraft;
import kkaylium.mods.TechliumCraft.lib.TCInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemHoe;

/**
 * Created by kkaylium on 11/6/14.
 */
public class ItemGHoe extends ItemHoe {

    public ItemGHoe(ToolMaterial enumToolMaterial) {
        super(enumToolMaterial);
        this.setCreativeTab(TechliumCraft.GGTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        itemIcon = iconregister.registerIcon(TCInfo.MOD_ID + ":" + "glowhoe");
    }
}