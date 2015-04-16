package QuantaCraft.Blocks.Cables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityBasicCable;
import QuantaCraft.Items.VoidGem;
import QuantaCraft.main.Sides;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.ICableItems;
import QuantaCraft.main.Interfaces.ICableRF;
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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;




public class TileEntityRFCable extends TileEntityBasicCable implements ICableRF , IEnergyHandler{


		public TileEntityRFCable() {
			super();
		}
		public ItemStack storedItem;
	    public boolean initialized;


		
        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
                super.readFromNBT(tagCompound); 

        }


        @Override
        public void writeToNBT(NBTTagCompound tagCompound) { 
                super.writeToNBT(tagCompound);
                NBTTagCompound tag = new NBTTagCompound();

                
                
                
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
        
        
	

		@Override
		public String getTexture() {
			return "CableRFNormal";
		}
		
		@Override
		public boolean CanConnect(TileEntity ent) {
			return (ent instanceof ICableRF || ent instanceof IEnergyStorage || ent instanceof IEnergyHandler);
		}


		@Override
		public void onCableTransfer(Sides side, float amount) {
			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
				return;
			}
			int splits = 0;
			for (Sides Side : Sides.values()){
				if (Side!=side){
					if (amount<1){
						return;
					}
					if (Sides.getBlock(this, Side).hasTileEntity()){
						if (Sides.getTileEntity(this, Side) instanceof ICableRF){
							splits++;
						} else if (Sides.getTileEntity(this, Side) instanceof IEnergyHandler && ((IEnergyHandler) Sides.getTileEntity(this, Side)).getEnergyStored(Side.getDir())!=((IEnergyHandler) Sides.getTileEntity(this, Side)).getMaxEnergyStored(Side.getDir())){
							splits++;
						} else if (Sides.getTileEntity(this, Side) instanceof IEnergyStorage && ((IEnergyStorage) Sides.getTileEntity(this, Side)).getEnergyStored()!=((IEnergyStorage) Sides.getTileEntity(this, Side)).getMaxEnergyStored()){
							splits++;
						}
					}
				}
			}
			float amm = amount;
			for (Sides Side : Sides.values()){
				if (Side!=side){
					if (amount<1 || splits == 0){
						return;
					}
					if (Sides.getBlock(this, Side).hasTileEntity()){
						if (Sides.getTileEntity(this, Side) instanceof ICableRF){
							((ICableRF)Sides.getTileEntity(this, Side)).addToTaskList(Side.getOppositeSide(Side), amount-1f);
							return;
						}
						if (Sides.getTileEntity(this, Side) instanceof IEnergyHandler){
							ForgeDirection dir = Side.getDir();
							IEnergyHandler ent = (IEnergyHandler) Sides.getTileEntity(this, Side);
							int energy = ent.getEnergyStored(dir);
							amount -= ent.receiveEnergy(dir,(int) amm/splits, false);
						}
						if (Sides.getTileEntity(this, Side) instanceof IEnergyStorage){
							ForgeDirection dir = Side.getDir();
							IEnergyStorage ent = (IEnergyStorage) Sides.getTileEntity(this, Side);
							int energy = ent.getEnergyStored();
							amount -= ent.receiveEnergy((int) (amm/splits), false);
						}
					}
				}
			}
			if (amount>0){
				for (Sides Side : Sides.values()){
					if (Side!=side){
						if (amount<1){
							return;
						}
						if (Sides.getBlock(this, Side).hasTileEntity()){
							if (Sides.getTileEntity(this, Side) instanceof ICableRF){
								((ICableRF)Sides.getTileEntity(this, Side)).addToTaskList(Side.getOppositeSide(Side), amount-1f);
								return;
							}
							if (Sides.getTileEntity(this, Side) instanceof IEnergyHandler){
								ForgeDirection dir = Side.getDir();
								IEnergyHandler ent = (IEnergyHandler) Sides.getTileEntity(this, Side);
								int energy = ent.getEnergyStored(dir);
								amount -= ent.receiveEnergy(dir,(int) amount, false);
							}
							if (Sides.getTileEntity(this, Side) instanceof IEnergyStorage){
								ForgeDirection dir = Side.getDir();
								IEnergyStorage ent = (IEnergyStorage) Sides.getTileEntity(this, Side);
								int energy = ent.getEnergyStored();
								amount -= ent.receiveEnergy((int) (amount), false);
							}
						}
					}
				}
			}
		}


		@Override
		public boolean canConnectEnergy(ForgeDirection from) {
			// TODO Auto-generated method stub
			return true;
		}


		@Override
		public int receiveEnergy(ForgeDirection from, int maxReceive,
				boolean simulate) {
			this.onCableTransfer(Sides.getSide(from), maxReceive);
			return maxReceive;
		}


		@Override
		public int extractEnergy(ForgeDirection from, int maxExtract,
				boolean simulate) {
			return 0;
		}


		@Override
		public int getEnergyStored(ForgeDirection from) {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public int getMaxEnergyStored(ForgeDirection from) {
			// TODO Auto-generated method stub
			return 65536;
		}



        
    private TaskList<Float> taskList = new TaskList<Float>();
        
    	public void addToTaskList(Sides side , float amount){
    		taskList.add(side , amount);
    	}
    
        @Override
    	public void readFromTaskList(){
        	for (int i = 0; i<taskList.size();i++){
        		this.onCableTransfer(taskList.getSide(i), taskList.getInfo(i));
        	}
        	taskList.empty();
        }



			
	}


