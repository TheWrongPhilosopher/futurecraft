package com.philosopher.futurecraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.philosopher.futurecraft.Futurecraft;
import com.philosopher.futurecraft.lib.References;
import com.philosopher.futurecraft.tile.TileEntityHSFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHSFurnace extends BlockContainer{
	
	private final boolean isActive = false;
	private static boolean keepInv = false;
	
	protected BlockHSFurnace(int id, int tex, Material mat, boolean active) {
		super(id, tex, mat);
		setHardness(3.0F);
		setResistance(5.0F);
		setBlockName("hsFurnace");
		setCreativeTab(Futurecraft.fcBlockTab);
	}
	
	public String getTextureFile(){
		return References.BLOCKSPRITES;
	}
	
	public void onBlockAdded(World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 2;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 5;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
        }
    }
	
	@SideOnly(Side.CLIENT)
	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
        	int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        	
        	return par5 != var6 ? this.blockIndexInTexture - 1 : (this.isActive ? this.blockIndexInTexture + 1 : this.blockIndexInTexture);
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
			int a, float b, float c, float d){
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity == null || player.isSneaking()){
			return false;
		}
		player.openGui(Futurecraft.instance, 0, world, x, y, z);
		return true;
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
        }
    }
	
	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
        keepInv = true;

        if (par0)
        {
            par1World.setBlockWithNotify(par2, par3, par4, ModBlocks.hsFurnaceActive.blockID);
        }
        else
        {
            par1World.setBlockWithNotify(par2, par3, par4, ModBlocks.hsFurnaceIdle.blockID);
        }
        
        keepInv = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var5);

        if (var6 != null)
        {
            var6.validate();
            par1World.setBlockTileEntity(par2, par3, par4, var6);
        }
    }
	
	 @Override
     public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
         if(!keepInv){    
        	 dropItems(world, x, y, z);
             super.breakBlock(world, x, y, z, par5, par6);
 
         }
         super.breakBlock(world, x, y, z, par5, par6);
	 }
     private void dropItems(World world, int x, int y, int z){
             Random rand = new Random();

             TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
             if (!(tileEntity instanceof IInventory)) {
                     return;
             }
             IInventory inventory = (IInventory) tileEntity;

             for (int i = 0; i < inventory.getSizeInventory(); i++) {
                     ItemStack item = inventory.getStackInSlot(i);

                     if (item != null && item.stackSize > 0) {
                             float rx = rand.nextFloat() * 0.8F + 0.1F;
                             float ry = rand.nextFloat() * 0.8F + 0.1F;
                             float rz = rand.nextFloat() * 0.8F + 0.1F;

                             EntityItem entityItem = new EntityItem(world,
                                             x + rx, y + ry, z + rz,
                                             new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
                             float factor = 0.05F;
                             entityItem.motionX = rand.nextGaussian() * factor;
                             entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                             entityItem.motionZ = rand.nextGaussian() * factor;
                             world.spawnEntityInWorld(entityItem);
                             item.stackSize = 0;
                     }
             }
     }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityHSFurnace();
	}
	
	
	
	
	
	
}
