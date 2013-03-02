package com.philosopher.futurecraft.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ModItems {
	
	public static Item crystalite;
	public static Item copperIngot;
	public static Item tinIngot;
	public static Item alumIngot;
	public static Item titanIngot;
	
	public static void init(){
		
		crystalite = new ItemFCIngot(2200).setIconIndex(0).setItemName("crystalite");
		copperIngot = new ItemFCIngot(2201).setIconIndex(1).setItemName("copperIngot");
		tinIngot = new ItemFCIngot(2202).setIconIndex(2).setItemName("tinIngot");
		alumIngot = new ItemFCIngot(2203).setIconIndex(3).setItemName("alumIngot");
		titanIngot = new ItemFCIngot(2204).setIconIndex(4).setItemName("titanIngot");
		
		LanguageRegistry.addName(crystalite, "Crystalite");
		LanguageRegistry.addName(copperIngot, "Copper");
		LanguageRegistry.addName(tinIngot, "Tin");
		LanguageRegistry.addName(alumIngot, "Aluminum");
		LanguageRegistry.addName(titanIngot, "Titanium");
		
		
	}
	
}
