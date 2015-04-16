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
public class TileEntityHoloEquipControl extends AdvancedHoloController{


	
	public boolean initialized;

	public TileEntityHoloEquipControl(){
		super();
	}
	@Override
	public void onItemPlaced(TileEntityHologramStand holo, EntityPlayer player) {
		if (player.getHeldItem()!=null){
			holo.setInventorySlotContents(0, player.getHeldItem());
		}
		player.setCurrentItemOrArmor(0, null);
			
	}

	@Override
	public void onItemTaken(TileEntityHologramStand holo, EntityPlayer player) {
		if (holo.getStackInSlot(0)==null){holo.setInventorySlotContents(0, null);return;}
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
    public boolean canTakeItem(TileEntityHologramStand holo, EntityPlayer player){
    	return true;
    }
    
    @Override 
    public boolean canPlaceItem(TileEntityHologramStand holo, EntityPlayer player){
    	return true;
    }
    


    @Override
	public String onPlayerWalkOn(TileEntityHologramStand holo, EntityPlayer player){
		if (holo.getStackInSlot(0)==null ){return "void";}
		if (holo.getStackInSlot(0).getItem() instanceof ItemArmor){
			ItemArmor arm = (ItemArmor) holo.getStackInSlot(0).getItem();
			if (player.inventory.armorInventory[3-arm.armorType]==null){
				player.inventory.armorInventory[3-arm.armorType] = holo.getStackInSlot(0);
				holo.setInventorySlotContents(0, null);
				return "took";
			} else if (player.getHeldItem()==null){
				player.setCurrentItemOrArmor(0, holo.getStackInSlot(0));
				holo.setInventorySlotContents(0, null);
				return "took";
			}
		}else if (player.getHeldItem()==null) {
			player.setCurrentItemOrArmor(0, holo.getStackInSlot(0));
			holo.setInventorySlotContents(0, null);

			return "took";
		}
		return "void";
		

		
	}

    @Override

    public void updateEntity() {

    	if (!this.worldObj.isRemote) {
    		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    	if (!this.initialized && worldObj != null) {
    	
    	}  
        this.initialized=true;
       
       

       
}               }

}
