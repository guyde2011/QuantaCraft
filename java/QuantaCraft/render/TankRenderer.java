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
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloColorControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloMultiControl;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityTank;

public class TankRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
	{
	
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
    ModelTank tank = new ModelTank();
    ModelTankTop tankBlockTop = new ModelTankTop();
    ModelTankBottom tankBlockBottom = new ModelTankBottom();
    ModelTankFull tankBlockFull = new ModelTankFull();
    ModelTankNope tankBlockNope = new ModelTankNope();
    
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y,
			double z, float var8) {
		
		GL11.glPushMatrix();
        ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/Tank.png");
       Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
     
        	
        TileEntityTank ent = ((TileEntityTank)var1);
        TileEntityTank ent1 = ((TileEntityTank)var1);
        TileEntityRendererDispatcher ted = TileEntityRendererDispatcher.instance;
        RenderBlocks rend = RenderBlocks.getInstance();
        TileEntityTank tankModel =  ((TileEntityTank)var1);
       
        if (!tankModel.near){
      if (tankModel.isUpTank()){
    		if (tankModel.isDownTank()){
    			 tankBlockNope.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);
    		} else {
    			 tankBlockTop.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);
    			 GL11.glScalef(1, 0.99F, 1);
    			 
    		}
    	} else if (tankModel.isDownTank()){
			 tankBlockBottom.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);;
			 GL11.glScalef(1, 0.99F, 1);
			 GL11.glTranslatef(0, 0.01f, 0);
    	} else {
    		 tankBlockFull.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);
			 GL11.glScalef(1, 0.95F, 1);
			 GL11.glTranslatef(0, 0.05f, 0);
    	}
        } else if (tankModel.isUpTank()){
        	tankBlockNope.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);
        }else
        {
        	 tankBlockBottom.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);;
        }
        int i = 1;
        int amount = 0;
        if (ent.tank.getFluid()!=null){
        	amount += ent.tank.getFluidAmount();
        }
        while (ent1.isUpTank()){
        	ent1 = (TileEntityTank)ent1.getWorldObj().getTileEntity(ent1.xCoord, ent1.yCoord + 1, ent1.zCoord);
        	i++;
            if (ent1.tank.getFluid()!=null && ent.tank.getFluid() == ent.tank.getFluid()){
            	amount += ent1.tank.getFluidAmount();
            }
        }
        	if (ent.getWorldObj()!=null && ent.renderNormally && ent.tank.getFluid() != null && ((TileEntityTank)var1).tank.getFluid().getFluid()!=null){
        		GL11.glTranslatef(0F, 1F, 0.0F);
        		renderFluid(((TileEntityTank)var1), tankModel.tank.getFluid() , (float)((TileEntityTank)var1).tank.getCapacity());
        	
        }


		GL11.glPopMatrix();

	}
	
	public void renderFluid( TileEntityTank tank,FluidStack fluidStack , float maxAmount){
		GL11.glPushMatrix();
    	GL11.glScalef(0.875f,1f,0.875f);
		if (fluidStack != null) {
		GL11.glDisable(GL11.GL_LIGHTING);
		final Fluid fluid = fluidStack.getFluid();
		IIcon texture = fluid.getStillIcon();
		final int color;
		if (texture != null) {
			bindTexture(getFluidSheet(fluid));
			color = fluid.getColor(fluidStack);
		} else {
			bindTexture(TextureMap.locationBlocksTexture);
			texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("missingno");
			color = 0xFFFFFFFF;
		}
		Tessellator t = Tessellator.instance;
		final double Height = ((double)fluidStack.amount / (double)maxAmount);
		final double uMin = texture.getInterpolatedU(0.1);
		final double uMax = texture.getInterpolatedU(16);
		final double vMin = texture.getInterpolatedV(0.1);
		final double vMax = texture.getInterpolatedV(16);
		final double vHeight = vMax - vMin;
		if (Height !=1){
		GL11.glTranslatef(0F, (float) (1f-Height), 0.0F);
		}
		final float r = (color >> 16 & 0xFF) / 255.0F;
		final float g = (color >> 8 & 0xFF) / 255.0F;
		final float b = (color & 0xFF) / 255.0F;
		// north side
		//adjustLightFixture(tank.getWorldObj(),tank.xCoord,tank.yCoord,tank.zCoord, tank.getBlockType());
		t.startDrawingQuads();
		t.setColorOpaque_F(r, g, b);
		t.addVertexWithUV(0.5, -0.5, -0.5, uMax, vMin); // bottom
		t.addVertexWithUV(-0.5, -0.5, -0.5, uMin, vMin); // bottom
		// top north/west
		t.addVertexWithUV(-0.5, -0.5 + Height, -0.5, uMin, vMin + (vHeight * Height));
		// top north/east
		t.addVertexWithUV(0.5, -0.5 + Height, -0.5, uMax, vMin + (vHeight * Height));
		// south side
		t.addVertexWithUV(0.5, -0.5, 0.5, uMin, vMin);
		// top south east
		t.addVertexWithUV(0.5, -0.5 + Height, 0.5, uMin, vMin + (vHeight * Height));
		// top south west
		t.addVertexWithUV(-0.5, -0.5 + Height, 0.5, uMax, vMin + (vHeight * Height));
		t.addVertexWithUV(-0.5, -0.5, 0.5, uMax, vMin);
		// east side
		t.addVertexWithUV(0.5, -0.5, -0.5, uMin, vMin);
		// top north/east
		t.addVertexWithUV(0.5, -0.5 + Height, -0.5, uMin, vMin + (vHeight * Height));
		// top south/east
		t.addVertexWithUV(0.5, -0.5 + Height, 0.5, uMax, vMin + (vHeight * Height));
		t.addVertexWithUV(0.5, -0.5, 0.5, uMax, vMin);
		// west side
		t.addVertexWithUV(-0.5, -0.5, 0.5, uMin, vMin);
		// top south/west
		t.addVertexWithUV(-0.5, -0.5 + Height, 0.5, uMin, vMin + (vHeight * Height));
		// top north/west
		t.addVertexWithUV(-0.5, -0.5 + Height, -0.5, uMax, vMin + (vHeight * Height));
		t.addVertexWithUV(-0.5, -0.5, -0.5, uMax, vMin);
		// top
		// south east
		t.addVertexWithUV(0.5, -0.5 + Height, 0.5, uMax, vMin);
		// north east
		t.addVertexWithUV(0.5, -0.5 + Height, -0.5, uMin, vMin);
		// north west
		t.addVertexWithUV(-0.5, -0.5 + Height, -0.5, uMin, vMax);
		// south west
		t.addVertexWithUV(-0.5, -0.5 + Height, 0.5, uMax, vMax);
		// bottom
		t.addVertexWithUV(0.5, -0.5, -0.5, uMax, vMin);
		t.addVertexWithUV(0.5, -0.5, 0.5, uMin, vMin);
		t.addVertexWithUV(-0.5, -0.5, 0.5, uMin, vMax);
		t.addVertexWithUV(-0.5, -0.5, -0.5, uMax, vMax);
		t.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		}
		// may be disabled by other procedures
		GL11.glPopMatrix();
	}
	public static ResourceLocation getFluidSheet(FluidStack liquid) {
		if (liquid == null) return TextureMap.locationBlocksTexture;
		return getFluidSheet(liquid.getFluid());
	}
	/**
	* @param liquid
	*/
	public static ResourceLocation getFluidSheet(Fluid liquid) {
		return TextureMap.locationBlocksTexture;
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
			ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/Tank.png");
          Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glTranslatef(0F, 1F, 0.0F);
        GL11.glRotatef(180,0F, 0F, 1F);


        tank.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);

	                GL11.glPopMatrix();

	  }
		
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 165;
	}
	
}
