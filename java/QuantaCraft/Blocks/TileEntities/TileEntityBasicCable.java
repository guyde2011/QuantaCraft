package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import QuantaCraft.Items.VoidGem;
import QuantaCraft.main.modMain;
import net.minecraft.block.BlockContainer;
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




public class TileEntityBasicCable extends TileEntity{


		public TileEntityBasicCable() {
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
         }
                
                
                
        

                @Override
                public void updateEntity() {
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                		if (!this.initialized && worldObj != null) {	
                    	}
                    this.initialized=true;       
                	}             
                	
                }
                
        @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }


		}


