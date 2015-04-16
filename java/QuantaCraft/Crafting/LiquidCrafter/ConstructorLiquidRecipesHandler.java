package QuantaCraft.Crafting.LiquidCrafter;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorFluidModifier;
import QuantaCraft.Items.IKnowledge;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.main.Knowledges;

public class ConstructorLiquidRecipesHandler {
	
	public List<ForgeRecipe> forge = new ArrayList<ForgeRecipe>();
	
	public void addRecipe(ItemStack[] ing , ItemStack out , int time , Knowledges know,Fluid liq){
	   forge.add(new ForgeRecipe(ing , out , time , know,liq).setShown(true));
	}
	public void addRecipe(ItemStack[] ing , ItemStack out , int time , Knowledges[] know,Fluid liq){
		forge.add(new ForgeRecipe(ing , out , time , know,liq).setShown(true));
	}
	
	public void addShapelessRecipe(ItemStack[] ing , ItemStack out , int time , Knowledges know,Fluid liq){
		forge.add(new ForgeRecipe(ing , out , time , know,liq).setShown(true));
		forge.add(new ForgeRecipe(new ItemStack[]{ing[0],ing[1]} , out , time , know,liq).setShown(false));
	}
	public void addShapelessRecipe(ItemStack[] ing , ItemStack out , int time , Knowledges[] know,Fluid liq){
		forge.add(new ForgeRecipe(ing , out , time , know,liq).setShown(true));
		forge.add(new ForgeRecipe(new ItemStack[]{ing[0],ing[1]} , out , time , know,liq).setShown(false));
	}
	
	private boolean areEqual(ItemStack stack1 , ItemStack stack2){
		if (stack1==null && stack1==stack2){
			return true;
		}
		if (stack1==null){
			return false;
		}
		if (stack2==null){
			return false;
		}
		return stack1.getItem()==stack2.getItem();

	}
	
	public boolean checkRecipe(TileEntityConstructorFluidModifier ent){
		boolean bool = true;
		for (int i = 0 ; i<forge.size();i++){
			ForgeRecipe cur = forge.get(i);
			Knowledges[] know = cur.getKnowledges();
			int v = know.length;
			for (Knowledges knows : know){
				if (ent.getStackInSlot(3)!=null && ent.getStackInSlot(3).getItem() instanceof IKnowledge){
					Knowledges[] Know = ItemKnowledge.getKnowledgesFromStack(ent.getStackInSlot(3));
					for (Knowledges Knows : Know){
						if (Knows==knows){
							v--;
							break;
						}
					}
				}
			}
			
			if (v==0){
	
			
			if (ent.tank.getFluid()!=null && ent.tank.getFluid().getFluid()==cur.getFluid() && ent.tank.getFluidAmount()>=cur.getLiquidAmount() && areEqual(cur.getInputA(),ent.getStackInSlot(0))){
				if (areEqual(cur.getInputB(),ent.getStackInSlot(1))){
					return true;
				}
			}
			
		}
		}
		return false;
	}
	
	public ForgeRecipe getCurResult(TileEntityConstructorFluidModifier ent){
		for (int i = 0 ; i<forge.size();i++){
			boolean bool = true;
			ForgeRecipe cur = forge.get(i);
			Knowledges[] know = cur.getKnowledges();
			int v = know.length;
			for (Knowledges knows : know){
				if (ent.getStackInSlot(3)!=null && ent.getStackInSlot(3).getItem() instanceof IKnowledge){
					Knowledges[] Know = ItemKnowledge.getKnowledgesFromStack(ent.getStackInSlot(3));
					for (Knowledges Knows : Know){
						if (Knows==knows){
							v--;
							break;
						}
					}
				}
			}
			
			if (v!=0){

				return null;
			}
			
			if (ent.tank.getFluid()!=null && ent.tank.getFluid().getFluid()==cur.getFluid() && ent.tank.getFluidAmount()>=cur.getLiquidAmount() && areEqual(cur.getInputA(),ent.getStackInSlot(0))){
				if (areEqual(cur.getInputB(),ent.getStackInSlot(1))){
					return cur;
				}
			}
			
		}
		return null;
	}
	
}
