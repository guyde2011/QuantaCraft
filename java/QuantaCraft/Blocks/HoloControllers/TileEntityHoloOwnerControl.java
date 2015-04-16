package QuantaCraft.Blocks.HoloControllers;

import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityHoloOwnerControl extends BasicHoloController{

	
	public String owner;
	public TileEntityHoloOwnerControl(EntityPlayer player){
		super();
		this.owner = player.getDisplayName();
	}
	
	public TileEntityHoloOwnerControl(){
		super();
	}
	@Override
	public void onItemPlaced(TileEntityHologramStand holo, EntityPlayer player) {
	}

	@Override
	public void onItemTaken(TileEntityHologramStand holo, EntityPlayer player) {
	}	

	
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
	
    @Override 
    public boolean canTakeItem(TileEntityHologramStand holo, EntityPlayer player){
    	return owner==null || player.getDisplayName().equals(this.owner);
    }
    
    @Override 
    public boolean canPlaceItem(TileEntityHologramStand holo, EntityPlayer player){
    	return owner==null || player.getDisplayName().equals(this.owner);
    }
	
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
    	super.readFromNBT(tagCompound); 
        if (tagCompound.hasKey("owner")){
        	this.owner = tagCompound.getString("owner");
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
    	super.writeToNBT(tagCompound);
    	if (this.owner!=null){
        	tagCompound.setString("owner",this.owner);
        }
    }
    
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound tag = new NBTTagCompound();
    	this.writeToNBT(tag);
    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }


}
