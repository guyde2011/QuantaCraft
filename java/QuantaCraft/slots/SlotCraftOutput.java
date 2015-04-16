package QuantaCraft.slots;

import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCraftOutput extends Slot{

	public IInventory inv;
	public SlotCraftOutput(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_){
		super(p_i1824_1_,p_i1824_2_, p_i1824_3_, p_i1824_4_);
		inv =p_i1824_1_;
		
	}
	
	public void onPickupFromSlot(EntityPlayer player , ItemStack stack){
		super.onPickupFromSlot(player, stack);
		if (!((TileEntityConstructorTable)inv).craft){
			for (int i = 0; i<9;i++){
				inv.decrStackSize(i,1);
			}
			inv.markDirty();
		}
		else {
			if (this.inv.getStackInSlot(9)!=null){
				((TileEntityConstructorTable)inv).craft = true;
			}
			if (this.inv.getStackInSlot(9)==null){
				((TileEntityConstructorTable)inv).craft = false;
			}
		}
	}
	
	
	
	public boolean isItemValid(ItemStack stack){
		return false;
	}
	
	
}
