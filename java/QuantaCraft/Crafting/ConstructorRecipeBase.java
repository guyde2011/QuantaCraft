package QuantaCraft.Crafting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import QuantaCraft.Crafting.Table.IConstructorCrafting;
import QuantaCraft.Crafting.Table.RecipeType;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.main.Knowledges;

public abstract class ConstructorRecipeBase implements IConstructorCrafting{
	
	private HashMap<Integer, ItemStack> recipe = new HashMap<Integer , ItemStack>();
	private String id;
	private Knowledges[] know;
	private ItemStack result;
	
	public String getId(){
		return id;
	}
	
	public boolean hasKnowledge(IInventory inv){
		ItemStack stack = inv.getStackInSlot(10);
		if (stack == null || stack.stackTagCompound == null || !stack.stackTagCompound.hasKey("KnowledgesForQuanta")){
			return false;
		} else {
			Knowledges[] knows =  ItemKnowledge.getKnowledgesFromStack(stack);
			int length = know.length;
			for (int i = 0;i<knows.length;i++){
				for (int j = 0;j<know.length;j++){
					if(know[j]==knows[i]){
						length--;
						break;
					}
				}
			}
			return length==0;
		}
	}
	
	public ConstructorRecipeBase(String recId , ItemStack stack , Knowledges[] knows , ItemStack[] ing ){
		for (int i = 0;i<ing.length;i++){
			recipe.put(i, ing[i]);
		}
		this.id = recId;
		result = stack;
		know = knows;
	}
	
	public ConstructorRecipeBase(String recId , ItemStack stack , Knowledges knows , ItemStack[] ing ){
		this(recId , stack , new Knowledges[]{knows} , ing);
	}
	
	public ConstructorRecipeBase(ItemStack stack , Knowledges knows , ItemStack[] ing ){
		this("" + knows + stack , stack , new Knowledges[]{knows} , ing);
	}
	
	public abstract boolean matches(IInventory inv);
	
	@Override
	public HashMap<Integer, ItemStack> getRecipeIngridients() {
		return (HashMap<Integer, ItemStack>) recipe.clone();
	}

	public abstract RecipeType getRecipeType();

	@Override
	public Knowledges[] getKnowledges() {
		// TODO Auto-generated method stub
		return know.clone();
	}

	@Override
	public ItemStack getResult() {
		// TODO Auto-generated method stub
		return result.copy();
	}
	
	public static boolean ItemStacksEqual(ItemStack stack , ItemStack stack1){
		return (stack==null && stack1==null) ||(stack!=null && stack1!=null && stack.getItem()==stack1.getItem() && stack.getItemDamage() == stack1.getItemDamage());
	}
	
	public static boolean stacksCanStack(ItemStack stack , ItemStack stack1){
		return (stack==null && stack1==null)||(stack!=null && stack1!=null &&stack.getItem()==stack1.getItem() && stack.getItemDamage() == stack1.getItemDamage() && ItemStack.areItemStackTagsEqual(stack, stack1));
	}

}
