package kkaylium.mods.TechliumCraft.gen.glowdimension;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;

/**
 * Created by Kayla Marie on 8/11/14.
 */
public class GDChunkProvider implements IChunkProvider {

    private Random rand;
    private NoiseGeneratorOctaves noiceGen1;
    private NoiseGeneratorOctaves noiceGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    /**
     * A NoiseGeneratorOctaves used in generating terrain
     */
    public NoiseGeneratorOctaves noiseGen5;
    /**
     * A NoiseGeneratorOctaves used in generating terrain
     */
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    /**
     * Reference to the World object.
     */
    private World worldObj;
    /**
     * are map structures going to be generated (e.g. strongholds)
     */
    private final boolean enableMapFeatures;
    private WorldType worldType;
    private final double[] field_147434_q;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    /**
     * Holds Stronghold Generator
     */
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    /**
     * Holds Village Generator
     */
    private MapGenVillage villageGenerator = new MapGenVillage();
    /**
     * Holds Mineshaft Generator
     */
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    /**
     * Holds ravine generator
     */
    private MapGenBase ravineGenerator = new MapGenRavine();
    /**
     * The biomes that are used to generate the chunk
     */
    private BiomeGenBase[] biomesForGeneration;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    int[][] field_73219_j = new int[32][32];

    {
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
        strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGenerator, STRONGHOLD);
        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(villageGenerator, VILLAGE);
        mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGenerator, MINESHAFT);
        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
        ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
    }

    public GDChunkProvider(World par1World, long par2, boolean par4) {

        this.worldObj = par1World;
        this.enableMapFeatures = par4;
        this.worldType = par1World.getWorldInfo().getTerrainType();
        this.rand = new Random(par2);
        this.noiceGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiceGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147434_q = new double[825];
        this.parabolicField = new float[25];

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.sqrt_float((float) (j * j + k * k) + 0.2F);
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

        NoiseGenerator[] noiseGens = {noiceGen1, noiceGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6, mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.rand, noiseGens);
        this.noiceGen1 = (NoiseGeneratorOctaves) noiseGens[0];
        this.noiceGen2 = (NoiseGeneratorOctaves) noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves) noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin) noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves) noiseGens[6];
    }

    public void func_147424_a(int x, int z, Block[] blocks) {
        byte b0 = 64;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.func_147423_a(x * 4, 0, z * 4);

        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = this.field_147434_q[k1 + k2];
                    double d2 = this.field_147434_q[l1 + k2];
                    double d3 = this.field_147434_q[i2 + k2];
                    double d4 = this.field_147434_q[j2 + k2];
                    double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int k3 = 0; k3 < 4; ++k3) {
                                if ((d15 += d16) > 0.0D) {
                                    blocks[j3 += short1] = Blocks.stone;
                                } else if (k2 * 8 + l2 <= b0) {
                                    blocks[j3 += short1] = Blocks.water;
                                } else {
                                    blocks[j3 += short1] = null;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, p_147422_1_, p_147422_2_, p_147422_3_, p_147422_5_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        double d0 = 0.03125D;
        this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(p_147422_1_ * 16), (double)(p_147422_2_ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = p_147422_5_[l + k * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.rand, p_147422_3_, p_147422_4_, p_147422_1_ * 16 + k, p_147422_2_ * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
        this.func_147424_a(par1, par2, ablock);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, ablock, abyte, this.biomesForGeneration);
        this.caveGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
        this.ravineGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);

        if (this.enableMapFeatures)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
            this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
        }

        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, par1, par2);
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void func_147423_a(int x, int y, int z)
    {
        double d0 = 684.412D;
        double d1 = 684.412D;
        double d2 = 512.0D;
        double d3 = 512.0D;
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
        this.field_147427_d = this.noiseGen3.generateNoiseOctaves(this.field_147427_d, x, y, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.field_147428_e = this.noiceGen1.generateNoiseOctaves(this.field_147428_e, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.field_147425_f = this.noiceGen2.generateNoiseOctaves(this.field_147425_f, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5D;

        for (int currentX = 0; currentX < 5; ++currentX)
        {
            for (int currentY = 0; currentY < 5; ++currentY)
            {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[currentX + 2 + (currentY + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        BiomeGenBase biomeGen = this.biomesForGeneration[currentX + l1 + 2 + (currentY + i2 + 2) * 10];
                        float f3 = biomeGen.rootHeight;
                        float f4 = biomeGen.heightVariation;

                        if (this.worldType == WorldType.AMPLIFIED && f3 > 0.0F)
                        {
                            f3 = 1.0F + f3 * 2.0F;
                            f4 = 1.0F + f4 * 4.0F;
                        }

                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

                        if (biomeGen.rootHeight > biomegenbase.rootHeight)
                        {
                            f5 /= 2.0F;
                        }

                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double d13 = this.field_147426_g[i1] / 8000.0D;

                if (d13 < 0.0D)
                {
                    d13 = -d13 * 0.3D;
                }

                d13 = d13 * 3.0D - 2.0D;

                if (d13 < 0.0D)
                {
                    d13 /= 2.0D;

                    if (d13 < -1.0D)
                    {
                        d13 = -1.0D;
                    }

                    d13 /= 1.4D;
                    d13 /= 2.0D;
                }
                else
                {
                    if (d13 > 1.0D)
                    {
                        d13 = 1.0D;
                    }

                    d13 /= 8.0D;
                }

                ++i1;
                double d12 = (double)f1;
                double d14 = (double)f;
                d12 += d13 * 0.2D;
                d12 = d12 * 8.5D / 8.0D;
                double d5 = 8.5D + d12 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2)
                {
                    double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / d14;

                    if (d6 < 0.0D)
                    {
                        d6 *= 4.0D;
                    }

                    double d7 = this.field_147428_e[l] / 512.0D;
                    double d8 = this.field_147425_f[l] / 512.0D;
                    double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

                    if (j2 > 29)
                    {
                        double d11 = (double)((float)(j2 - 29) / 3.0F);
                        d10 = d10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider chunkProvider, int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int chunkXBlocks = x * 16;
        int chunkYBlocks = z * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(chunkXBlocks + 16, chunkYBlocks + 16);
        this.rand.setSeed(this.worldObj.getSeed());

        long randX = this.rand.nextLong() / 2L * 2L + 1L;
        long randZ = this.rand.nextLong() / 2L * 2L + 1L;

        this.rand.setSeed((long)x * randX + (long)z * randZ ^ this.worldObj.getSeed());

        boolean hasVillageGenerated = false;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunkProvider, worldObj, rand, x, z, hasVillageGenerated));

        if (this.enableMapFeatures)
        {
            this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, x, z);
            hasVillageGenerated = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, x, z);
            this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, x, z);
            this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, x, z);
        }

        int randBlockX;
        int randBlockY;
        int randBlockZ;

        if (biomegenbase != BiomeGenBase.desert && biomegenbase != BiomeGenBase.desertHills && !hasVillageGenerated && this.rand.nextInt(4) == 0
                && TerrainGen.populate(chunkProvider, worldObj, rand, x, z, hasVillageGenerated, LAKE))
        {
            randBlockX = chunkXBlocks + this.rand.nextInt(16) + 8;
            randBlockY = this.rand.nextInt(256);
            randBlockZ = chunkYBlocks + this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, randBlockX, randBlockY, randBlockZ);
        }

        if (TerrainGen.populate(chunkProvider, worldObj, rand, x, z, hasVillageGenerated, LAVA) && !hasVillageGenerated && this.rand.nextInt(8) == 0)
        {
            randBlockX = chunkXBlocks + this.rand.nextInt(16) + 8;
            randBlockY = this.rand.nextInt(this.rand.nextInt(248) + 8);
            randBlockZ = chunkYBlocks + this.rand.nextInt(16) + 8;

            if (randBlockY < 63 || this.rand.nextInt(10) == 0)
            {
                (new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, randBlockX, randBlockY, randBlockZ);
            }
        }

        boolean doGen = TerrainGen.populate(chunkProvider, worldObj, rand, x, z, hasVillageGenerated, DUNGEON);
        for (randBlockX = 0; doGen && randBlockX < 8; ++randBlockX)
        {
            randBlockY = chunkXBlocks + this.rand.nextInt(16) + 8;
            randBlockZ = this.rand.nextInt(256);
            int j2 = chunkYBlocks + this.rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(this.worldObj, this.rand, randBlockY, randBlockZ, j2);
        }

        biomegenbase.decorate(this.worldObj, this.rand, chunkXBlocks, chunkYBlocks);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, chunkXBlocks + 8, chunkYBlocks + 8, 16, 16, this.rand);
        chunkXBlocks += 8;
        chunkYBlocks += 8;

        doGen = TerrainGen.populate(chunkProvider, worldObj, rand, x, z, hasVillageGenerated, ICE);
        for (randBlockX = 0; doGen && randBlockX < 16; ++randBlockX)
        {
            for (randBlockY = 0; randBlockY < 16; ++randBlockY)
            {
                randBlockZ = this.worldObj.getPrecipitationHeight(chunkXBlocks + randBlockX, chunkYBlocks + randBlockY);

                if (this.worldObj.isBlockFreezable(randBlockX + chunkXBlocks, randBlockZ - 1, randBlockY + chunkYBlocks))
                {
                    this.worldObj.setBlock(randBlockX + chunkXBlocks, randBlockZ - 1, randBlockY + chunkYBlocks, Blocks.ice, 0, 2);
                }

                if (this.worldObj.func_147478_e(randBlockX + chunkXBlocks, randBlockZ, randBlockY + chunkYBlocks, true))
                {
                    this.worldObj.setBlock(randBlockX + chunkXBlocks, randBlockZ, randBlockY + chunkYBlocks, Blocks.snow_layer, 0, 2);
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunkProvider, worldObj, rand, x, z, hasVillageGenerated));

        BlockFalling.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean saveMethod, IProgressUpdate progress)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    @SuppressWarnings("rawtypes")
    public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z)
    {
        BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords(x, z);
        return creatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(x, y, z) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : currentBiome.getSpawnableList(creatureType);
    }

     public ChunkPosition func_147416_a(World world, String genType, int x, int y, int z)
    {
        return genType.equals("Stronghold") && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(world, x, y, z) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int x, int z)
    {
        if (this.enableMapFeatures)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, x, z, (Block[])null);
            this.villageGenerator.func_151539_a(this, this.worldObj, x, z, (Block[])null);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, x, z, (Block[])null);
            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, x, z, (Block[])null);
        }
    }
}
