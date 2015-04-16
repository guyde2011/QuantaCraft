package QuantaCraft.render;

import org.lwjgl.opengl.GL11;

import QuantaCraft.main.Interfaces.ICable;
import QuantaCraft.main.Sides;
import QuantaCraft.main.modMain;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloColorControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloMultiControl;
import QuantaCraft.Blocks.TileEntities.TileEntityBasicCable;

public class BasicCableRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
	{
	public CableModel model = new CableModel();

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
		ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/" + ((ICable)var1).getTexture() + ".png");
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        TileEntityBasicCable cable = (TileEntityBasicCable) var1;
        int f = 0;
        boolean none = false;
        for (Sides side : Sides.values()){
        	if (Sides.getBlock(var1, side).hasTileEntity() && ((ICable)var1).CanConnect(Sides.getTileEntity(var1, side))){
        		
        		GL11.glPushMatrix();
        		GL11.glScalef(0.5f, 0.5f, 0.5f);
        		model.renderCable(f,null, 0, 0, -0.1F, 0, 0, 0.0625f);
        		
        		none = true;
        		GL11.glPopMatrix();
        	}
        	f++;
        }
    		GL11.glPushMatrix();
    		GL11.glScalef(0.5f, 0.5f, 0.5f);
    		model.renderCable(6,null, 0, 0, -0.1F, 0, 0, 0.0625f);
    		GL11.glPopMatrix();
        if (!none){
    		GL11.glPushMatrix();
    		GL11.glScalef(0.5f, 0.5f, 0.5f);
    		model.renderCable(0,null, 0, 0, -0.1F, 0, 0, 0.0625f);
    		model.renderCable(6,null, 0, 0, -0.1F, 0, 0, 0.0625f);
    		none = true;
    		GL11.glPopMatrix();
        }
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
			ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/" + ((ICable)block).getTexture() +  ".png");
          Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef(0F, 1F, 0.0F);
        GL11.glRotatef(180,0F, 0F, 1F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        model.renderCable(2,null, 0, 0, -0.1F, 0, 0, 0.0625f);
        model.renderCable(3,null, 0, 0, -0.1F, 0, 0, 0.0625f);
        model.renderCable(6,null, 0, 0, -0.1F, 0, 0, 0.0625f);
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
		return 162;
	}
	
}
