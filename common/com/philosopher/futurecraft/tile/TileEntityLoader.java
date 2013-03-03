package com.philosopher.futurecraft.tile;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityLoader {
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityHSFurnace.class, "teHSFurnace");
	}
}
