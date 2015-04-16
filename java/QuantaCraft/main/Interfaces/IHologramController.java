package QuantaCraft.main.Interfaces;

import net.minecraft.entity.player.EntityPlayer;
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;

public interface IHologramController {
	public void onPlayerInteract(TileEntityHologramStand holo , EntityPlayer player);
	public void onItemPlaced(TileEntityHologramStand holo , EntityPlayer player);
	public void onItemTaken(TileEntityHologramStand holo , EntityPlayer player);
	public String onPlayerWalkOn(TileEntityHologramStand holo , EntityPlayer player);
	public boolean canTakeItem(TileEntityHologramStand holo , EntityPlayer player);
	public boolean canPlaceItem(TileEntityHologramStand holo , EntityPlayer player);
}
