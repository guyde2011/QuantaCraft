package QuantaCraft.render;
import org.lwjgl.opengl.GL11;

import QuantaCraft.main.modMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemHologramRenderer implements IItemRenderer{
	        private ModelHologramStand model;
	       
	        public RenderItemHologramRenderer() {
	                this.model = new ModelHologramStand();
	        }
	        

	       


			@Override
			public boolean handleRenderType(ItemStack item, ItemRenderType type) {
				 switch(type)
				  {
				  case EQUIPPED:return true;
				  case EQUIPPED_FIRST_PERSON: return true;
					  
				  
				  default: return false;
				
			}
			}
			@Override
			public boolean shouldUseRenderHelper(ItemRenderType type,
					ItemStack item, ItemRendererHelper helper) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void renderItem(ItemRenderType type, ItemStack item,
					Object... data) {
				ResourceLocation textures = new ResourceLocation(modMain.modid.toLowerCase() ,"textures/models/hologramStand.png");
			   
	     //Use in 1.6.2  this
				  switch(type)
				  {
				  case EQUIPPED:{
					  GL11.glPushMatrix();
					  
			            Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			          GL11.glTranslatef(0.3F, 0.8F, 0.0F);
		              GL11.glRotatef(-140,0F, 0F, 1F);
		              GL11.glScalef(1F, 1F, 1F);
		     

		              model.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);

				                GL11.glPopMatrix();

				  }
				  case EQUIPPED_FIRST_PERSON:{
					 GL11.glPushMatrix();
					  
			            Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			             GL11.glTranslatef(0.3F, 0.8F, 0.0F);
			              GL11.glRotatef(-140,0F, 0F, 1F);
			              GL11.glScalef(1F, 1F, 1F);
			              model.render(null, 0, 0, -0.1F, 0, 0, 0.0625f);
				        
				               GL11.glPopMatrix();
				  }
				  default: break;
				  }
	            
				
			}
	}

