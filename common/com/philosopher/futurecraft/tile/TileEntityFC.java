package com.philosopher.futurecraft.tile;

import com.philosopher.futurecraft.implement.IConductor;

import net.minecraft.tileentity.TileEntity;

public class TileEntityFC extends TileEntity implements IConductor {
	
	int maxMJ;
	boolean canReceive;
	boolean isGenerating;
	
	
	
	@Override
	public boolean isOverloaded(int currentMJ) {
		return currentMJ > maxMJ;
	}

	@Override
	public void setReceiver(){
		this.canReceive = true;
	}

	@Override
	public void setGenerating() {
		this.isGenerating = true;		
	}
	
	
	
}
