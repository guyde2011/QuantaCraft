package QuantaCraft.main.Interfaces;

import QuantaCraft.main.Sides;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface ICableRF extends ICable{
	public void onCableTransfer(Sides side , float amount);
	public void addToTaskList(Sides side , float amount);
	public void readFromTaskList();
}
