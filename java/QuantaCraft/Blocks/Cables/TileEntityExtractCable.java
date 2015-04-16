package QuantaCraft.Blocks.Cables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import QuantaCraft.Blocks.TileEntities.TileEntityBasicCable;
import QuantaCraft.Items.VoidGem;
import QuantaCraft.main.Sides;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.ICableItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.item.EntityItem;
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




public class TileEntityExtractCable extends TileEntityBasicCable implements ICableItems{


		public TileEntityExtractCable() {
			super();
		}
		public ItemStack storedItem;
	    public boolean initialized;


		
        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
                super.readFromNBT(tagCompound); 
                if (tagCompound.hasKey("StorageItem")){
                	storedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound) tagCompound.getTag("StorageItem"));
                } else {
                	storedItem = null;
                }
        }


        @Override
        public void writeToNBT(NBTTagCompound tagCompound) { 
                super.writeToNBT(tagCompound);
                NBTTagCompound tag = new NBTTagCompound();
                if (storedItem!=null){
                	storedItem.writeToNBT(tag);
                	tagCompound.setTag("StorageItem", tag);
                }
                
                
                
        }

                @Override
                public void updateEntity() {
                	
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                		if (!this.initialized && worldObj != null) {	
                    	}
                    this.initialized=true;
        			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
        				return;
        			}
        			this.readFromTaskList();
                    for (Sides side : Sides.values()){
                    	if (Sides.getBlock(this, side).hasTileEntity() && Sides.getTileEntity(this, side) instanceof IInventory){
                    		IInventory inv = ((IInventory)Sides.getTileEntity(this, side));
                    		for (int i = 0;i<inv.getSizeInventory();i++){
                    			if (inv.getStackInSlot(i)!=null){
                    				this.onCableTransfer(side, inv.getStackInSlot(i));
                    				inv.setInventorySlotContents(i, null);
                    			}
                    		}
                    	}
                    }
                	}            
                	
                }
                
        @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

        public boolean areStacksEqual(ItemStack stack0 , ItemStack stack1){
        	if (stack0==null && stack1==null){
        		return true;
        	}
        	if (stack0!=null && stack1==null){
        		return false;
        	}
        	if (stack0==null && stack1!=null){
        		return false;
        	}
        	if (stack0.getItem()!=stack1.getItem()){
        		return false;
        	}
        	if (stack0.getItemDamage()!=stack1.getItemDamage()){
        		return false;
        	}
        	if (stack0.areItemStackTagsEqual(stack0, stack1)){
        		return true;
        	}
        	return false;
        }
        
        private TaskList<ItemStack> taskList = new TaskList<ItemStack>();
        
        @Override
    	public void readFromTaskList(){
        }
        
        @Override
        public void addToTaskList(Sides side, ItemStack stack){
        	taskList.add(side, stack);
        }
        
		@Override
		public void onCableTransfer(Sides side, ItemStack stack) {
			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
				return;
			}
			for (Sides Side : Sides.values()){
				if (Side!=side){
					if (Sides.getBlock(this, Side).hasTileEntity()){
						if (Sides.getTileEntity(this, Side) instanceof ICableItems){
							((ICableItems)Sides.getTileEntity(this, Side)).addToTaskList(Side.getOppositeSide(Side), stack);
							return;
						}
						if (Sides.getTileEntity(this, Side) instanceof IInventory){
							IInventory inv = ((IInventory)Sides.getTileEntity(this, Side));
							for (int i = 0; i<inv.getSizeInventory();i++){
								if (areStacksEqual(inv.getStackInSlot(i), stack) && stack!=null){
									int v = inv.getStackInSlot(i).stackSize;
									inv.decrStackSize(i,Math.max(-stack.stackSize, inv.getStackInSlot(i).stackSize-inv.getStackInSlot(i).getMaxStackSize()));
									
									if (inv.getStackInSlot(i)!=null && inv.getStackInSlot(i).stackSize>0){
										stack.stackSize -= inv.getStackInSlot(i).stackSize - v;
									} else {
										stack = null;
										return;
									}
								}
								if (inv.getStackInSlot(i)==null){
									inv.setInventorySlotContents(i, stack);
									stack = null;
									return;
								}
							}
						}
					}
				}
			}
			Random rand = new Random();
    		float rx = rand.nextFloat() * 0.8F + 0.1F;
            float ry = rand.nextFloat() * 0.8F + 0.1F;
            float rz = rand.nextFloat() * 0.8F + 0.1F;

            EntityItem entityItem = new EntityItem(this.worldObj,
                            this.xCoord + rx, this.yCoord + ry, this.zCoord + rz,
                           stack);
            float factor = 0.05F;
            entityItem.motionX = rand.nextGaussian() * factor;
            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = rand.nextGaussian() * factor;
            if (!worldObj.isRemote){
            	worldObj.spawnEntityInWorld(entityItem);
            }
            
            
			
			
		}


		@Override
		public String getTexture() {
			return "CableExtract";
		}
		@Override
		public boolean CanConnect(TileEntity ent) {
			
			return ((ent instanceof ICableItems && !(ent instanceof TileEntityExtractCable) )|| ent instanceof IInventory );
		}

			
		}


