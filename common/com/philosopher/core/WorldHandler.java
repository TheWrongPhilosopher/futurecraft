package com.philosopher.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;



public class WorldHandler implements IWorldGenerator {
	
	//Sets up an array to hold all the ores
	
	private static List oreList = new ArrayList();
	public static WorldHandler instance = new WorldHandler();
	public static void addOreCluster(OreCluster cluster){
		oreList.add(cluster);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		//Checks the dimension its generating so you can put ores there
		
		switch(world.provider.dimensionId) { 
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		
		}
	}
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ){
		
	}
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ){
		
		/*
		 *Gets the size of the array that contains our ores, then
		 *gets the ore at each slot in the array and generates it
		 *how it was registered  
		 */
		
		for (int i = 0; i < oreList.size(); i++){
			
			OreCluster cluster = (OreCluster)oreList.get(i);
			
			for (int j = 0; j < cluster.genRarity; j++){
				int randPosX = chunkX + random.nextInt(16);
				int randPosY = random.nextInt(cluster.maxY - cluster.minY);
				int randPosZ = chunkZ + random.nextInt(16);
				(new WorldGenMinable(cluster.oreID, cluster.genSize)).generate(world, random, randPosX, randPosY, randPosZ);
			}
		}
	}
	
	private void generateNether(World world, Random random, int chunkX, int chunkZ){
		
	}
	
	public static class OreCluster //Registers the ore and its properties
	{
		int minY;
		int maxY;
		int genRarity;
		int genSize;
		int oreID;
		public OreCluster(int yMin, int yMax, int rarity, int nodeSize, int id){
			this.minY = yMin;
			this.maxY = yMax;
			this.genRarity = rarity;
			this.genSize = nodeSize;
			this.oreID = id;
		}
		
	}
	
}