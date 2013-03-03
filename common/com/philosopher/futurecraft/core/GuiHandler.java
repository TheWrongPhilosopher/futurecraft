package com.philosopher.futurecraft.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.philosopher.futurecraft.container.ContainerHSFurnace;
import com.philosopher.futurecraft.gui.GuiHSFurnace;
import com.philosopher.futurecraft.tile.TileEntityHSFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityHSFurnace){
			return new ContainerHSFurnace(player.inventory, (TileEntityHSFurnace) tileEntity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityHSFurnace){
			return new GuiHSFurnace(player.inventory, (TileEntityHSFurnace) tileEntity);
		}
		return null;
	}

}
