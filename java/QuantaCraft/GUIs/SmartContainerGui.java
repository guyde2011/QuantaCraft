package QuantaCraft.GUIs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;












import QuantaCraft.Blocks.Containers.ContainerAdamantiteContainer;
import QuantaCraft.Blocks.Containers.ContainerSmartContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntitySmartContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class SmartContainerGui extends GuiContainer {
    	public int guiX = 256;
    	public TileEntitySmartContainer myMachine;
    	public int guiY = 256;
    	public int mouseX;
    	public int mouseY;
        public SmartContainerGui (InventoryPlayer inventoryPlayer,
                        TileEntitySmartContainer tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerSmartContainer(inventoryPlayer, tileEntity));
                myMachine = tileEntity;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        }

        public void drawScreen(int par1 , int par2 , float par3){
            this.mouseX = par1;
            this.mouseY = par2;
            super.drawScreen(par1, par2, par3);
    		int x = (width - guiX) / 2;
    		int y = (height - guiY) / 2;
        	if (myMachine.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid!=null){

                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                //draws "Inventory" or your regional equivalent
        		if (mouseX >= x + 219 && mouseX <= x + 235 && mouseY <= y + 215 && mouseY >= y + 55 ){
        			List list = new ArrayList();
        			list.add(EnumChatFormatting.GRAY + myMachine.getTank().getFluid().getFluid().getLocalizedName(myMachine.getTank().getFluid()));
        			list.add("" + EnumChatFormatting.GRAY + myMachine.getTank().getFluidAmount() + "mb /" + myMachine.getTank().getCapacity() + "mb");
        			this.drawHoveringText(list, mouseX , mouseY , this.fontRendererObj);
        		}
        	} else {
        		if (mouseX >= x + 219 && mouseX <= x + 235 && mouseY <= y + 215 && mouseY >= y + 55 ){
        			List list = new ArrayList();
        			list.add(EnumChatFormatting.GRAY + "Empty");
        			list.add("" + EnumChatFormatting.GRAY + "0mb /" + myMachine.getTank().getCapacity() + "mb");
        			this.drawHoveringText(list, mouseX, mouseY , this.fontRendererObj);
        		}
        	}
            if (mouseX >= x + 50 && mouseX <= x + 210 && mouseY <= y + 132 && mouseY >= y + 124 ){
            	List list = new ArrayList();
            	list.add("" + myMachine.getEnergyStored(ForgeDirection.UNKNOWN) + "/" + myMachine.getMaxEnergyStored(ForgeDirection.UNKNOWN) + " RF");
            	this.drawHoveringText(list, mouseX , mouseY , this.fontRendererObj);
            }

        }
        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
        	    ResourceLocation res = new ResourceLocation("quantacraft:textures/gui/SmartContainer.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(res);
                int x = (width - guiX) / 2;
                int y = (height - guiY) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, 256, 236);
            //    myMachine = (TileEntitySmartContainer) myMachine.getWorldObj().getTileEntity(myMachine.xCoord, myMachine.yCoord, myMachine.zCoord);
                float prc = (float)myMachine.getEnergyStored(ForgeDirection.UNKNOWN)/(float)myMachine.getMaxEnergyStored(ForgeDirection.UNKNOWN);
                drawBar((int)(prc * 160));
                int a = 0;
                Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                if (myMachine.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid!=null){
                	for (int i = 0;i<10;i++){ 
                			this.itemRender.renderIcon(x + 219 , y + 199 - i * 16, myMachine.getTank().getFluid().getFluid().getStillIcon(), 16 , 16);
                	}
                	Minecraft.getMinecraft().getTextureManager().bindTexture(res);
                    int proc = 1 + (int)((myMachine.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount*159f)/(float)myMachine.getTank().getCapacity());
                    for (int i = 0;i*16 + 15<160 - proc;i++){ 
                    	this.drawTexturedModalRect(x + 219 , y + 199 - i * 16 - proc, 240, 240, 16, 16);
                    }
                    	this.drawTexturedModalRect(x + 219 , y + 55, 240, 240, 16, Math.max(0 , Math.min(16 , 160-proc)));

                }
                

                

                


                
        }
        
        private void drawBar(int length){
        	 int x = (width - guiX) / 2;
             int y = (height - guiY) / 2;
        	 this.drawTexturedModalRect(x+50, y+124, 1, 244, length, 8 );
        }

       
    
}
