package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import QuantaCraft.GUIs.ISmartGui;
import QuantaCraft.main.modMain;
import QuantaCraft.packets.SmartContainerSyncCommand;
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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;



public class TileEntitySmartContainer extends TileEntity  implements ISidedInventory , IFluidHandler , IEnergyHandler{


		public TileEntitySmartContainer() {
			super();

		}
		public int energyStored;
		public int maxEnergy;
		
		public EnergyStorage store;
	    public boolean initialized;

		public boolean bucketUp;
        private ItemStack[] inv= new ItemStack[37];
		@Override
		public int[] getAccessibleSlotsFromSide(int par1)
	    {
			int[] allSlots = new int[37];
			for (int i=0;i<36;i++){
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
                tank.readFromNBT(tagCompound);
                tank.setCapacity(tagCompound.getInteger("FluCapacity"));
                NBTTagList tagList = tagCompound.getTagList("Inventory",tagCompound.getId());
                for (int i = 0; i < tagList.tagCount(); i++) {
                        NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                        byte slot = tag.getByte("Slot");
                        if (slot >= 0 && slot < inv.length) {
                                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                                
                        }
                }
            	if (store==null) {
            		store = new EnergyStorage(10000000, 15000 , 15000);
            	}
            	store.setEnergyStored(tagCompound.getInteger("EnergyRF"));
                	
                	

        }

        public void updateGuiItem(ItemStack stack){
        	this.setInventorySlotContents(37 , stack);
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) { 
                super.writeToNBT(tagCompound);
                tank.writeToNBT(tagCompound);
                tagCompound.setInteger("FluCapacity", tank.getCapacity());
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
                if (store!=null){
                	tagCompound = store.writeToNBT(tagCompound);
                }
                tagCompound.setInteger("EnergyRF", energyStored);
                tagCompound.setTag("Inventory", itemList);
        }

                @Override

                public void updateEntity() {
        			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
        				return;
        			}
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                	if (!this.initialized && worldObj != null) {
        				store = new EnergyStorage(10000000, 15000 , 15000);
                	}  
                    this.initialized=true;
                    if (this.tank.getFluid() != null){
                    	modMain.packetCommands.sendPublicServerMessage(new SmartContainerSyncCommand(this));
                    } else {
                    	modMain.packetCommands.sendPublicServerMessage(SmartContainerSyncCommand.noFluid(this));
                    }
                	}  
                	boolean pie = true;
                	int i =1;
                	while (pie){
                		if (this.worldObj.getBlock(xCoord, yCoord + i, zCoord).hasTileEntity() && this.worldObj.getTileEntity(xCoord, yCoord + i, zCoord) instanceof TileEntityTank){
                			pie = true;
                			((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord + i, zCoord)).renderNormally = false;
                			((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord + i, zCoord)).change = false;
                			if (i==1){
                    			((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord + i, zCoord)).near = true;
                			}
                    		i++;
                		} else {
                    		i++;
                			pie = false;
                		}

                	}
                	this.tank.setCapacity(16000 * i);
                	System.out.println(this.getEnergyStored(ForgeDirection.UNKNOWN) + " hello");
                	if (store!=null){
                		energyStored = store.getEnergyStored();
                		maxEnergy = store.getMaxEnergyStored();
                	}}
                @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

				@Override
				public boolean isItemValidForSlot(int i, ItemStack itemstack) {
					if (i < 36) {
						return false;
					} else {
						return true;
					}			
				}

			
			private ISmartGui gui;	
			
			public void setSubGui(ISmartGui subGui){
				gui = subGui;
			}
			
			public void clearGui(){
				this.gui = null;
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





			@Override
			public boolean canConnectEnergy(ForgeDirection from) {
				// TODO Auto-generated method stub
				return true;
			}


			@Override
			public int receiveEnergy(ForgeDirection from, int maxReceive,
					boolean simulate) {
				// TODO Auto-generated method stub
            	if (store==null) {
            		store = new EnergyStorage(10000000, 15000 , 15000);
            	}
				int a = store.receiveEnergy(maxReceive, simulate);
                energyStored = store.getEnergyStored();
                maxEnergy = store.getMaxEnergyStored();
                return a;
			}


			@Override
			public int extractEnergy(ForgeDirection from, int maxExtract,
					boolean simulate) {
				// TODO Auto-generated method stub
            	if (store==null) {
            		store = new EnergyStorage(10000000, 15000 , 15000);
            	}
				int a = store.extractEnergy(maxExtract, simulate);
                energyStored = store.getEnergyStored();
                maxEnergy = store.getMaxEnergyStored();
                return a;
			}
			
			public void readFromTank(NBTTagCompound tag){
				 tank.readFromNBT(tag);
			}


			@Override
			public int getEnergyStored(ForgeDirection from) {
				// TODO Auto-generated method stub
				return energyStored;
			}


			@Override
			public int getMaxEnergyStored(ForgeDirection from) {
				// TODO Auto-generated method stub
				return maxEnergy;
			}
			
			private FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 32);

			public FluidTank getTank(){
				return tank;
			}
			
			public void setTankCapacity(int a){
				tank.setCapacity(a);
			}

		    /* IFluidHandler */
		    @Override
		    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
		    {
		        return tank.fill(resource, doFill);
		    }

		    @Override
		    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
		    {
		        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
		        {
		            return null;
		        }
		        return tank.drain(resource.amount, doDrain);
		    }

		    @Override
		    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
		    {
		        return tank.drain(maxDrain, doDrain);
		    }

		    @Override
		    public boolean canFill(ForgeDirection from, Fluid fluid)
		    {
		        return true;
		    }

		    @Override
		    public boolean canDrain(ForgeDirection from, Fluid fluid)
		    {
		        return true;
		    }

		    @Override
		    public FluidTankInfo[] getTankInfo(ForgeDirection from)
		    {
		        return new FluidTankInfo[] { tank.getInfo() };
		    }
		    
		    public void setFluidAndAmount(int id , int amount){
		    	tank.setFluid(new FluidStack(FluidRegistry.getFluid(id),amount));
		    }

				
		}


