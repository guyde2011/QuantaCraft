package QuantaCraft.render;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

public class ModelRenderer extends net.minecraft.client.model.ModelRenderer
{

	public ModelRenderer(ModelBase p_i1173_1_) {
		super(p_i1173_1_);
		// TODO Auto-generated constructor stub
	}
	
	public ModelRenderer(ModelBase p_i1173_1_, int a , int b) {
		super(p_i1173_1_ , a , b);
		// TODO Auto-generated constructor stub
	}
	
	public ModelRenderer(ModelBase p_i1172_1_, String p_i1172_2_) {
		super(p_i1172_1_, p_i1172_2_);
		// TODO Auto-generated constructor stub
	}

	public void render(float f1)
    {
		 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         GL11.glEnable(GL11.GL_BLEND);
		super.render(f1);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
    }
	   public void postRender(float f2){
			 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	         GL11.glEnable(GL11.GL_BLEND);
			super.postRender(f2);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
		   
	   }
}