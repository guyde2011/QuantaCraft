package QuantaCraft.GUIs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;








import QuantaCraft.Blocks.Containers.ContainerAdamantiteContainer;
import QuantaCraft.Blocks.Containers.ContainerConstructorFluidModifier;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorFluidModifier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class ConstructorFluidModifierGui extends GuiContainer {
    	public int guiX = 256;
    	private TileEntityConstructorFluidModifier myMachine;
    	public int guiY = 256;
        public ConstructorFluidModifierGui (InventoryPlayer inventoryPlayer,
                        TileEntityConstructorFluidModifier tileEntity) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerConstructorFluidModifier(inventoryPlayer, tileEntity));
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
        	    ResourceLocation res = new ResourceLocation("quantacraft:textures/gui/ConstructorLiquidTable.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(res);
                int x = (width - guiX) / 2;
                int y = (height - guiY) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, 256, 226);
                Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                if (myMachine.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid==null){
                	return;
                }
                int a = 0;
                for (int i = 0;i<4;i++){ 
                	a = Math.min(16 , (int)((myMachine.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount*64f)/5000f-i*16));
                	if (!((myMachine.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount*64f)/5000f-i*16<0)){
                		this.itemRender.renderIcon(x + 187 , y + 116 - i * 16 +16 - a, myMachine.tank.getFluid().getFluid().getStillIcon(), 16 , a);
                	}
                	
                }
        }

       
    
}
