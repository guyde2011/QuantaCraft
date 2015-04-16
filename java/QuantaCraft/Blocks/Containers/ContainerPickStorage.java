package QuantaCraft.Blocks.Containers;

import java.util.Iterator;



import QuantaCraft.Blocks.TileEntities.TileEntityPickStorage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerPickStorage extends Container {


	
    private TileEntityPickStorage tileEntity;
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    

    public ContainerPickStorage (InventoryPlayer inventoryPlayer, TileEntityPickStorage te){
            tileEntity = te;

            //the Slot constructor takes the IInventory and the slot number in that it binds to
            //and the x-y coordinates it resides on-screen
            for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                            addSlotToContainer(new Slot(te,i * 9 + j,10 + j * 18, i * 18 ));

                    }
            }

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
                    if (slot < 45) {
                            if (!this.mergeItemStack(stackInSlot, 45, 45 + 35, true)) {
                                    return null;
                            }
                    }
                    //places it into the tileEntity is possible since its in the player inventory
                    else if (!this.mergeItemStack(stackInSlot, 0, 44, false)) {
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
