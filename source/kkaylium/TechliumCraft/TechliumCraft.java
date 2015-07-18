package kkaylium.TechliumCraft;

import java.util.logging.Logger;

import kkaylium.TechliumCraft.creativetabs.GGTab;
import kkaylium.TechliumCraft.creativetabs.TCTab;
import kkaylium.TechliumCraft.gen.OverworldBiomeOreGen;
import kkaylium.TechliumCraft.handlers.TCBucketHandler;
import kkaylium.TechliumCraft.inits.BiomesInit;
import kkaylium.TechliumCraft.inits.TCInits;
import kkaylium.TechliumCraft.inits.TCRegisters;
import kkaylium.TechliumCraft.lib.Misc;
import kkaylium.TechliumCraft.lib.Reference;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.mobs.darkSlimes.EntityDarkSlime;
import kkaylium.TechliumCraft.mobs.entities.EntityRainbowSlime;
import kkaylium.TechliumCraft.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author kkaylium
 * @version 1.0.1a
 *
 */	
@Mod(name = Strings.MOD_NAME, modid = Strings.MOD_ID, version = "1.0.0a")
public class TechliumCraft {
	
	public static final Logger logger = Logger.getLogger("TechliumCraft");
	
	@Instance("techliumcraft")
	public static TechliumCraft instance;
	
	@SidedProxy(clientSide = "kkaylium.TechliumCraft.proxy.ClientProxy", serverSide = "kkaylium.TechliumCraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs GGTab = new GGTab(CreativeTabs.getNextID(), "Glow Glass");
	public static CreativeTabs TCTab = new TCTab(CreativeTabs.getNextID(), "Techlium Craft");
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		TCBucketHandler.INSTANCE.buckets.put(TCInits.dilutionLiquid, TCInits.dilutionLiquidBucket);
		MinecraftForge.EVENT_BUS.register(TCBucketHandler.INSTANCE);

        TCRegisters.itemRegisters();
        TCRegisters.blockRegisters();
        TCRegisters.liquidRegisters();
        TCRegisters.machineRegisters();
        TCRegisters.miscRegisters();

        TCRegisters.tileentityRegisters();
		//TCRegisters.biomeRegisters();
        BiomesInit.biomesInit();
		//BiomeDictionary.registerAllBiomesAndGenerateEvents();
		
		//MinecraftForge.EVENT_BUS.register(new DropRainbowCrystals());
		   
		//TCBasicRecipes.initBasicRecipes();
		
		//MinecraftForge.EVENT_BUS.register(new EventClass());
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
		//GameRegistry.registerWorldGenerator(new OverworldTreeGenerator(), 5);
		//GameRegistry.registerWorldGenerator(new GlowLandOreGen(), 4);

		//DimensionManager.registerDimension(Misc.DarkLandID, Misc.DarkLandID);
		
		//OreDictionary.registerOre(TCInits.glowOres.getUnlocalizedName(), new ItemStack(TCInits.glowCrystals));


		
		GameRegistry.registerWorldGenerator(new OverworldBiomeOreGen(), 3);
		
		proxy.registerRenderInformation();
		EntityRegistry.registerModEntity(EntityRainbowSlime.class, "RainbowSlime", 2, this, 40, 3, true);
        EntityRegistry.addSpawn(EntityRainbowSlime.class, 30, 2, 10, EnumCreatureType.monster, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.plains);
        EntityRegistry.registerModEntity(EntityDarkSlime.class, "DarkSlime", 3, this, 40, 3, true);
        EntityRegistry.addSpawn(EntityDarkSlime.class, 30, 2, 10, EnumCreatureType.monster, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.plains, BiomeGenBase.desert, BiomeGenBase.desertHills);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Kkaylium says to tell you that TechliumCraft is now initialized");
	}

}
