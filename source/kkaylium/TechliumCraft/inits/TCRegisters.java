package kkaylium.TechliumCraft.inits;

import kkaylium.TechliumCraft.blocks.BlockDarkAir;
import kkaylium.TechliumCraft.blocks.dark.BlockDarkBlock;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowBlock;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowDirt;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowDoor;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowFence;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowGlass;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowLeaves;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowLog;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowOre;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowPlanks;
import kkaylium.TechliumCraft.blocks.glow.BlockGlowStone;
import kkaylium.TechliumCraft.blocks.glow.ItemBlockGlowDirt;
import kkaylium.TechliumCraft.blocks.glow.ItemBlockGlowOre;
import kkaylium.TechliumCraft.blocks.glow.ItemBlockGlowStone;
import kkaylium.TechliumCraft.blocks.liquids.TCFluid;
import kkaylium.TechliumCraft.items.ItemGlowDoor;
import kkaylium.TechliumCraft.items.ItemGlowSticks;
import kkaylium.TechliumCraft.items.ItemSmallCrystal;
import kkaylium.TechliumCraft.lib.Strings;
import kkaylium.TechliumCraft.tileentities.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCRegisters {
	
	public static void itemRegisters(){
		TCInits.glowCrystals = new ItemSmallCrystal("GC");
			GameRegistry.registerItem(TCInits.glowCrystals, Strings.GLOW_CRYSTALS_KEY);
		TCInits.glowSticks = new ItemGlowSticks().setUnlocalizedName(Strings.GLOW_STICKS_KEY);
			GameRegistry.registerItem(TCInits.glowSticks, Strings.GLOW_STICKS_KEY);
		TCInits.glowDoorItem = new ItemGlowDoor().setUnlocalizedName(Strings.GLOW_DOOR_ITEM_KEY);
			GameRegistry.registerItem(TCInits.glowDoorItem, Strings.GLOW_DOOR_ITEM_KEY);
			
		TCInits.darkCrystals = new ItemSmallCrystal("DC");
			GameRegistry.registerItem(TCInits.darkCrystals, Strings.DARK_CRYSTALS_KEY);
	}
	
	public static void blockRegisters(){
		TCInits.glowOres = new BlockGlowOre().setBlockName(Strings.GLOW_ORE_KEY);
			GameRegistry.registerBlock(TCInits.glowOres, ItemBlockGlowOre.class, Strings.GLOW_ORE_KEY);
		TCInits.glowDirt = new BlockGlowDirt().setBlockName(Strings.GLOW_DIRT_KEY);
			GameRegistry.registerBlock(TCInits.glowDirt, ItemBlockGlowDirt.class, Strings.GLOW_DIRT_KEY);
		TCInits.glowStone = new BlockGlowStone().setBlockName(Strings.GLOW_STONE_KEY);
			GameRegistry.registerBlock(TCInits.glowStone, ItemBlockGlowStone.class, Strings.GLOW_STONE_KEY);


		TCInits.glowLog = new BlockGlowLog().setBlockName(Strings.GLOW_LOG_KEY);
			GameRegistry.registerBlock(TCInits.glowLog, Strings.GLOW_LOG_KEY);
		TCInits.glowLeaves = new BlockGlowLeaves().setBlockName(Strings.GLOW_LEAVES_KEY);
			GameRegistry.registerBlock(TCInits.glowLeaves, Strings.GLOW_LEAVES_KEY);
		//TCInits.glowSapling = new BlockGlowSapling();
			//Register Later
		
		TCInits.glowBlock = new BlockGlowBlock().setBlockName(Strings.GLOW_BLOCK_KEY);
			GameRegistry.registerBlock(TCInits.glowBlock, Strings.GLOW_BLOCK_KEY);
		TCInits.glowGlass = new BlockGlowGlass("glass", false).setBlockName(Strings.GLOW_GLASS_KEY);
			GameRegistry.registerBlock(TCInits.glowGlass, Strings.GLOW_GLASS_KEY);
		TCInits.glowPlanks = new BlockGlowPlanks().setBlockName(Strings.GLOW_PLANKS_KEY);
			GameRegistry.registerBlock(TCInits.glowPlanks, Strings.GLOW_PLANKS_KEY);
		TCInits.glowFence = new BlockGlowFence(Strings.GLOW_FENCE_KEY).setBlockName(Strings.GLOW_FENCE_KEY);
			GameRegistry.registerBlock(TCInits.glowFence, Strings.GLOW_FENCE_KEY);
		TCInits.glowDoor = new BlockGlowDoor().setBlockName(Strings.GLOW_DOOR_KEY);
			GameRegistry.registerBlock(TCInits.glowDoor, Strings.GLOW_DOOR_KEY);
			
		TCInits.darkBlock = new BlockDarkBlock().setBlockName(Strings.DARK_BLOCK_KEY);
			GameRegistry.registerBlock(TCInits.darkBlock, Strings.DARK_BLOCK_KEY);
			
	}
	
	public static void liquidRegisters(){
		TCInits.plainSerumFluid = new Fluid(Strings.PLAIN_SERUM_KEY);
			FluidRegistry.registerFluid(TCInits.plainSerumFluid);
		TCInits.plainSerum = new TCFluid(TCInits.plainSerumFluid, Material.water, Strings.PLAIN_SERUM_KEY).setBlockName(Strings.PLAIN_SERUM_KEY);
			GameRegistry.registerBlock(TCInits.plainSerum, Strings.PLAIN_SERUM_KEY);
			TCInits.plainSerumFluid.setUnlocalizedName(Strings.PLAIN_SERUM_KEY);
			
		TCInits.dilutionLiquidFluid = new Fluid(Strings.DILUTION_LIQUID_KEY);
			FluidRegistry.registerFluid(TCInits.dilutionLiquidFluid);
		TCInits.dilutionLiquid = new TCFluid(TCInits.dilutionLiquidFluid, Material.water, Strings.DILUTION_LIQUID_KEY).setBlockName(Strings.DILUTION_LIQUID_KEY);
			GameRegistry.registerBlock(TCInits.dilutionLiquid, Strings.DILUTION_LIQUID_KEY);
			TCInits.dilutionLiquidFluid.setUnlocalizedName(Strings.DILUTION_LIQUID_KEY);
	}

	public static void machineRegisters(){
		
	}
	
	public static void tileentityRegisters(){
		GameRegistry.registerTileEntity(TileEntityGlowBlock.class, Strings.TE_GLOW_BLOCK_KEY);
		GameRegistry.registerTileEntity(TileEntityGlowGlass.class, Strings.TE_GLOW_GLASS_KEY);
		GameRegistry.registerTileEntity(TileEntityGlowPlanks.class, Strings.TE_GLOW_PLANKS_KEY);
		GameRegistry.registerTileEntity(TileEntityGlowFence.class, Strings.TE_GLOW_FENCE_KEY);
		GameRegistry.registerTileEntity(TileEntityGlowDoor.class, Strings.TE_GLOW_DOOR_KEY);
        GameRegistry.registerTileEntity(TileEntityGlowLog.class, "GlowLogTE");
        GameRegistry.registerTileEntity(TileEntityGlowLeaves.class, "GlowLeavesTE");
		
		GameRegistry.registerTileEntity(TileEntityDarkBlock.class, Strings.TE_DARK_BLOCK_KEY);
	}
	
	public static void biomeRegisters(){
//		TCInits.glowForest = new BiomeGenGlowForest(50).setColor(00000).setBiomeName("Glow Forest").setHeight(Misc.height_Default).setDisableRain();
//			BiomeManager.addSpawnBiome(TCInits.glowForest);
	}
		
	public static void miscRegisters(){
		TCInits.darkAir = new BlockDarkAir().setBlockName("darkAir");
			GameRegistry.registerBlock(TCInits.darkAir, "darkAir");
			
		//TCInits.dilutionLiquidBucket = new DilutionLiquidBucket(TCInits.dilutionLiquid).setUnlocalizedName("dilutionBucket");
			//GameRegistry.registerItem(TCInits.dilutionLiquidBucket, "dilutionLiquidBucket");
			
		//FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(Strings.PLAIN_SERUM_KEY, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(TCInits.dilutionLiquidBucket), new ItemStack(Items.bucket));
		//FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(Strings.DILUTION_LIQUID_KEY, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(TCInits.dilutionLiquidBucket), new ItemStack(Items.bucket));
	}
}
