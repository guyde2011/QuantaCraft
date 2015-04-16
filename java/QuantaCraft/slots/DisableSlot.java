package QuantaCraft.slots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import QuantaCraft.Blocks.Containers.ContainerSmartContainer;
import QuantaCraft.GUIs.ISmartGui;
import QuantaCraft.GUIs.SmartContainerGui;
import QuantaCraft.GUIs.SmartGuisRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import static QuantaCraft.GUIs.SmartGuisRegistry.*;

public class DisableSlot extends Slot{

			public IInventory inv;
			private boolean disabled;
			private boolean disabledMaster;
			public DisableSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_){
				super(p_i1824_1_,p_i1824_2_, p_i1824_3_, p_i1824_4_);
				inv =p_i1824_1_;
				
			}
			
			
			public boolean isItemValid(ItemStack stack){
				return !disabled;
			}
			
			public boolean canDisable(){
				return this.getHasStack();
			}
			
			public void forceDisable(){
				disabled = true;
			}
			
			public boolean disable(){
				if (canDisable()){
					forceDisable();
					return true;
				}
				return false;
			}
			
			public void forceEnable(){
				disabled = false;
			}
			
			public boolean enable(){
				if (!disabledMaster){
					forceEnable();
					return true;
				}
				return false;
			}
			
			public void DisableAsMaster(ISmartGui gui){
				if (gui!=null){
					disabledMaster = true;
				}
			}
			
			public void EnableAsMaster(ISmartGui gui){
				if (gui!=null){
					disabledMaster = false;
				}
			}
			
			@SideOnly(Side.CLIENT)
			public void replaceInGui(SmartContainerGui gui){
				gui.drawTexturedModalRect(this.xDisplayPosition, this.yDisplayPosition, 222, 238, 18, 18);
			}
			@SideOnly(Side.CLIENT)
			public void replaceInGui(SmartContainerGui gui , ISmartGui smartGui){
				Minecraft.getMinecraft().getTextureManager().bindTexture(smartGui.getTexturePath());
				gui.drawTexturedModalRect(this.xDisplayPosition, this.yDisplayPosition, 0 , GUI_HEIGHT , 18, 18);
			}
			
			public void replaceInContainer(ContainerSmartContainer cont){
				if (disabled){
					cont.inventorySlots.set(this.slotNumber, null);
				} else {
					cont.inventorySlots.set(this.slotNumber, this);
				}
			}
}
