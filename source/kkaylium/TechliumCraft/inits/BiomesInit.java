package kkaylium.TechliumCraft.inits;

//import kkaylium.TechliumCraft.gen.glowLand.biomes.BiomeGenGlowBiome;
//import kkaylium.TechliumCraft.lib.Reference;
import kkaylium.TechliumCraft.gen.biomes.GlowPlainsBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomesInit {

    public static BiomeGenBase glowPlainsRED = (new GlowPlainsBiome(42, 0)).setBiomeName("Glow Biome: Red");
    public static BiomeGenBase glowPlainsORANGE = (new GlowPlainsBiome(43, 1)).setBiomeName("Glow Biome: Orange");

    public static void biomesInit(){

        BiomeManager.warmBiomes.add(new BiomeManager.BiomeEntry(glowPlainsRED, 100));
        BiomeManager.addSpawnBiome(glowPlainsRED);
        WorldChunkManager.allowedBiomes.add(glowPlainsRED);
        BiomeDictionary.registerBiomeType(glowPlainsRED, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL);

        BiomeManager.warmBiomes.add(new BiomeManager.BiomeEntry(glowPlainsORANGE, 100));
        BiomeManager.addSpawnBiome(glowPlainsORANGE);
        WorldChunkManager.allowedBiomes.add(glowPlainsORANGE);
        BiomeDictionary.registerBiomeType(glowPlainsORANGE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL);

    }
}
