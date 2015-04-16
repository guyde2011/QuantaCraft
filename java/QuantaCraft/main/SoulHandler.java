package QuantaCraft.main;

import QuantaCraft.main.Interfaces.ISoulItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SoulHandler {

	public static void addSoulsForPlayer(EntityPlayer player , int Souls){
		
	}
	
	public static ItemStack addSouls(ItemStack stack , int Souls){
		if (stack.stackTagCompound==null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger("soulsQuanta", 0);
		}
		stack.stackTagCompound.setInteger("soulsQuanta" ,stack.stackTagCompound.getInteger("soulsQuanta")+Souls);
		return stack;
	}
	
	public static ItemStack setSouls(ItemStack stack , int Souls){
		if (stack.stackTagCompound==null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger("soulsQuanta", 0);
		}
		stack.stackTagCompound.setInteger("soulsQuanta" ,Souls);
		return stack;
	}
	
	public static int getMaxCharge(ItemStack stack){
		int retInt = 0;
		if (stack!=null){
			if (stack.getItem() instanceof ISoulItem && stack.stackTagCompound!=null && stack.stackTagCompound.hasKey("maxSoulCharge")){
				retInt = stack.stackTagCompound.getInteger("maxSoulCharge");
			}
		}
		return retInt;
	}
	
	public static int getSouls(ItemStack stack){
		int retInt = 0;
		if (stack!=null){
			if (stack.getItem() instanceof ISoulItem && stack.stackTagCompound!=null && stack.stackTagCompound.hasKey("soulsQuanta")){
				retInt = stack.stackTagCompound.getInteger("soulsQuanta");
			}
		}
		return retInt;
	}

	public static int getSoulsInInventory(EntityPlayer player){
		int ret = 0;
		for (int i = 0; i<player.inventory.getSizeInventory();i++){
			if (player.inventory.getStackInSlot(i)!=null){
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack.getItem() instanceof ISoulItem){
					ret += SoulHandler.getSouls(stack);
				}
			}
		}
		return ret;
	}
	
	public static boolean useSoulsFromInventory(EntityPlayer player , int Souls){
		int ret = Souls;
		int ret1 = 0;
		System.out.println("test123");
		for (int i = 0; i<player.inventory.getSizeInventory();i++){
			if (player.inventory.getStackInSlot(i)!=null){
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (stack.getItem() instanceof ISoulItem && ret>0){
					ret1 = Math.min(SoulHandler.getSouls(stack),Souls);
					ret -=ret1;
					System.out.println("test1234");
					player.inventory.setInventorySlotContents(i, SoulHandler.addSouls(stack, -ret1));
				}
			}
		}
		return ret<1;
	}
	
}
