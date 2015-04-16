package QuantaCraft.neiRecipes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import QuantaCraft.Crafting.LiquidCrafter.ForgeRecipe;
import QuantaCraft.Crafting.Table.IConstructorCrafting;
import QuantaCraft.Crafting.Table.RecipeType;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.main.Knowledges;
import QuantaCraft.main.modMain;
import QuantaCraft.neiRecipes.ConstructorShapelessRecipe.CachedConstructorShapelessRecipe;
import QuantaCraft.slots.KnowledgeSlot;
import QuantaCraft.slots.SlotLiqOutput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;


public class ConstructorLiquidRecipe extends TemplateRecipeHandler{


	public String getRecipeName(){
		return "Liquid Modify";
	}
	public String getOverlayIdentifier(){
		return "LiquidConst";
	}
	
	public String getGuiTexture(){
		return "quantacraft:textures/gui/ConstructorLiquidTableNei.png";
	}
	public String getRecipeId(){
		return "QuantCraft.consTableLiquid";
	}
	 @Override
	 public int recipiesPerPage() {
		 return 1;
	 }
	
	
	
	public class CachedConstructorLiquidRecipe extends TemplateRecipeHandler.CachedRecipe{
		public List<PositionedStack> recInputs = new ArrayList<PositionedStack>();
		public PositionedStack Knowledge;
		public PositionedStack recOutput;
		public int LiquidAmount;
		public Fluid Liquid;
		public int rY = -21;
		public int rX = -4;
		
		public CachedConstructorLiquidRecipe(ForgeRecipe recipe){
			super();
			
			this.LiquidAmount = recipe.getLiquidAmount();
			this.Liquid = recipe.getFluid();
			if (recipe.getInputA()!=null){
				recInputs.add(new PositionedStack(recipe.getInputA(),50+rX, 35+rY));
			}
			if (recipe.getInputB()!=null){
				recInputs.add(new PositionedStack(recipe.getInputB(),107+rX, 35+rY));
			}
            //addSlotToContainer(new Slot(te,0,50, 35));
            //addSlotToContainer(new Slot(te,1,107, 35));
            //addSlotToContainer(new SlotLiqOutput(te,2,79,72));
            //addSlotToContainer(new KnowledgeSlot(te,3 ,79,22));

			recOutput = new PositionedStack(recipe.getResult(),79+rX,65+rY);
			Knowledge = new PositionedStack(KnowledgeBook.addKnowledgeForStack(new ItemStack(modMain.knowBook),recipe.getKnowledges()),79+rX,22+rY);
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
			for(int i =0;i<modMain.fRecipes.forge.size();i++){
				ForgeRecipe entry = modMain.fRecipes.forge.get(i);
				if (entry.showInNEI()){
					this.arecipes.add(new CachedConstructorLiquidRecipe(entry));
				}
			}
		} else {
			super.loadCraftingRecipes(Id, results);
		}
	}
	
	public void loadCraftingRecipes(ItemStack result){
	
		for (int i =0;i<modMain.fRecipes.forge.size();i++){
			ForgeRecipe recipe = modMain.fRecipes.forge.get(i);
			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getResult(), result) && recipe.showInNEI()){
				this.arecipes.add(new CachedConstructorLiquidRecipe(recipe));
			}
		}
	}
	
	public void loadUsageRecipes(ItemStack ingredient){
		for (int i =0;i<modMain.fRecipes.forge.size();i++){
			ForgeRecipe recipe = modMain.fRecipes.forge.get(i);
				if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getInputA(), ingredient) && recipe.showInNEI()){
					this.arecipes.add(new CachedConstructorLiquidRecipe(recipe));
				} else if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getInputB(), ingredient) && recipe.showInNEI()){
					this.arecipes.add(new CachedConstructorLiquidRecipe(recipe));
				}
			}
		
		for (int i =0;i<modMain.fRecipes.forge.size();i++){
			ForgeRecipe recipe = modMain.fRecipes.forge.get(i);
			Knowledges[] ing = recipe.getKnowledges();
			if (ingredient.getItem() instanceof ItemKnowledge){
				Knowledges know = ItemKnowledge.getKnowledgesFromStack(ingredient)[0];
				for (int t = 0;t<ing.length;t++){
					if (ing[t]==know  && recipe.showInNEI()){
						this.arecipes.add(new CachedConstructorLiquidRecipe(recipe));
						break;
					}
				}
			}
		}
	}
	public void loadTransferRects(){
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 25, 16), getRecipeId(), new Object[0]));
	}
	public void drawExtras(int recipe) {
		CachedConstructorLiquidRecipe rec = (CachedConstructorLiquidRecipe) this.arecipes.get(recipe);
	
		int liqAmount = 1+(int)(((float)rec.LiquidAmount)*59f/5000f);
		Fluid flu = rec.Liquid;
		int a =0;
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		for (int i = 0;i<4;i++){ 
        	a = Math.min(15 , liqAmount-i*15);       	
        	if (!(liqAmount-i*15<0)){
        		new RenderItem().renderIcon( 147 + rec.rX,  83 - i * 15 - a + rec.rY, flu.getStillIcon(), 16 , a);
        	}
        	
        }
	}
}