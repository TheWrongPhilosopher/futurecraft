package com.philosopher.futurecraft.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabHandler extends CreativeTabs {
	
	private String name;
	
	public CreativeTabHandler(String label) {
		super(label);
		name = label;
	}
	
	@Override
	public ItemStack getIconItemStack() {
		if(name == "fcItemTab"){
			return new ItemStack(Item.eyeOfEnder);
		}
		else return new ItemStack(Item.arrow);
	}
	
}
