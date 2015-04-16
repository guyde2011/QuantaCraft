package QuantaCraft.main;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ReflexioAspect extends Aspect{

	public ReflexioAspect(){
		super("Reflexio",0xb5b5cd, new Aspect[]{Aspect.LIGHT,Aspect.ORDER},new ResourceLocation(modMain.modid.toLowerCase() ,"textures/aspects/Reflection.png"),1);
		
	}
	
}
