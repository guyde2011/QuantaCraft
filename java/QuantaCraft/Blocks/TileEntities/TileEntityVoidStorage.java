package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import QuantaCraft.Items.VoidGem;
import QuantaCraft.main.modMain;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.BlockFluidBase;




public class TileEntityVoidStorage extends TileEntity implements ISidedInventory {


		public TileEntityVoidStorage() {
			super();
		}
		public int storage;
		public boolean voiding;
		public int storageMax= 5000;
		public ItemStack storedItem;
	    public boolean initialized;
		public static double r;
		public boolean adamUp;
		public boolean bucketUp;
		
        private ItemStack[] inv= new ItemStack[2];
		@Override
		public int[] getAccessibleSlotsFromSide(int par1)
	    {
			int[] allSlots = new int[2];
			for (int i=0;i<2;i++){
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
                if (tagCompound.hasKey("StorageItem")){
                	storedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound) tagCompound.getTag("StorageItem"));
                } else {
                	storedItem = null;
                }
                if (tagCompound.hasKey("MaxStorage")){
                	this.storageMax = tagCompound.getInteger("MaxStorage");
                	this.storage = tagCompound.getInteger("StorageAmount");
                }
        }

        @Override
        public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
  
        }
		@Override
		public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
		{
				readFromNBT(packet.func_148857_g());
	        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
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
                tagCompound.setInteger("MaxStorage", this.storageMax);
                tagCompound.setInteger("StorageAmount", this.storage);
                NBTTagCompound tag = new NBTTagCompound();
                if (storedItem!=null){
                	storedItem.writeToNBT(tag);
                	tagCompound.setTag("StorageItem", tag);
                }
                
                
                
        }

                @Override

                public void updateEntity() {
        			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
        				return;
        			}
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                	if (!this.initialized && worldObj != null) {
                	
                	}  
                    this.initialized=true;
                  
                   ItemStack stack = this.getStackInSlot(0);
                    if (this.storage<this.storageMax && this.getStackInSlot(0)!=null && this.getStackInSlot(0).stackSize==this.getStackInSlot(0).getItem().getItemStackLimit(stack) &&( storedItem==null || ( stack.getItemDamage()==storedItem.getItemDamage() && stack.getItem()==storedItem.getItem() && ItemStack.areItemStackTagsEqual(stack,storedItem)))){

                    	if (this.getStackInSlot(1)!=null && this.getStackInSlot(1).getItem() instanceof VoidGem){
                    		ItemStack stack1 = this.getStackInSlot(1);
                    		stack1.stackTagCompound.setInteger("VoidCount", stack1.stackTagCompound.getInteger("VoidCount")+1);
                    		this.setInventorySlotContents(1, stack1);
                    	} else {
                    		this.storage++;
                    		if (this.storedItem==null){
                    			this.storedItem=this.getStackInSlot(0);
                    		}
                    	}
                    	this.decrStackSize(0, 1);
                    	
      
                    }
                    if (this.getStackInSlot(0)!=null && this.getStackInSlot(0).stackSize+1<this.getStackInSlot(0).getMaxStackSize()&& this.storage>0 && stack.getItemDamage()==storedItem.getItemDamage() && stack.getItem()==storedItem.getItem() && ItemStack.areItemStackTagsEqual(stack,storedItem)){

                		ItemStack a = storedItem;
                    	a.stackSize = Math.min(storedItem.getMaxStackSize(), storage + this.getStackInSlot(0).stackSize);
                		this.storage-= a.stackSize - this.getStackInSlot(0).stackSize;
                		this.setInventorySlotContents(0, a);
                		if (this.storage==0){
                			this.storedItem=null;
                		}
                	}
                    if (this.getStackInSlot(0)==null && this.storage>0 && this.storedItem!=null){
                    	ItemStack a = storedItem;
                       	a.stackSize = Math.min(storedItem.getMaxStackSize(), storage);
                    	this.storage-= a.stackSize;
                		this.setInventorySlotContents(0, a);
                		
                		if (this.storage==0){
                			this.storedItem=null;
                		}
                	}
                   
}               }
                @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

				@Override
				public boolean isItemValidForSlot(int i, ItemStack itemstack) {
					switch(i){
					case 0:
						return true;
					case 1:
						return itemstack.getItem() instanceof VoidGem;
					default:
						return false;
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


