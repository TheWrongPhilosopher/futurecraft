package com.philosopher.futurecraft.core;

import net.minecraftforge.client.MinecraftForgeClient;

import com.philosopher.futurecraft.lib.References;

public class ClientProxy extends CommonProxy {
	
	public static String ITEMS_PNG = References.ITEMSPRITES;
	public static String BLOCK_PNG = References.BLOCKSPRITES;
	
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
	}
	
}
