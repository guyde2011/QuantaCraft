package QuantaCraft.Crafting.Table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.main.Knowledges;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class KnowledgeRecipesHandler {

	
	private Map<Integer, IConstructorCrafting> map = new HashMap<Integer,IConstructorCrafting>();
	private int keyAmount;
	
	public KnowledgeRecipesHandler(){
		keyAmount = 0;
	}
	
	public Map<Integer,IConstructorCrafting> getMap(){
		return map;
	}
	
	public void addRecipe(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,Item> RecipeMap = new HashMap<String,Item>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(Item) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,Item> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = new ItemStack(RecipeMap1.get(CurItem));
					}
				}
			}
		}
		map.put(keyAmount, new ShapedConstructorRecipe("ShapedRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addRecipe(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addRecipe(stack , new Knowledges[]{know} , Shape , params);
	}
	
	
	
	public void addRecipeStacks(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,ItemStack> RecipeMap = new HashMap<String,ItemStack>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(ItemStack) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,ItemStack> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = RecipeMap1.get(CurItem);
					}
				}
			}
		}

		map.put(keyAmount, new ShapedConstructorRecipe("ShapedRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addRecipeStacks(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addRecipeStacks(stack , new Knowledges[]{know} , Shape , params);
	}
	
	public void addShapelessRecipe(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,Item> RecipeMap = new HashMap<String,Item>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(Item) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,Item> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = new ItemStack(RecipeMap1.get(CurItem));
					}
				}
			}
		}
		map.put(keyAmount, new ShapelessConstructorRecipe("ShapelessRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addShapelessRecipe(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addShapelessRecipe(stack , new Knowledges[]{know} , Shape , params);
	}
	
	
	
	public void addShapelessRecipeStacks(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,ItemStack> RecipeMap = new HashMap<String,ItemStack>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(ItemStack) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,ItemStack> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = RecipeMap1.get(CurItem);
					}
				}
			}
		}

		map.put(keyAmount, new ShapelessConstructorRecipe("ShapedRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addShapelessRecipeStacks(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addShapelessRecipeStacks(stack , new Knowledges[]{know} , Shape , params);
	}
	
	public void addSecretRecipe(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,Item> RecipeMap = new HashMap<String,Item>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(Item) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,Item> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = new ItemStack(RecipeMap1.get(CurItem));
					}
				}
			}
		}
		map.put(keyAmount, new ShapeSecretConstructorRecipe("ShapedRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addSecretRecipe(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addSecretRecipe(stack , new Knowledges[]{know} , Shape , params);
	}
	
	
	
	public void addSecretRecipeStacks(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,ItemStack> RecipeMap = new HashMap<String,ItemStack>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(ItemStack) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,ItemStack> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = RecipeMap1.get(CurItem);
					}
				}
			}
		}

		map.put(keyAmount, new ShapeSecretConstructorRecipe("ShapedRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addSecretRecipeStacks(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addSecretRecipeStacks(stack , new Knowledges[]{know} , Shape , params);
	}
	
	public void addSecretShapelessRecipe(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,Item> RecipeMap = new HashMap<String,Item>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(Item) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,Item> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = new ItemStack(RecipeMap1.get(CurItem));
					}
				}
			}
		}
		map.put(keyAmount, new ShapelessSecretConstructorRecipe("ShapelessRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addSecretShapelessRecipe(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addSecretShapelessRecipe(stack , new Knowledges[]{know} , Shape , params);
	}
	
	
	
	public void addSecretShapelessRecipeStacks(ItemStack stack , Knowledges[] know ,String[] Shape , Object[] params){
		ItemStack[] ingridients = new ItemStack[9];
		Object[] cut = params;
		Map<String,ItemStack> RecipeMap = new HashMap<String,ItemStack>();
		while (cut.length>0){
			String CurItem = (String)cut[0];
			RecipeMap.put((String)cut[0],(ItemStack) cut[1]);
			cut = Arrays.copyOfRange(cut, 2, cut.length);
		}
		for (int i = 0; i<3;i++){
			Map<String,ItemStack> RecipeMap1 = RecipeMap;
			Iterator iter = RecipeMap.keySet().iterator();
			while (iter.hasNext()){
				String CurItem = (String) iter.next();
				for (int i1 = 0;i1<3;i1++){
					String spShape = "" + Shape[i].charAt(i1);
					if (spShape.equals(CurItem)){
						ingridients[3*i+i1] = RecipeMap1.get(CurItem);
					}
				}
			}
		}

		map.put(keyAmount, new ShapelessSecretConstructorRecipe("ShapedRecipe:" + keyAmount + ":" + stack ,stack ,know , ingridients));
		keyAmount++;
	}
	
	public void addSecretShapelessRecipeStacks(ItemStack stack , Knowledges know ,String[] Shape , Object[] params){
		addSecretShapelessRecipeStacks(stack , new Knowledges[]{know} , Shape , params);
	}
	

	
	
	
	public boolean checkRecipe(IInventory inv){
		boolean bool = true;
		for (int i = 0 ; i<keyAmount;i++){
			bool = bool && !map.get(i).matches(inv);
			if (!bool){
				bool = bool && !map.get(i).hasKnowledge(inv);
				if (!bool){
					break;
				}
			}
		}
		return !bool;
	}
	
	
	
	public ItemStack getRecipe(IInventory inv){

		for (int i = 0 ; i<keyAmount;i++){
			boolean bool = true;
			bool = bool && !map.get(i).matches(inv);
			if (!bool){
				bool = true && !map.get(i).hasKnowledge(inv);
				if (!bool){
					return map.get(i).getResult();
				}
			}
		}
		return null;
	}
	
}
