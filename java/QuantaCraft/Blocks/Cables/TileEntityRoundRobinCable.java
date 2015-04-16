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




public class TileEntityRoundRobinCable extends TileEntityBasicCable implements ICableItems{


		public TileEntityRoundRobinCable() {
			super();
			sideCur = 0;
		}
		public int sideCur;
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
        
        public void addToSide(){
        	sideCur++;
        	if (sideCur==6){
        		sideCur = 0;
        	}
        }
        
        public int toSix(int a){
        	if (a>=6){
        		return a-6;
        	}
        	return a;
        }
        
        @Override
        public void updateEntity() {
        	if (!this.worldObj.isRemote) {
        		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        		if (!this.initialized && worldObj != null) {	
            	}
            this.initialized=true;       
        	}             
        	this.readFromTaskList();
        	
        }
        
    private TaskList<ItemStack> taskList = new TaskList<ItemStack>();
        
        @Override
    	public void readFromTaskList(){
        	for (int i = 0; i<taskList.size();i++){
        		this.onCableTransfer(taskList.getSide(i), taskList.getInfo(i));
        	}
        	taskList.empty();
        }
        
        @Override
        public void addToTaskList(Sides side, ItemStack stack){
        	taskList.add(side, stack);
        }
        
	

        
		@Override
		public void onCableTransfer(Sides side, ItemStack stack) {
			int f = 0;
			for (Sides Side : Sides.values()){
				Sides sid = Sides.values()[toSix(f+sideCur)];
				if (sid!=side){
					if (Sides.getBlock(this, sid).hasTileEntity()){
						if (Sides.getTileEntity(this, sid) instanceof ICableItems && !(Sides.getTileEntity(this, sid) instanceof TileEntityExtractCable)){
							sideCur = toSix(f+sideCur);
							break;
						}
						if (Sides.getTileEntity(this, sid) instanceof IInventory){
							sideCur = toSix(f+sideCur);
							break;
						}
					}
				}
				f++;
			}
			int r = 0;
			for (Sides Side : Sides.values()){
				if (Side!=side && r==sideCur){
					if (Sides.getBlock(this, Side).hasTileEntity()){
						if (Sides.getTileEntity(this, Side) instanceof ICableItems && !(Sides.getTileEntity(this, Side) instanceof TileEntityExtractCable)){
							((ICableItems)Sides.getTileEntity(this, Side)).addToTaskList(Side.getOppositeSide(Side), stack);
							addToSide();
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
										addToSide();
										stack = null;
										return;
									}
								}
								if (inv.getStackInSlot(i)==null){
									inv.setInventorySlotContents(i, stack);
									stack = null;
									addToSide();
									return;
								}
							}
						}
					}
				}
				r++;
			}
			Random rand = new Random();
    		float rx = rand.nextFloat() * 0.8F + 0.1F;
            float ry = rand.nextFloat() * 0.8F + 0.1F;
            float rz = rand.nextFloat() * 0.8F + 0.1F;
            ItemStack item = new ItemStack(stack.getItem());
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
			return "CableRoundRobin";
		}
		@Override
		public boolean CanConnect(TileEntity ent) {
			
			return (ent instanceof ICableItems || ent instanceof IInventory);
		}

			
		}


