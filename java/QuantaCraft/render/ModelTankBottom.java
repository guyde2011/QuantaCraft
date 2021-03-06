package QuantaCraft.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

// Date: 16/11/2014 20:46:07
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX







public class ModelTankBottom extends ModelBase
{
  //fields
    ModelRenderer Bottom;
    ModelRenderer Tend1;
    ModelRenderer Tend2;
    ModelRenderer Tend3;
    ModelRenderer Tend4;
  
  public ModelTankBottom()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Bottom = new ModelRenderer(this, 0, 0);
      Bottom.addBox(0F, 0F, 0F, 16, 2, 16);
      Bottom.setRotationPoint(-8F, 8F, -8F);
      Bottom.setTextureSize(64, 64);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Tend1 = new ModelRenderer(this, 0, 0);
      Tend1.addBox(0F, 0F, 0F, 1, 14, 1);
      Tend1.setRotationPoint(-8F, 10F, -8F);
      Tend1.setTextureSize(64, 64);
      Tend1.mirror = true;
      setRotation(Tend1, 0F, 0F, 0F);
      Tend2 = new ModelRenderer(this, 0, 0);
      Tend2.addBox(0F, 0F, 0F, 1, 14, 1);
      Tend2.setRotationPoint(-8F, 10F, 7F);
      Tend2.setTextureSize(64, 64);
      Tend2.mirror = true;
      setRotation(Tend2, 0F, 0F, 0F);
      Tend3 = new ModelRenderer(this, 0, 0);
      Tend3.addBox(0F, 0F, 0F, 1, 14, 1);
      Tend3.setRotationPoint(7F, 10F, 7F);
      Tend3.setTextureSize(64, 64);
      Tend3.mirror = true;
      setRotation(Tend3, 0F, 0F, 0F);
      Tend4 = new ModelRenderer(this, 0, 0);
      Tend4.addBox(0F, 0F, 0F, 1, 14, 1);
      Tend4.setRotationPoint(7F, 10F, -8F);
      Tend4.setTextureSize(64, 64);
      Tend4.mirror = true;
      setRotation(Tend4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Bottom.render(f5);
    Tend1.render(f5);
    Tend2.render(f5);
    Tend3.render(f5);
    Tend4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
