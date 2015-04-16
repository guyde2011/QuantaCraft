package QuantaCraft.Blocks.HoloControllers;

import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TileEntityHoloRedstoneControl extends BasicHoloController{
	
	public int Redstone;
	public boolean initialized;
	
	public TileEntityHoloRedstoneControl(){
		super();
	}
	@Override
	public void onItemPlaced(TileEntityHologramStand holo, EntityPlayer player) {
			if (player.getHeldItem()!=null){
				holo.setInventorySlotContents(0, player.getHeldItem());
				player.setCurrentItemOrArmor(0, null);
			}
				
		
		Redstone = 15;
			
	}

	@Override
	public void onItemTaken(TileEntityHologramStand holo, EntityPlayer player) {
		if (holo.getStackInSlot(0)!=null){
			player.setCurrentItemOrArmor(0, holo.getStackInSlot(0));
			holo.setInventorySlotContents(0, null);
		}
			Redstone = 0;
		
	}

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
    	super.readFromNBT(tagCompound); 
    	if (tagCompound.hasKey("RedstoneOut")){
    		this.Redstone = tagCompound.getInteger("RedstoneOut");
    	}
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
    	super.writeToNBT(tagCompound);
    	tagCompound.setInteger("RedstoneOut", Redstone);
    	
    }
    
    @Override 
    public boolean canTakeItem(TileEntityHologramStand holo, EntityPlayer player){
    	return true;
    }
    
    @Override 
    public boolean canPlaceItem(TileEntityHologramStand holo, EntityPlayer player){
    	return true;
    }
    
    @Override
    public void updateEntity() {

    	if (!this.worldObj.isRemote) {

    		if (!this.initialized && worldObj != null) {
    	
    		}  
    	}
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    	World par1World = this.worldObj;
    	int par2 = this.xCoord;
    	int par3 = this.yCoord;
    	int par4 = this.zCoord;
    	par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.getBlockType());
    	par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.getBlockType());
    	par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.getBlockType());
    	par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.getBlockType());
    	par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.getBlockType());
    	par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.getBlockType());
        this.initialized=true;
    }


}
