package QuantaCraft.Items;

import java.util.Iterator;






import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.main.Interfaces.ISoulItem;
import QuantaCraft.slots.SoulSlot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerSoulDrain extends Container {


	
    private InventorySoulDrain tileEntity;
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    

    public ContainerSoulDrain (InventoryPlayer inventoryPlayer, InventorySoulDrain te){
            tileEntity = te;

            //the Slot constructor takes the IInventory and the slot number in that it binds to
            //and the x-y coordinates it resides on-screen


            //commonly used vanilla code that adds the player's inventory
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
            return true;
    }
    @Override
    public void detectAndSendChanges()
    {
             super.detectAndSendChanges();
            
             
    }         
    public void updateProgressBar(int par1, int par2)
    {
    }
    
  
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            //null checks and checks if the item can be stacked (maxStackSize > 1)
            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    //merges the item into player inventory since its in the tileEntity
                    System.out.println(slot);
                    if (slot < 2) {
                            if (!this.mergeItemStack(stackInSlot, 2, 37, true)) {
                                    return null;
                            }
                    }
                    //places it into the tileEntity is possible since its in the player inventory
                    else if (stack.getItem() instanceof ISoulItem){  
                    if (!this.mergeItemStack(stackInSlot, 0, 2, false)) {
                            return null;
                    }
                    }
                    if (stackInSlot.stackSize == 0) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }
}
