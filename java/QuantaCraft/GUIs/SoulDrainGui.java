package QuantaCraft.GUIs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;












import QuantaCraft.Blocks.Containers.ContainerAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Items.ContainerSoulDrain;
import QuantaCraft.Items.InventorySoulDrain;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeGuiBase;
import QuantaCraft.main.modMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class SoulDrainGui extends GuiContainer {
    	public int guiX = 256;

    	public int guiY = 256;
    	public KnowledgeGuiBase knowledge;
        public SoulDrainGui (InventoryPlayer inventoryPlayer,
                       InventorySoulDrain inv , KnowledgeGuiBase know) {
                //the container is instanciated and passed to the superclass for handling
                super(new ContainerSoulDrain(inventoryPlayer, inv));
                knowledge = know;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                //draws "Inventory" or your regional equivalent
            int x = (width/2) / 2;
            int y = (height/2 - 4) / 2;
            knowledge.renderGuiName(this, this.itemRender, this.fontRendererObj , guiX-234 , guiY-267);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
        	    ResourceLocation res = new ResourceLocation("quantacraft:textures/gui/Knowledge.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().getTextureManager().bindTexture(res);
                int x = (width - guiX) / 2;
                int y = (height - guiY) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, 256, 226);
                knowledge.renderGuiRecipe(this, this.itemRender, this.fontRendererObj , x , y);
               
       }
       
    
}
