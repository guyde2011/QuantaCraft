package QuantaCraft.render;

import org.lwjgl.opengl.GL11;

import QuantaCraft.main.Sides;
import QuantaCraft.main.modMain;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
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
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;

public class HologramRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
	{
	public ModelHologramStand model = new ModelHologramStand();
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
		ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/hologramStand.png");
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);

		
		TileEntityHologramStand holo = ((TileEntityHologramStand)var1);
		holo.markDirty();
		if (holo.getStackInSlot(0)!=null || Sides.getBlock(holo, Sides.BOTTOM)==modMain.voidStorage || Sides.getBlock(holo, Sides.BOTTOM)==modMain.pickVoidStorage){

			ItemStack stack = holo.getItem();
		if (holo.renderItem()){
			GL11.glPopMatrix();
			GL11.glPushMatrix();
		EntityItem entItem = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, stack);
		//Without the below line, the item will spazz out
		entItem.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		RenderManager r = RenderManager.instance;
		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.65F, (float)z + 0.5F);
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		double xDis = p.posX-(holo.xCoord+0.5);
		double zDis = p.posZ-(holo.zCoord+0.5);
		double angle = 180 * Math.atan2(xDis, zDis)/Math.PI + 180;
		GL11.glRotatef((float) angle, 0, 1, 0);
		
		r.renderEntityWithPosYaw(entItem,0 , 0 , 0 , 0, 0);
		
		RenderItem.renderInFrame = false;

		}
		if (holo.showText()){
			GL11.glPopMatrix();
			String text = holo.getText();
			int lines = 1;
		
		GL11.glPushMatrix();
        
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.3F, (float)z + 0.5F);
		GL11.glNormal3f(0, 1, 0);
		GL11.glRotatef(-RenderManager.instance.playerViewY, 0, 1, 0);
		GL11.glRotatef(RenderManager.instance.playerViewX, 1, 0, 0);
		GL11.glScalef(-0.016666668F, -0.016666668F, 0.016666668F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);				
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setColorRGBA_F(0, 0, 0, 0.25F);
		Tessellator.instance.addVertex(-RenderManager.instance.getFontRenderer().getStringWidth(text) / 2 - 1, -1, 0);
		Tessellator.instance.addVertex(-RenderManager.instance.getFontRenderer().getStringWidth(text) / 2 - 1, 2 + 8 * lines, 0);
		Tessellator.instance.addVertex(RenderManager.instance.getFontRenderer().getStringWidth(text) / 2 + 1, 2 + 8 * lines, 0);
		Tessellator.instance.addVertex(RenderManager.instance.getFontRenderer().getStringWidth(text) / 2 + 1, -1, 0);
		Tessellator.instance.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		RenderManager.instance.getFontRenderer().drawString(text, -RenderManager.instance.getFontRenderer().getStringWidth(text) / 2, 0 , 553648127);		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		if (holo.getColor().substring(2).equals("")){
			RenderManager.instance.getFontRenderer().drawString(text, -RenderManager.instance.getFontRenderer().getStringWidth(text) / 2, 0, -1);
		} else {
			RenderManager.instance.getFontRenderer().drawString(text, -RenderManager.instance.getFontRenderer().getStringWidth(text) / 2, 0, (int) Long.parseLong(holo.getColor().substring(2),16));
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1, 1, 1, 1);
		}
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
			ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/hologramStand.png");
          Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef(0F, 1F, 0.0F);
        GL11.glRotatef(180,0F, 0F, 1F);
        GL11.glScalef(1F, 1F, 1F);


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
		return 161;
	}
	
}
