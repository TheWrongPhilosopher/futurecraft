package com.philosopher.futurecraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileEntityHSFurnace extends TileEntityFC implements IInventory, ISidedInventory {
	
	private ItemStack[] furnaceItems = new ItemStack[2];
	public int cookTime = 0;
	
	@Override
	public int getStartInventorySide(ForgeDirection side) {
		if(side == ForgeDirection.UP) return 0;
		return 1;
	}
	
	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return 1;
	}
	
	@Override
	public int getSizeInventory() {
		return this.furnaceItems.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.furnaceItems[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		if(furnaceItems[slot] != null){
			ItemStack amtTaken;
			
			if(furnaceItems[slot].stackSize <= amt){
				amtTaken = this.furnaceItems[slot];
				this.furnaceItems[slot] = null;
				return amtTaken;
			}
			else
			{
				amtTaken = this.furnaceItems[slot].splitStack(amt);
				if(furnaceItems[slot].stackSize == 0){
					this.furnaceItems[slot] = null;
				}
				return amtTaken;
			}
			
		}
		else
		{
			return null;
		}
		
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null){
			setInventorySlotContents(slot, null);
		}
		return stack;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		furnaceItems[slot] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = getInventoryStackLimit();
		}
	}
	
	public int getCookProgressScaled(int par1)
    {
        return this.cookTime * par1 / 120;
    }
	
	@Override
	public String getInvName() {
		return "fcContainer.hsFurnace";
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	@Override
	public void openChest() {
	}
	
	@Override
	public void closeChest() {
	}
	
	public void updateEntity(){
		boolean workDone = false;
		
		if (this.canSmelt())
        {
            ++this.cookTime;

            if (this.cookTime == 120)
            {
                this.cookTime = 0;
                this.smeltItem();
                workDone = true;
            }
        }
        else
        {
            this.cookTime = 0;
        }

        if (workDone)
        {
        	this.onInventoryChanged();
        }
	}
	
	private boolean canSmelt(){
		if (this.furnaceItems[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItems[0]);
            if (var1 == null) return false;
            if (this.furnaceItems[1] == null) return true;
            if (!this.furnaceItems[1].isItemEqual(var1)) return false;
            int result = furnaceItems[1].stackSize + var1.stackSize;
            return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
        }
	}
	
	public void smeltItem(){
		if (this.canSmelt())
        {
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItems[0]);

            if (this.furnaceItems[1] == null)
            {
                this.furnaceItems[1] = var1.copy();
            }
            else if (this.furnaceItems[1].isItemEqual(var1))
            {
                furnaceItems[1].stackSize += var1.stackSize;
            }

            --this.furnaceItems[0].stackSize;

            if (this.furnaceItems[0].stackSize <= 0)
            {
                this.furnaceItems[0] = null;
            }
        }
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            
            NBTTagList tagList = tagCompound.getTagList("Inventory");
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < furnaceItems.length) {
                            furnaceItems[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                            
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < furnaceItems.length; i++) {
                    ItemStack stack = furnaceItems[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            tagCompound.setTag("Inventory", itemList);
    }
	
}
