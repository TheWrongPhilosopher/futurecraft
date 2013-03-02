package com.philosopher.futurecraft.items;

import com.philosopher.futurecraft.lib.References;

import net.minecraft.item.Item;

public class ItemFC extends Item {

	public ItemFC(int id) {
		super(id);
		maxStackSize = 64;
		setTextureFile(References.ITEMSPRITES);
	}
	
	public String getTextureFile() {
		return References.ITEMSPRITES;
	}
	
}
