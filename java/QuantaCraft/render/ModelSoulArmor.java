package QuantaCraft.render;

import QuantaCraft.main.modMain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelSoulArmor extends ModelBiped
{
  //fields
   private static IModelCustom chest;
   ModelRenderer wingRight;
   ModelRenderer wingLeft;
   ModelRenderer bodyForward;
   ModelRenderer bodyBack;
   ModelRenderer head;
   ModelRenderer bodyLeft;
   ModelRenderer rightarm;
   ModelRenderer leftarm;
   ModelRenderer rightleg;
   ModelRenderer leftleg;
   ModelRenderer bodyRight;
 
 public ModelSoulArmor(float f)
 {
	 super(f,0,64,64);
   textureWidth = 64;
   textureHeight = 64;
	  float p_i1149_1_ = f;
	  float p_i1149_2_ = 0;
   this.bipedCloak = new ModelRenderer(this, 0, 0);
	  this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, p_i1149_1_);
	  this.bipedEars = new ModelRenderer(this, 24, 0);
	  this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i1149_1_);
	  this.bipedHead = new ModelRenderer(this, 0, 0);
	  this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_);
	  this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
	  this.bipedHeadwear = new ModelRenderer(this, 32, 0);
	  this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_ + 0.5F);
	  this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
	  this.bipedBody = new ModelRenderer(this, 16, 16);
	  this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1149_1_);
	  this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
	  this.bipedRightArm = new ModelRenderer(this, 40, 16);
	  this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
	  this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);
	  this.bipedLeftArm = new ModelRenderer(this, 40, 16);
	  this.bipedLeftArm.mirror = true;
	  this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
	  this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
	  this.bipedRightLeg = new ModelRenderer(this, 0, 16);
	  this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
	  this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + p_i1149_2_, 0.0F);
	  this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
	  this.bipedLeftLeg.mirror = true;
	  this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
	  this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + p_i1149_2_, 0.0F);
     wingRight = new ModelRenderer(this, 0, 47);
     wingRight.addBox(2F, 3F, -2F, 12, 4, 1);
     wingRight.setRotationPoint(0F, 0F, 0F);
     wingRight.setTextureSize(64, 64);
     wingRight.mirror = false;
     setRotation(wingRight, 0F, -2.80998F, 0F);
     wingRight.mirror = false;
     wingLeft = new ModelRenderer(this, 0, 47);
     wingLeft.addBox(2F, 3.033333F, 1F, 12, 4, 1);
     wingLeft.setRotationPoint(0F, 0F, 0F);
     wingLeft.setTextureSize(64, 64);
     wingLeft.mirror = true;
     setRotation(wingLeft, 0F, -0.3316126F, 0F);
     wingLeft.mirror = true;
     bodyForward = new ModelRenderer(this, 0, 32);
     bodyForward.addBox(-3.3F, 0F, -2F, 6, 14, 1);
     bodyForward.setRotationPoint(0F, 0F, 0F);
     bodyForward.setTextureSize(64, 64);
     bodyForward.mirror = true;
     setRotation(bodyForward, -0.1047198F, 0F, 0F);
     bodyBack = new ModelRenderer(this, 14, 32);
     bodyBack.addBox(-2F, 0F, 2F, 4, 11, 1);
     bodyBack.setRotationPoint(0F, 0F, 0F);
     bodyBack.setTextureSize(64, 64);
     bodyBack.mirror = true;
     setRotation(bodyBack, 0F, 0F, 0F);
     head = new ModelRenderer(this, 0, 0);
     head.addBox(-4F, -8F, -4F, 8, 8, 8);
     head.setRotationPoint(0F, 0F, 0F);
     head.setTextureSize(64, 64);
     head.mirror = true;
     setRotation(head, 0F, 0F, 0F);
     bodyLeft = new ModelRenderer(this, 26, 48);
     bodyLeft.addBox(-4F, 0F, -2F, 2, 12, 4);
     bodyLeft.setRotationPoint(6F, 0F, 0F);
     bodyLeft.setTextureSize(64, 64);
     bodyLeft.mirror = true;
     setRotation(bodyLeft, 0F, 0F, 0F);
     rightarm = new ModelRenderer(this, 40, 16);
     rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
     rightarm.setRotationPoint(-5F, 2F, 0F);
     rightarm.setTextureSize(64, 64);
     rightarm.mirror = true;
     setRotation(rightarm, 0F, 0F, 0F);
     leftarm = new ModelRenderer(this, 40, 16);
     leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
     leftarm.setRotationPoint(5F, 2F, 0F);
     leftarm.setTextureSize(64, 64);
     leftarm.mirror = true;
     setRotation(leftarm, 0F, 0F, 0F);
     rightleg = new ModelRenderer(this, 0, 16);
     rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
     rightleg.setRotationPoint(-2F, 12F, 0F);
     rightleg.setTextureSize(64, 64);
     rightleg.mirror = true;
     setRotation(rightleg, 0F, 0F, 0F);
     leftleg = new ModelRenderer(this, 0, 16);
     leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
     leftleg.setRotationPoint(2F, 12F, 0F);
     leftleg.setTextureSize(64, 64);
     leftleg.mirror = true;
     setRotation(leftleg, 0F, 0F, 0F);
     bodyRight = new ModelRenderer(this, 38, 48);
     bodyRight.addBox(-4F, 0F, -2F, 2, 12, 4);
     bodyRight.setRotationPoint(0F, 0F, 0F);
     bodyRight.setTextureSize(64, 64);
     bodyRight.mirror = true;
     if (f!=0.5){
     setRotation(bodyRight, 0F, 0F, 0F);
     bipedBody.addChild(bodyRight);
     bipedBody.addChild(bodyLeft);
     bipedBody.addChild(bodyForward);
     bipedBody.addChild(bodyBack);
     bipedBody.addChild(wingRight);
     bipedBody.addChild(wingLeft);
     }
   
 }
  

  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
