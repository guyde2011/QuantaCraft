package QuantaCraft.GUIs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;







import com.mojang.realmsclient.gui.ChatFormatting;

import QuantaCraft.Blocks.Containers.ContainerVoidPickStorage;
import QuantaCraft.Blocks.Containers.ContainerVoidStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class VoidPickStorageGui extends GuiContainer {
    	public int guiX = 256;
    	private TileEntityVoidPickStorage myMachine;
    	public int guiY = 256;
        public VoidPickStorageGui (InventoryPlayer inventoryPlayer,
                        TileEntityVoidPickStorage tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerVoidPickStorage(inventoryPlayer, tileEntity));
                myMachine = tileEntity;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                //draws "Inventory" or your regional equivalent
         	int x = (width) / 2;
            int y = (height) / 2;
            this.fontRendererObj.drawString(ChatFormatting.DARK_GRAY + "x " + myMachine.storage + " / " + myMachine.storageMax, 95, 53, 0);
           

        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
        	    ResourceLocation res = new ResourceLocation("quantacraft:textures/gui/PickVoidStorageUnit.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(res);
                int x = (width - guiX) / 2;
                int y = (height - guiY) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, 256, 236);
                if (myMachine.storedItem!=null){
                	this.itemRender.renderItemIntoGUI(this.fontRendererObj, Minecraft.getMinecraft().getTextureManager(), myMachine.storedItem, x+115, y+94);
                }
               
          	}
        
        public void updateScreen(){
        	 int x1 = (width - guiX) / 2;
             int y1 = (height - guiY) / 2;
             if (myMachine.storedItem!=null){
            	 this.itemRender.renderItemIntoGUI(this.fontRendererObj, Minecraft.getMinecraft().getTextureManager(), myMachine.storedItem, x1+115, y1+94);
             }
         	int x = (width) / 2;
            int y = (height) / 2;
            this.fontRendererObj.drawString(ChatFormatting.DARK_GRAY + "x " + myMachine.storage + " / " + myMachine.storageMax, 95, 53, 0);
           
        }

       
    
}
