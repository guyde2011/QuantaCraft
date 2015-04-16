package QuantaCraft.GUIs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;






import QuantaCraft.Blocks.Containers.ContainerAdamantiteContainer;
import QuantaCraft.Blocks.Containers.ContainerConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class ConstructorTableGui extends GuiContainer {
    	public int guiX = 256;
    	private TileEntityConstructorTable myMachine;
    	public int guiY = 256;
        public ConstructorTableGui (InventoryPlayer inventoryPlayer,
                        TileEntityConstructorTable tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerConstructorTable(inventoryPlayer, tileEntity));
                myMachine = tileEntity;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                //draws "Inventory" or your regional equivalent

        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
        	    ResourceLocation res = new ResourceLocation("quantacraft:textures/gui/ConstructorTable.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(res);
                int x = (width - guiX) / 2;
                int y = (height - guiY) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, 256, 226);
        	
        }

       
    
}
