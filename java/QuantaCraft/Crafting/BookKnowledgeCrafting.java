package QuantaCraft.Crafting;

import java.lang.reflect.Field;
import java.util.Random;

import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.main.Knowledges;
import QuantaCraft.main.modMain;

import com.google.common.base.Throwables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class BookKnowledgeCrafting implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		boolean bool = false;
		boolean hasBook = false;
		
		for (int i=0;i<inv.getSizeInventory();i++){
			if (inv.getStackInSlot(i)!=null && !(inv.getStackInSlot(i).getItem() instanceof ItemKnowledge||inv.getStackInSlot(i).getItem() instanceof KnowledgeBook)){
				return false;
			}
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof ItemKnowledge)){
				bool = true;
			}
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof KnowledgeBook)){
				if (hasBook){
					return false;
				} else {
					hasBook = true;
				}
			}
			if (i==inv.getSizeInventory()-1&&bool&&hasBook){
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack stack = new ItemStack(modMain.knowBook);

		int length = 0;
		for (int i=0;i<inv.getSizeInventory();i++){
			
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof ItemKnowledge)){
				length++;
			}
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof KnowledgeBook)){
				length+= ItemKnowledge.getKnowledgesFromStack(inv.getStackInSlot(i)).length;
			}
		}
		Knowledges[] know = new Knowledges[length];
		int values = 0;
		for (int i=0;i<inv.getSizeInventory();i++){
			
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof ItemKnowledge)){
				know[values] = ItemKnowledge.getKnowledgesFromStack(inv.getStackInSlot(i))[0];
				values++;
			}
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof KnowledgeBook)){
				for (int u = 0;u<ItemKnowledge.getKnowledgesFromStack(inv.getStackInSlot(i)).length;u++){
					know[values] = ItemKnowledge.getKnowledgesFromStack(inv.getStackInSlot(i))[u];
					values++;
				}
				
			}
			
		}
		KnowledgeBook.addKnowledgeForStack(stack, know);
		return stack;
	}
	
	@Override
    public int getRecipeSize()
    {
        return 10;
    }
	
	@Override
    public ItemStack getRecipeOutput()
    {
		return null;
    }
	


}
