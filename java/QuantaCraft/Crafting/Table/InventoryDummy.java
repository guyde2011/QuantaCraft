package QuantaCraft.Crafting.Table;

import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryDummy extends InventoryCrafting{

	public InventoryDummy(TileEntityConstructorTable ent) {
		super(new ContainerDummy(),3,3);
		setInventory(ent);
		// TODO Auto-generated constructor stub
	}
	
	public void setInventory(TileEntityConstructorTable ent){
		for (int i=0;i<9;i++){
			this.setInventorySlotContents(i, ent.getStackInSlot(i));
		}
	}
	


}
