package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;









import cpw.mods.fml.common.registry.GameRegistry;
import QuantaCraft.Crafting.Table.InventoryDummy;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.main.modMain;
import net.minecraft.block.BlockContainer;
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
import net.minecraftforge.fluids.BlockFluidBase;




public class TileEntityConstructorTable extends TileEntity implements ISidedInventory {


		public TileEntityConstructorTable() {
			super();
		}
	    public boolean initialized;
		public static double r;
		public boolean craft=false;
        private ItemStack[] inv= new ItemStack[11];
		@Override
		public int[] getAccessibleSlotsFromSide(int par1)
	    {
			int[] inputSlots = new int[9];
			for (int i=0;i<9;i++){
				inputSlots[i]=i;
			}
			return inputSlots;
	    }

		 /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
		@Override
	    public boolean canInsertItem(int par1, ItemStack stack, int par3)
	    {
			switch(par3){
			case 9:
				return false;
			case 10:
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
                this.markDirty();
            	if (craft) { return;}
                if (slot!=9){
                	if(modMain.nRecipes.checkRecipe(this)){
                		this.setInventorySlotContents(9,  modMain.nRecipes.getRecipe(this));
                		return;
                	} else {
                		this.setInventorySlotContents(9, null);
                	}
                	List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

                	for (int i = 0;i<recipes.size();i++){
                		IRecipe cur = recipes.get(i);
                		if (cur.matches(new InventoryDummy(this), this.worldObj)){
                			this.setInventorySlotContents(9, cur.getCraftingResult(new InventoryDummy(this)));
                			break;
                		}
                		if (i==recipes.size()-1){
                			this.setInventorySlotContents(9, null);
                		}
                	}
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
                	this.markDirty();
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                		if (!this.initialized && worldObj != null) {
                	
                		}  
                		this.initialized=true;
                		if (this.getStackInSlot(9)==null){
                			this.craft = false;
                			if(modMain.nRecipes.checkRecipe(this)){
                        		this.setInventorySlotContents(9,  modMain.nRecipes.getRecipe(this));
                        		return;
                        	} else {
                        		this.setInventorySlotContents(9, null);

                        	}
                        	List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

                        	for (int i = 0;i<recipes.size();i++){
                        		IRecipe cur = recipes.get(i);
                        		if (cur.matches(new InventoryDummy(this), this.worldObj)){
                        			this.setInventorySlotContents(9, cur.getCraftingResult(new InventoryDummy(this)));
                        			break;
                        		}
                        		if (i==recipes.size()-1){
                        			this.setInventorySlotContents(9, null);
                        		}
                        	}
                		}
                    
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


