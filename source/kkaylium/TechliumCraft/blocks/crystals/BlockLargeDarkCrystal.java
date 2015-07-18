package kkaylium.TechliumCraft.blocks.crystals;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 7/19/14.
 */
public class BlockLargeDarkCrystal extends BlockContainer{

    public BlockLargeDarkCrystal(){
        super(Material.glass);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
