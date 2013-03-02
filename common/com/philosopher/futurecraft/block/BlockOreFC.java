package com.philosopher.futurecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.philosopher.futurecraft.Futurecraft;
import com.philosopher.futurecraft.lib.References;

public class BlockOreFC extends Block {

	public BlockOreFC(int id, int tex, Material mat) {
		super(id, tex, mat);
		this.setCreativeTab(Futurecraft.fcBlockTab);
	}
	
	@Override
	public String getTextureFile() {
		return References.BLOCKSPRITES;
	}
}
