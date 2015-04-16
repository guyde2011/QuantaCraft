package QuantaCraft.Blocks.Containers;

import java.util.Iterator;






import QuantaCraft.Blocks.TileEntities.TileEntityConstructorFluidModifier;
import QuantaCraft.slots.KnowledgeSlot;
import QuantaCraft.slots.SlotCraftOutput;
import QuantaCraft.slots.SlotLiqOutput;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerConstructorFluidModifier extends Container {


	
    private TileEntityConstructorFluidModifier tileEntity;
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    

    public ContainerConstructorFluidModifier (InventoryPlayer inventoryPlayer, TileEntityConstructorFluidModifier te){
            tileEntity = te;

            //the Slot constructor takes the IInventory and the slot number in that it binds to
            //and the x-y coordinates it resides on-screen
          
            addSlotToContainer(new Slot(te,0,50, 35));
            addSlotToContainer(new Slot(te,1,107, 35));
            addSlotToContainer(new SlotLiqOutput(te,2,79,72));
            addSlotToContainer(new KnowledgeSlot(te,3 ,79,22));
            

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
                    if (slot < 4) {
                            if (!this.mergeItemStack(stackInSlot, 4, 39, true)) {
                                    return null;
                            }
                    } else if (!this.mergeItemStack(stackInSlot, 0, 3, false)) {
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
