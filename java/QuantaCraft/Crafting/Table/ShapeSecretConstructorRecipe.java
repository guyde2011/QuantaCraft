package QuantaCraft.Crafting.Table;

import QuantaCraft.Crafting.ConstructorRecipeBase;
import QuantaCraft.main.Knowledges;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ShapeSecretConstructorRecipe extends ConstructorRecipeBase{

	public ShapeSecretConstructorRecipe(String recId, ItemStack stack,
			Knowledges[] knows, ItemStack[] ing) {
		super(recId, stack, knows, ing);
	}
	
	public ShapeSecretConstructorRecipe(String recId, ItemStack stack,
			Knowledges knows, ItemStack[] ing) {
		super(recId, stack, knows, ing);
	}
	
	public ShapeSecretConstructorRecipe(ItemStack stack,
			Knowledges knows, ItemStack[] ing) {
		super(stack, knows, ing);
	}
	

	@Override
	public boolean matches(IInventory inv) {
		for (int i = 0;i<9;i++){
			if (!this.ItemStacksEqual(this.getRecipeIngridients().get(i),inv.getStackInSlot(i))){
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
		return RecipeType.SHAPED_CANT_SEE;
	}

}
