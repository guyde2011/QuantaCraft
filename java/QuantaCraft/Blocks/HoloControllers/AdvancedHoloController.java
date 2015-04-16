package QuantaCraft.Blocks.HoloControllers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;
import QuantaCraft.main.Interfaces.IHologramController;

public abstract class AdvancedHoloController extends TileEntity implements IHologramController{

	public AdvancedHoloController(){
		super();
	}
	
	@Override
	public void onPlayerInteract(TileEntityHologramStand holo , EntityPlayer player){
		if (holo.getStackInSlot(0)!=null && player.getHeldItem()==null && player.isSneaking() && this.canTakeItem(holo, player)){
			onItemTaken(holo , player);
		} else if (holo.getStackInSlot(0)==null && player.getHeldItem()!=null && !player.isSneaking() && this.canPlaceItem(holo, player)){
			onItemPlaced(holo , player);
		}
		
	}
	


	public abstract void onItemPlaced(TileEntityHologramStand holo , EntityPlayer player);
	
	public abstract void onItemTaken(TileEntityHologramStand holo , EntityPlayer player);

	public String onPlayerWalkOn(TileEntityHologramStand holo, EntityPlayer player){
		return "void";
		
	}

}
