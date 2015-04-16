package QuantaCraft.Crafting.Table;

import java.util.Map;

import QuantaCraft.Crafting.ConstructorRecipeBase;
import QuantaCraft.main.Knowledges;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ShapelessSecretConstructorRecipe extends ConstructorRecipeBase{

	public ShapelessSecretConstructorRecipe(String recId, ItemStack stack,
			Knowledges[] knows, ItemStack[] ing) {
		super(recId, stack, knows, ing);
	}
	
	public ShapelessSecretConstructorRecipe(String recId, ItemStack stack,
			Knowledges knows, ItemStack[] ing) {
		super(recId, stack, knows, ing);
	}
	
	public ShapelessSecretConstructorRecipe(ItemStack stack,
			Knowledges knows, ItemStack[] ing) {
		super(stack, knows, ing);
	}
	

	@Override
	public boolean matches(IInventory inv) {
		for (int i = 0;i<9;i++){
			Map<Integer,ItemStack> ing = this.getRecipeIngridients();
			boolean bool = false;
			for (int j = 0;j<9;j++){
				
				if (ing.containsKey(j) && this.ItemStacksEqual(this.getRecipeIngridients().get(j),inv.getStackInSlot(i))){
					ing.remove(j);
					bool = true;
				}

			}
			if (!bool){
				return false;
			}
			if (i==8){
				return true;
			}
		}
		return false;
	}

	@Override
	public RecipeType getRecipeType() {
		return RecipeType.SHAPELESS_CANT_SEE;
	}

}
