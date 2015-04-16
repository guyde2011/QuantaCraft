package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


















import cpw.mods.fml.common.registry.GameRegistry;
import QuantaCraft.Crafting.LiquidCrafter.ForgeRecipe;
import QuantaCraft.Crafting.Table.InventoryDummy;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.main.modMain;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;




public class TileEntityConstructorFluidModifier extends TileEntity implements ISidedInventory , IFluidHandler{


		public TileEntityConstructorFluidModifier() {
			super();
			Flu = FluidRegistry.WATER;
		}
		
		
		
		 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 5);



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
		
		
	    public boolean initialized;
		public static double r;
		public boolean craft=false;
		public Fluid Flu;
        private ItemStack[] inv= new ItemStack[4];
		@Override
		public int[] getAccessibleSlotsFromSide(int par1)
	    {
			int[] inputSlots = new int[2];
			for (int i=0;i<2;i++){
				inputSlots[i]=i;
			}
			return inputSlots;
	    }
		
		public void readFromTank(NBTTagCompound tag){
			 tank.readFromNBT(tag);
		}
		
		@Override
		public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
		{
				readFromTank(packet.func_148857_g());
	        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	        if (this.worldObj.isRemote){
	        	Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
	        }
		}
        @Override
        public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
  
        }
        
    	private boolean areEqual(ItemStack stack1 , ItemStack stack2){
    		if (stack1==null && stack1==stack2){
    			return true;
    		}
    		if (stack1==null){
    			return false;
    		}
    		if (stack2==null){
    			return false;
    		}
    		return stack1.getItem()==stack2.getItem();

    	}

		 /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
		@Override
	    public boolean canInsertItem(int par1, ItemStack stack, int par3)
	    {
			switch(par3){
			case 3:
				return false;
			case 4:
				return stack.getItem() instanceof ItemKnowledge || stack.getItem() instanceof KnowledgeBook;
			}
	    	return true;
	     
	    }

	    /**
	     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
		@Override
	    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	    	return par1==9;
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
        	this.markDirty();
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }              

        }

        @Override
        public ItemStack decrStackSize(int slot, int amt) {
        	this.markDirty();
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
                this.markDirty();
                return stack;
        }

        @Override
        public ItemStack getStackInSlotOnClosing(int slot) {
        	this.markDirty();
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        setInventorySlotContents(slot, null);
                }
                this.markDirty();
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
                tank.writeToNBT(tagCompound);
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
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                		if (!this.initialized && worldObj != null) {
                	
                		}  
                		this.initialized=true;
                	}
                	if (modMain.fRecipes.checkRecipe(this)){
                		ForgeRecipe rec = modMain.fRecipes.getCurResult(this);
                		ItemStack stack = this.getStackInSlot(2);
                		if (this.getStackInSlot(2)==null){
                			this.decrStackSize(0, 1);
                			this.decrStackSize(1, 1);
                			tank.drain(rec.getLiquidAmount(), true);
                			stack = rec.getResult();
                		}
                		else if (areEqual(this.getStackInSlot(2),rec.getResult()) && rec.getResult().stackSize+this.getStackInSlot(2).stackSize<=this.getStackInSlot(2).getMaxStackSize()){
                			this.decrStackSize(0, 1);
                			this.decrStackSize(1, 1);
                			tank.drain(rec.getLiquidAmount(), true);
                			stack = rec.getResult();
                			stack.stackSize += this.getStackInSlot(2).stackSize;
                		}
                		this.setInventorySlotContents(2, stack);
                	}
            		
                	this.markDirty();
                }
                
                @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

				@Override
				public boolean isItemValidForSlot(int i, ItemStack itemstack) {
					this.markDirty();
					if (i < 9) {
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


