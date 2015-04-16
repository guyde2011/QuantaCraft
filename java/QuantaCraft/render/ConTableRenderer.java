package QuantaCraft.render;


import org.lwjgl.opengl.GL11;

import QuantaCraft.main.Sides;
import QuantaCraft.main.modMain;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloColorControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloMultiControl;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;

public class ConTableRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
	{
	public ConTableModel model = new ConTableModel();
    int a = 0;
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y,
			double z, float var8) {
		
		GL11.glPushMatrix();
        ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/ConstructorTable.png");
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        if (var1!=null && ((TileEntityConstructorTable)var1).getWorldObj()!=null && ((TileEntityConstructorTable)var1).getStackInSlot(10)!=null){
        	model.renderWithBook(null, 0, 0, -0.1F, 0, 0, 0.0625f);
        } else {
        	model.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);
        }

        TileEntityRendererDispatcher ted = TileEntityRendererDispatcher.instance;
 
		GL11.glPopMatrix();

	}
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        //float brightness = block.getBlockBrightness(world, i, j, k);
        //As of MC 1.7+ block.getBlockBrightness() has become block.getLightValue():
        float brightness = block.getLightValue(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
}
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		  GL11.glPushMatrix();
			ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/ConstructorTable.png");
          Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef(0F, 1F, 0.0F);
        GL11.glRotatef(180,0F, 0F, 1F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);


        model.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);

	                GL11.glPopMatrix();

	  }
		
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 163;
	}
	
}
