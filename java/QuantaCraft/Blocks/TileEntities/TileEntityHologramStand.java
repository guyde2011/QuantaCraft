package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloColorControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloMultiControl;
import QuantaCraft.main.Sides;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.IHologramController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;




public class TileEntityHologramStand extends TileEntity implements ISidedInventory {


		public TileEntityHologramStand() {
			super();
		}
		public boolean bool; 
	    public boolean initialized;
		public static double r;
		public boolean adamUp;
		public boolean bucketUp;
        private ItemStack[] inv= new ItemStack[1];
		@Override
		public int[] getAccessibleSlotsFromSide(int par1)
	    {
			int[] allSlots = new int[1];
			allSlots[0]=1;
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
        	this.markDirty();
                return inv[slot];
        }
        
        public String getColor(){
        	String str = "0x-1";
    		boolean bool1 = false;
    		/* Start of the Render Text Code*/
    		if (this.getWorldObj().getBlock(this.xCoord, this.yCoord-1, this.zCoord)!=null){
    			Block bl = this.getWorldObj().getBlock(this.xCoord, this.yCoord-1, this.zCoord);
    			if (bl.hasTileEntity()){
    				TileEntity ent = this.getWorldObj().getTileEntity(this.xCoord, this.yCoord-1, this.zCoord);
    				if (ent instanceof TileEntityHoloColorControl && ((TileEntityHoloColorControl)ent).color!=null){
    					str = ((TileEntityHoloColorControl)ent).color;
    					if (str.substring(2).equals("")){
    						str = "0x-1";
    					}
    					bool1 = true;
    				}
    			}
    		}
    		if (this.getWorldObj().getBlock(this.xCoord, this.yCoord-1, this.zCoord)!=null){
    			Block bl = this.getWorldObj().getBlock(this.xCoord, this.yCoord-1, this.zCoord);
    			if (bl.hasTileEntity()){
    				TileEntity ent = this.getWorldObj().getTileEntity(this.xCoord, this.yCoord-1, this.zCoord);
    				if (ent instanceof TileEntityHoloMultiControl && ((TileEntityHoloMultiControl)ent).hasColor(Sides.TOP, 0)){
    					str = ((TileEntityHoloMultiControl)ent).getColor(Sides.TOP,0);
    					if (str.substring(2).equals("")){
    						str = "0x-1";
    					}
    					bool1 = true;
    				}
    			}
    		}
    		return str;
        }
        

        
        public boolean renderItem(){
        	Block block = worldObj.getBlock(xCoord, yCoord-1, zCoord);
			if (block == modMain.voidStorage){
				TileEntityVoidStorage store = (TileEntityVoidStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
				if (store.getStackInSlot(0)!=null){
					return true;
				}
			}
			if (block == modMain.pickVoidStorage){
				TileEntityVoidPickStorage store = (TileEntityVoidPickStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
				if (store.getStackInSlot(0)!=null){
					return true;
				}
			}
        	return (this.getStackInSlot(0)!=null && this.getStackInSlot(0).getItem()!=modMain.textPlate);
        }

       
        @Override
        public void setInventorySlotContents(int slot, ItemStack stack) {
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }        
                this.markDirty();
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
		public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
		{
				readFromNBT(packet.func_148857_g());
	        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	        if (this.worldObj.isRemote){
	        	Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
	        }
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
        public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
  
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
                	this.markDirty();		
                		
                	if (!this.initialized && worldObj != null) {
                	
                	}  
                	
                    this.initialized=true;
                   
                   
      bool = this.getStackInSlot(0)!=null;

this.markDirty();
                	}
                @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

				@Override
				public boolean isItemValidForSlot(int i, ItemStack itemstack) {
					if (i < 1) {
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
			public boolean showText(){
				Block block = worldObj.getBlock(xCoord, yCoord-1, zCoord);
				if (block == modMain.voidStorage){
					TileEntityVoidStorage store = (TileEntityVoidStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
					if (store.getStackInSlot(0)!=null){
						return true;
					}
				}
				if (block == modMain.pickVoidStorage){
					TileEntityVoidPickStorage store = (TileEntityVoidPickStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
					if (store.getStackInSlot(0)!=null){
						return true;
					}
				}
				return (this.getStackInSlot(0)!=null && !this.getStackInSlot(0).getDisplayName().equals("noName"));
			}
			
			public String getText(){
				Block block = worldObj.getBlock(xCoord, yCoord-1, zCoord);
				if (block == modMain.voidStorage){
					TileEntityVoidStorage store = (TileEntityVoidStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
					if (store.getStackInSlot(0)!=null){
						int a = store.getStackInSlot(0).stackSize + store.storage;
						return store.getStackInSlot(0).getDisplayName() + "x" + a;
					}
				}
				if (block == modMain.pickVoidStorage){
					TileEntityVoidPickStorage store = (TileEntityVoidPickStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
					if (store.getStackInSlot(0)!=null){
						int a = store.getStackInSlot(0).stackSize + store.storage;
						return store.getStackInSlot(0).getDisplayName() + "x" + a;
					}
				}
				return this.getStackInSlot(0).getDisplayName();
			}
			
			public ItemStack getItem(){
				Block block = worldObj.getBlock(xCoord, yCoord-1, zCoord);
				if (block == modMain.voidStorage){
					TileEntityVoidStorage store = (TileEntityVoidStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
					if (store.getStackInSlot(0)!=null){
						return store.getStackInSlot(0);
					}
				}
				if (block == modMain.pickVoidStorage){
					TileEntityVoidPickStorage store = (TileEntityVoidPickStorage) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
					if (store.getStackInSlot(0)!=null){
						return store.getStackInSlot(0);
					}
				}
					return this.getStackInSlot(0);
				
			}

		}


