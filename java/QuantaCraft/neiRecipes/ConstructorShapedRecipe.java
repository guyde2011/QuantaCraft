package QuantaCraft.neiRecipes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import QuantaCraft.Crafting.Table.IConstructorCrafting;
import QuantaCraft.Crafting.Table.RecipeType;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.main.Knowledges;
import QuantaCraft.main.modMain;
import QuantaCraft.neiRecipes.ConstructorShapelessRecipe.CachedConstructorShapelessRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;


public class ConstructorShapedRecipe extends TemplateRecipeHandler{


	public String getRecipeName(){
		return "Shaped Construct";
	}
	public String getOverlayIdentifier(){
		return "Constructortable";
	}
	
	public String getGuiTexture(){
		return "quantacraft:textures/gui/ConstructorTableNei.png";
	}
	public String getRecipeId(){
		return "QuantCraft.consTable";
	}
	
	
	
	
	public class CachedConstructorRecipe extends TemplateRecipeHandler.CachedRecipe{
		public List<PositionedStack> recInputs = new ArrayList<PositionedStack>();
		public PositionedStack Knowledge;
		public PositionedStack recOutput;
		
		public CachedConstructorRecipe(IConstructorCrafting recipe){
			super();

			for (int i = 0;i<recipe.getRecipeIngridients().keySet().size()/3;i++){
				for (int j = 0;j<3;j++){
					if (recipe.getRecipeIngridients().get(3*i +j)!=null){
						recInputs.add(new PositionedStack(recipe.getRecipeIngridients().get(3*i +j),72-50 + j * 18, 8 + i * 18 ));
					}
				}
			}
			recOutput = new PositionedStack(recipe.getResult(),116,26);
			Knowledge = new PositionedStack(KnowledgeBook.addKnowledgeForStack(new ItemStack(modMain.knowBook),recipe.getKnowledges()),1,25);
			recInputs.add(Knowledge);
		}
		
		public List<PositionedStack> getIngredients()
		{
		    return this.recInputs;
		}
		
		@Override
		public PositionedStack getResult() {
			// TODO Auto-generated method stub
			return recOutput;
		}
	}
	public void loadCraftingRecipes(String Id, Object... results){
		if (Id.equals(getRecipeId())){
			for(Map.Entry<Integer, IConstructorCrafting> entry:modMain.nRecipes.getMap().entrySet()){
				if (entry.getValue().getRecipeType()==RecipeType.SHAPED){
					this.arecipes.add(new CachedConstructorRecipe(entry.getValue()));
				}
			}
		} else {
			super.loadCraftingRecipes(Id, results);
		}
	}
	
	public void loadCraftingRecipes(ItemStack result){
		Map<Integer,IConstructorCrafting> map = modMain.nRecipes.getMap();
		for (int i =0;i<map.keySet().size();i++){
			IConstructorCrafting recipe = map.get(i);
			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getResult(), result) && recipe.getRecipeType()==RecipeType.SHAPED){
				this.arecipes.add(new CachedConstructorRecipe(recipe));
			}
		}
	}
	
	public void loadUsageRecipes(ItemStack ingredient){
		Map<Integer,IConstructorCrafting> map = modMain.nRecipes.getMap();
		for (int i =0;i<map.keySet().size();i++){
			IConstructorCrafting recipe = map.get(i);
			Map<Integer,ItemStack> ing = recipe.getRecipeIngridients(); 
			for (int j = 0;j<ing.keySet().size();j++){
				if (NEIServerUtils.areStacksSameTypeCrafting(ing.get(j), ingredient) && recipe.getRecipeType()==RecipeType.SHAPED){
					this.arecipes.add(new CachedConstructorRecipe(recipe));
					break;
				}
			}
		}
		for (int i =0;i<map.keySet().size();i++){
			IConstructorCrafting recipe = map.get(i);
			Knowledges[] ing = recipe.getKnowledges();
			if (ingredient.getItem() instanceof ItemKnowledge){
				Knowledges know = ItemKnowledge.getKnowledgesFromStack(ingredient)[0];
				for (int t = 0;t<ing.length;t++){
					if (ing[t]==know  && recipe.getRecipeType()==RecipeType.SHAPED){
						this.arecipes.add(new CachedConstructorRecipe(recipe));
						break;
					}
				}
			}
		}
	}
	public void loadTransferRects(){
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 25, 16), getRecipeId(), new Object[0]));
	}
}