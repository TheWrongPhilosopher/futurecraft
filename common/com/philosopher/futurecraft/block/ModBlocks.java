package com.philosopher.futurecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import com.philosopher.futurecraft.core.WorldHandler;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	private static WorldHandler.OreCluster crysCluster;
	private static WorldHandler.OreCluster copperCluster;
	private static WorldHandler.OreCluster tinCluster;
	private static WorldHandler.OreCluster alumCluster;
	private static WorldHandler.OreCluster titanCluster;
	
	public static Block crysOre;
	public static Block copperOre;
	public static Block tinOre;
	public static Block alumOre;
	public static Block titanOre;
	public static Block hsFurnaceIdle;
	public static Block hsFurnaceActive;
	public static Block machineHousing;
	
	public static void init() {
		crysOre = new BlockOreFC(200, 0, Material.rock).setHardness(3.0F).setBlockName("crysOre");
		copperOre = new BlockOreFC(201, 1, Material.rock).setHardness(3.0F).setBlockName("copperOre");
		tinOre = new BlockOreFC(202, 2, Material.rock).setHardness(3.0F).setBlockName("tinOre");
		alumOre = new BlockOreFC(203, 3, Material.rock).setHardness(3.0F).setBlockName("alumOre");
		titanOre = new BlockOreFC(204, 4, Material.rock).setHardness(3.0f).setBlockName("titanOre");
		machineHousing = new BlockOreFC(205, 5, Material.iron).setHardness(3.5F).setBlockName("machineHousing");
		hsFurnaceIdle = new BlockHSFurnace(206, 6, Material.iron, false);
		hsFurnaceActive = new BlockHSFurnace(207, 6, Material.iron, true);
		
		LanguageRegistry.addName(crysOre, "Crystalite Ore");
		LanguageRegistry.addName(copperOre, "Copper Ore");
		LanguageRegistry.addName(tinOre, "Tin Ore");
		LanguageRegistry.addName(alumOre, "Aluminum Ore");
		LanguageRegistry.addName(titanOre, "Titanium Ore");
		LanguageRegistry.addName(hsFurnaceIdle, "HighSpeed Furnace");
		LanguageRegistry.addName(hsFurnaceActive, "HighSpeed Furnace");
		
		GameRegistry.registerBlock(crysOre, "crysOre");
		GameRegistry.registerBlock(copperOre, "copperOre");
		GameRegistry.registerBlock(tinOre, "tinOre");
		GameRegistry.registerBlock(alumOre, "alumOre");
		GameRegistry.registerBlock(titanOre, "titanOre");
		GameRegistry.registerBlock(hsFurnaceIdle, "hsFurnaceIdle");
		GameRegistry.registerBlock(hsFurnaceActive, "hsFurnaceActive");
		
		OreDictionary.registerOre("oreCopper", new ItemStack(copperOre));
		OreDictionary.registerOre("oreTin", new ItemStack(tinOre));
		
		MinecraftForge.setBlockHarvestLevel(crysOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(copperOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(tinOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(alumOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(titanOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(hsFurnaceIdle, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(hsFurnaceActive, "pickaxe", 2);
	}
	
	public static void generate() {
		crysCluster = new WorldHandler.OreCluster(0, 64, 9, 7, crysOre.blockID);
		copperCluster = new WorldHandler.OreCluster(16, 64, 7, 6, copperOre.blockID);
		tinCluster = new WorldHandler.OreCluster(16, 64, 7, 6, tinOre.blockID);
		alumCluster = new WorldHandler.OreCluster(0, 32, 6, 5, alumOre.blockID);
		titanCluster = new WorldHandler.OreCluster(0, 32, 6, 5, titanOre.blockID);
		
		WorldHandler.addOreCluster(crysCluster);
		WorldHandler.addOreCluster(copperCluster);
		WorldHandler.addOreCluster(tinCluster);
	}
}
