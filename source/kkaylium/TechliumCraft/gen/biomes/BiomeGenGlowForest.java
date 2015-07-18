package kkaylium.TechliumCraft.gen.biomes;

import java.util.Random;

import kkaylium.TechliumCraft.gen.WorldGenGlowTree;
import kkaylium.TechliumCraft.inits.TCInits;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenGlowForest extends BiomeGenBase{
	
public WorldGenMinable theWorldGenerator;
	
	public BiomeGenGlowForest(int par1)
    {
        super(par1);
        this.topBlock = TCInits.glowDirt;
        this.theBiomeDecorator.treesPerChunk = 18;
        this.theBiomeDecorator.flowersPerChunk = 58;
        this.theBiomeDecorator.grassPerChunk = 24;
        this.theBiomeDecorator.mushroomsPerChunk = 0;
        this.theBiomeDecorator.bigMushroomsPerChunk = 0;
        this.waterColorMultiplier = 15745542;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityBat.class, 8, 4, 8));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityZombie.class, 2, 4, 8));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 5, 5, 5));
        //this.theWorldGenerator = new WorldGenMinable(Block.blockLapis.blockID, 25);
        //this.theWorldGenerator = new WorldGenMinable(Block.oreEmerald.blockID, 10);
        
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenGlowTree(true, true);
    }
    
    public int getSkyColorByTemp(float par1)
    {
        return 1;
    }

}
