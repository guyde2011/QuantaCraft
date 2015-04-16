package QuantaCraft.Crafting.LiquidCrafter;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import QuantaCraft.main.Knowledges;

public class ForgeRecipe {
	
	private ItemStack result;
	private ItemStack inputA;
	private ItemStack inputB;
	private int liqAmount;
	private Fluid fluid;
	private Knowledges[] knows;
	private boolean shown;
	
	public ForgeRecipe(ItemStack[] ing , ItemStack out , int time , Knowledges know , Fluid liquid){
	    this(ing,out,time,new Knowledges[]{know},liquid);
	}
	
	public ForgeRecipe(ItemStack[] ing , ItemStack out , int time , Knowledges[] know , Fluid liquid){
		inputA = ing[0];
		inputB = ing[1];
		result = out;
		liqAmount = time;
		knows = know;
		fluid = liquid;
	}
	
	public ForgeRecipe setShown(boolean bool){
		this.shown = bool;
		return this;
	}
	
	
	
	public Knowledges[] getKnowledges(){
		return knows;
	}
	public ItemStack getResult(){
		return result;
	}
	public ItemStack getInputA(){
		return inputA;
	}
	public ItemStack getInputB(){
		return inputB;
	}
	public int getLiquidAmount(){
		return liqAmount;
	}
	public Fluid getFluid(){
		return fluid;
	}
	public boolean showInNEI(){
		return shown;
	}
	
}
