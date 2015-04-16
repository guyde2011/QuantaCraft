package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import QuantaCraft.main.modMain;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.BlockFluidBase;




public class TileEntityPickStorage extends TileEntity implements ISidedInventory {


		public TileEntityPickStorage() {
			super();
		}
		public ItemStack PlacedBy;
	    public boolean initialized;
		public static double r;
		public boolean adamUp;
		public boolean bucketUp;
        private ItemStack[] inv= new ItemStack[45];
		@Override
		public int[] getAccessibleSlotsFromSide(int par1)
	    {
			int[] allSlots = new int[45];
			for (int i=0;i<45;i++){
				allSlots[i]=i;
			}
			return allSlots;
	    }

		
		 /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
		@Override
	    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	    	return true;
	     
	    }

	    /**
	     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
		@Override
	    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	    return true;
	    }




        @Override
        public int getSizeInventory() {
                return inv.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
                return inv[slot];
        }

       
        @Override
        public void setInventorySlotContents(int slot, ItemStack stack) {
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }              
        }

        @Override
        public ItemStack decrStackSize(int slot, int amt) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        if (stack.stackSize <= amt) {
                                setInventorySlotContents(slot, null);
                        } else {
                                stack = stack.splitStack(amt);
                                if (stack.stackSize == 0) {
                                        setInventorySlotContents(slot, null);
                                }
                        }
                }
                return stack;
        }

        @Override
        public ItemStack getStackInSlotOnClosing(int slot) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        setInventorySlotContents(slot, null);
                }
                return stack;
        }
       
        @Override
        public int getInventoryStackLimit() {
                return 64;
        }

        @Override
        public boolean isUseableByPlayer(EntityPlayer player) {
                return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
        }
       

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
                super.readFromNBT(tagCompound); 
                NBTTagList tagList = tagCompound.getTagList("Inventory",tagCompound.getId());
                for (int i = 0; i < tagList.tagCount(); i++) {
                        NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                        byte slot = tag.getByte("Slot");
                        if (slot >= 0 && slot < inv.length) {
                                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                                
                        }
                }
        }
   
        @Override
        public void writeToNBT(NBTTagCompound tagCompound) { 
                super.writeToNBT(tagCompound);
                NBTTagList itemList = new NBTTagList();
                for (int i = 0; i < inv.length; i++) {
                        ItemStack stack = inv[i];
                        if (stack != null) {
                                NBTTagCompound tag = new NBTTagCompound();
                                tag.setByte("Slot", (byte) i);
                                stack.writeToNBT(tag);
                                itemList.appendTag(tag);
                                
                        }
                }
                tagCompound.setTag("Inventory", itemList);
        }

                @Override

                public void updateEntity() {
        			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
        				return;
        			}
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                	
                	if (!this.initialized && worldObj != null && this.PlacedBy !=null && this.PlacedBy.stackTagCompound !=null) {
                		this.PlacedBy.stackTagCompound.setInteger("x", this.xCoord);
                		this.PlacedBy.stackTagCompound.setInteger("y", this.yCoord);
                		this.PlacedBy.stackTagCompound.setInteger("z", this.zCoord);
                		this.readFromNBT(this.PlacedBy.stackTagCompound);
                	}
                	
                    this.initialized=true;
                    if (!this.worldObj.isRemote) {
                    }
                   
                	}
                   
        }
                @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

				@Override
				public boolean isItemValidForSlot(int i, ItemStack itemstack) {
					if (i > 45) {
						return false;
					} else {
						return true;
					}			
				}


				
			
			@Override
			public String getInventoryName() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public boolean hasCustomInventoryName() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public void openInventory() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void closeInventory() {
				// TODO Auto-generated method stub
				
			}

		}


