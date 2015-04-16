package QuantaCraft.Blocks.Containers;

import java.util.Iterator;






import QuantaCraft.Blocks.TileEntities.TileEntityVoidPickStorage;
import QuantaCraft.Items.VoidGem;
import QuantaCraft.slots.KnowledgeSlot;
import QuantaCraft.slots.SlotCraftOutput;
import QuantaCraft.slots.VoidSlot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerVoidPickStorage extends Container {


	
    private TileEntityVoidPickStorage tileEntity;
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    

    public ContainerVoidPickStorage (InventoryPlayer inventoryPlayer, TileEntityVoidPickStorage te){
            tileEntity = te;

            //the Slot constructor takes the IInventory and the slot number in that it binds to
            //and the x-y coordinates it resides on-screen
            addSlotToContainer(new Slot(te,0,75,28));
            addSlotToContainer(new VoidSlot(te,1 ,75,70));

            //commonly used vanilla code that adds the player's inventory
            bindPlayerInventory(inventoryPlayer);
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
    
    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
            for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                            addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 +9,
                                            10 + j * 18, 96 + i * 18));
                    }
            }

            for (int i = 0; i < 9; i++) {
                    addSlotToContainer(new Slot(inventoryPlayer, i, 10 + i * 18, 154));
            }
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
                    else if (!(stack.getItem() instanceof VoidGem)){
                    	 if (!this.mergeItemStack(stackInSlot, 0, 0, false)) {
                    		 return null;
                    	 }
                    }  else if (!this.mergeItemStack(stackInSlot, 0, 1, false)){
                           return null;
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
