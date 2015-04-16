package QuantaCraft.main.Interfaces;

import net.minecraft.item.ItemStack;
import QuantaCraft.main.Sides;

public interface ICableItems extends ICable{
	public void onCableTransfer(Sides side , ItemStack stack);
	public void addToTaskList(Sides side , ItemStack stack);
	public void readFromTaskList();
}
