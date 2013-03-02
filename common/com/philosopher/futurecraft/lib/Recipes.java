package com.philosopher.futurecraft.lib;

import net.minecraft.item.ItemStack;

import com.philosopher.futurecraft.block.ModBlocks;
import com.philosopher.futurecraft.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	
	public static ItemStack copperStack;
	public static ItemStack tinStack;
	public static ItemStack crysStack;
	public static ItemStack alumStack;
	public static ItemStack titanStack;
	
	public static void init(){
		copperStack = new ItemStack(ModItems.copperIngot);
		tinStack = new ItemStack(ModItems.tinIngot);
		crysStack = new ItemStack(ModItems.crystalite);
		alumStack = new ItemStack(ModItems.alumIngot);
		titanStack = new ItemStack(ModItems.titanIngot);
		
		GameRegistry.addSmelting(ModBlocks.copperOre.blockID, copperStack, 0.7F);
		GameRegistry.addSmelting(ModBlocks.tinOre.blockID, tinStack, 0.7F);
		GameRegistry.addSmelting(ModBlocks.crysOre.blockID, crysStack, 0.7F);
		GameRegistry.addSmelting(ModBlocks.alumOre.blockID, alumStack, 0.7F);
		GameRegistry.addSmelting(ModBlocks.titanOre.blockID, titanStack, 0.7F);
	}
	
	
	
	
	
}
