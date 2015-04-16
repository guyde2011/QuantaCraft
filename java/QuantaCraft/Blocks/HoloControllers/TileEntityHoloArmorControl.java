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

public class TileEntityHoloArmorControl extends BasicHoloController{


	
	public TileEntityHoloArmorControl(){
		super();
	}
	@Override
	public void onItemPlaced(TileEntityHologramStand holo, EntityPlayer player) {
		if (player.getHeldItem()!=null){
			holo.setInventorySlotContents(0, player.getHeldItem());
			player.setCurrentItemOrArmor(0, null);
		}
			
	}

	@Override
	public void onItemTaken(TileEntityHologramStand holo, EntityPlayer player) {
		if (holo.getStackInSlot(0)==null){holo.setInventorySlotContents(0, null); return;}
		if (holo.getStackInSlot(0).getItem() instanceof ItemArmor){
			ItemArmor arm = (ItemArmor) holo.getStackInSlot(0).getItem();
			if (player.inventory.armorInventory[3-arm.armorType]==null){
				player.inventory.armorInventory[3-arm.armorType] = holo.getStackInSlot(0);
				holo.setInventorySlotContents(0, null);
			} else {
				player.setCurrentItemOrArmor(0, holo.getStackInSlot(0));
				holo.setInventorySlotContents(0, null);
			}
		} else {
			player.setCurrentItemOrArmor(0, holo.getStackInSlot(0));
			holo.setInventorySlotContents(0, null);
		}
	}
	
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
	
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
    	super.readFromNBT(tagCompound); 
      
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
    public void writeToNBT(NBTTagCompound tagCompound) {
    	super.writeToNBT(tagCompound);
    	
    }
    
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound tag = new NBTTagCompound();
    	this.writeToNBT(tag);
    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }


}
