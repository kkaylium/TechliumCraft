package net.kkaylium.mods.TechliumCraft.gen.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.kkaylium.mods.TechliumCraft.init.TCInits;
import net.kkaylium.mods.TechliumCraft.mobs.EntityRainbowSlime;
import net.kkaylium.mods.TechliumCraft.mobs.EntityRainbowTurtle;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Kayla Marie on 8/8/14.
 */
public class BiomeGlow extends BiomeGenBase {

    private int setColor;

    public BiomeGlow(int id, int color, Block block) {
        super(id);
        setColor = color;
        //Surface And Next Few Blocks, Like Grass and Dirt
        topBlock = block;
        fillerBlock = Blocks.dirt;
        //Find Stone replacement variable...

        theBiomeDecorator.treesPerChunk = 4;
        theBiomeDecorator.flowersPerChunk = 10;
        theBiomeDecorator.grassPerChunk = 3;
        theBiomeDecorator.mushroomsPerChunk = 0;
        theBiomeDecorator.bigMushroomsPerChunk = 0;
        waterColorMultiplier = color;
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();

        spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 5, 5));
        spawnableCreatureList.add(new SpawnListEntry(EntityRainbowSlime.class, 5, 5, 5));
        spawnableCreatureList.add(new SpawnListEntry(EntityRainbowTurtle.class, 5, 5, 5));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
        return setColor;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
        return setColor;
    }
}