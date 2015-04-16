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

public class KnowledgeCrafting implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		boolean bool = false;
		
		for (int i=0;i<inv.getSizeInventory();i++){
			if (inv.getStackInSlot(i)!=null && !(inv.getStackInSlot(i).getItem() instanceof ItemKnowledge)){
				return false;
			}
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof ItemKnowledge)){
				bool = true;
			}
			if (i==inv.getSizeInventory()-1&&bool){
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
		}
		Knowledges[] know = new Knowledges[length];
		int values = 0;
		for (int i=0;i<inv.getSizeInventory();i++){
			
			if (inv.getStackInSlot(i)!=null && (inv.getStackInSlot(i).getItem() instanceof ItemKnowledge)){
				know[values] = ItemKnowledge.getKnowledgesFromStack(inv.getStackInSlot(i))[0];
				values++;
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
