package kkaylium.TechliumCraft.gen;

import java.util.LinkedList;
import java.util.Random;

import kkaylium.TechliumCraft.inits.TCInits;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class OverworldBiomeOreGen implements IWorldGenerator{

	public class OreInfo
	{
		public int maxHeight;
		public int minHeight;
		public int maxCluster;
		public int minCluster;
		public int clustersPerChunk;
		public Block oreType;
		public int metaOre;
        public BiomeDictionary.Type spawnBiomeType;
		
		public OreInfo(int MaxHeight, int MinHeight, int MaxCluster, int MinCluster, int ClustersPerChunk, Block OreType, int MetaOre, Type SpawnBiomeType){
			maxHeight = MaxHeight;
			minHeight = MinHeight;
			maxCluster = MaxCluster;
			minCluster = MinCluster;
			clustersPerChunk = ClustersPerChunk;
			oreType = OreType;
			metaOre = MetaOre;
            spawnBiomeType = SpawnBiomeType;
		}
	}
	
	private LinkedList<OreInfo> oreList;
	
	public OverworldBiomeOreGen(){
		oreList = new LinkedList<OreInfo>();
		OreInfo info;
		
		info = new OreInfo(60, 1, 12, 4, 10, TCInits.glowOres, 0, Type.MOUNTAIN); //White
		 oreList.add(info);
        info = new OreInfo(60, 1, 12, 4, 10, TCInits.glowOres, 0, Type.HILLS); //White
        oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 1, Type.MOUNTAIN); //Black
		 oreList.add(info);
        info = new OreInfo(60, 1, 12, 4, 10, TCInits.glowOres, 1, Type.HILLS); //White
        oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 2, Type.DESERT); //Red
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 2, Type.BEACH); //Red
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 3, Type.DESERT); //Orange
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 3, Type.BEACH); //Orange
		 oreList.add(info);
        info = new OreInfo(40, 1, 12, 5, 10, TCInits.glowOres, 3, Type.DESERT); //Orange
        oreList.add(info);
        info = new OreInfo(40, 1, 12, 5, 10, TCInits.glowOres, 3, Type.BEACH); //Orange
        oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 5, Type.JUNGLE); //Lime
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 5, Type.FOREST); //Green
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 6, Type.FOREST); //Green
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 6, Type.SWAMP); //Green
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 7, Type.FROZEN); //Sky
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 7, Type.WATER); //Sky
		 oreList.add(info);
        info = new OreInfo(40, 1, 12, 3, 10, TCInits.glowOres, 7, Type.FROZEN); //Blue
        oreList.add(info);
        info = new OreInfo(40, 1, 12, 3, 10, TCInits.glowOres, 7, Type.WATER); //Blue
        oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 9, Type.PLAINS); //Lilac
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 9, Type.FOREST); //Lilac
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 10, Type.PLAINS); //Purple
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 10, Type.FOREST); //Purple
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 10, Type.PLAINS); //Purple
		 oreList.add(info);
		info = new OreInfo(40, 1, 12, 4, 10, TCInits.glowOres, 11, Type.FOREST); //Pink
		 oreList.add(info);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		 
		 int x,y,z;
         int numOre;
         int numCluster;
         int tcount = 0;
         BiomeGenBase[] biome;
         if (world.provider.dimensionId != 0) {//Only generate in the main world.  
                 return;
         }
         for(OreInfo info: oreList) {
                 tcount++;
                 numCluster = random.nextInt(info.clustersPerChunk);
                 if(numCluster == 0 && info.clustersPerChunk != 0){
                         numCluster = 1;
                 }
                 
                 for(int count = 0; count < numCluster; count++) {
                         biome = BiomeDictionary.getBiomesForType(info.spawnBiomeType);
                         x = random.nextInt(16);
                         z = random.nextInt(16);
                         y = random.nextInt(info.maxHeight - info.minHeight);
                         x = x + (16 * chunkX);
                         z = z + (16 * chunkZ);
                         y = y + info.minHeight;
                         numOre = MathHelper.clamp_int(random.nextInt(info.maxCluster), info.minCluster, info.maxCluster);

                     if(info.spawnBiomeType == null){
                         generateOre(world, random, x, y, z, info.oreType, info.metaOre, numOre);
                     }else{
                     for(int i = 0; i < biome.length; i++)
                     {
                         if(info.spawnBiomeType != null && world.getBiomeGenForCoords(x, z) == biome[i]){
                             generateOre(world, random, x, y, z, info.oreType, info.metaOre, numOre);
                         }
                     }
                     }
                 }
         }
	}
	
	private void generateOre(World world, Random random, int x, int y, int z, Block blockID, int metadata, int ntg) {
		int lx, ly, lz;
		lx = x;
		ly = y;
		lz = z;
		Block id;
		id = world.getBlock(x, y, z);
		int Metadata = metadata;
		if (id != Blocks.stone) {
			return;
		}
		for (int i = 0; i < ntg; i++) {
			id = world.getBlock(lx, ly, lz);

			world.setBlock(lx, ly, lz, blockID, Metadata, 2);
			switch (random.nextInt(3)) {
			case 0:
				lx = lx + (random.nextInt(4) - 2);
				break;
			case 1:
				ly = ly + (random.nextInt(4) - 2);
				break;
			case 2:
				lz = lz + (random.nextInt(4) - 2);
				break;
			}
		}
	}

}
