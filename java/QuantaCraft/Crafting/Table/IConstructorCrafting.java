package QuantaCraft.Crafting.Table;

import java.util.HashMap;
import java.util.Map;

import QuantaCraft.main.Knowledges;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IConstructorCrafting {
	public HashMap<Integer,ItemStack> getRecipeIngridients();
	public RecipeType getRecipeType();
	public Knowledges[] getKnowledges();
	public ItemStack getResult();
	public String getId();
	public boolean matches(IInventory inv);
	public boolean hasKnowledge(IInventory inv);
}
