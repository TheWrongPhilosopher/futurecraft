package com.philosopher.futurecraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

import com.philosopher.futurecraft.tile.TileEntityHSFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerHSFurnace extends Container {
	
	protected TileEntityHSFurnace tileEntity;
	int lastCookTime;
	
	public ContainerHSFurnace(InventoryPlayer inventoryPlayer, TileEntityHSFurnace te){
		tileEntity = te;
		this.addSlotToContainer(new Slot(tileEntity, 0, 56, 35));
		this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntity, 1, 116, 35));
		bindPlayerInventory(inventoryPlayer);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
         for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 9; j++) {
                         addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                         8 + j * 18, 84 + i * 18));
                 }
         }

         for (int i = 0; i < 9; i++) {
                 addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
         }
	}
	
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
    }
	
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastCookTime != this.tileEntity.cookTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
            }
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tileEntity.cookTime = par2;
        }
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            //null checks and checks if the item can be stacked (maxStackSize > 1)
            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    //merges the item into player inventory since its in the tileEntity
                    if (slot < 2) {
                            if (!this.mergeItemStack(stackInSlot, 2, 38, true)) {
                                    return null;
                            }
                    }
                    //places it into the tileEntity is possible since its in the player inventory
                    else if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
                            return null;
                    }

                    if (stackInSlot.stackSize == 0) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }

}
