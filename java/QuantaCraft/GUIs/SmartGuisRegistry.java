package QuantaCraft.GUIs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SmartGuisRegistry {
	
	private static List<Class<? extends ISmartGui>> Guis = new ArrayList<Class<? extends ISmartGui>>();
	private static List<ItemStack> Items = new ArrayList<ItemStack>();
	
	
	public static int getIDForGui(Class<? extends ISmartGui> gui){
		return Guis.indexOf(gui);
	}
	
	public static ISmartGui getGui(int i){
		try {
			return Guis.get(i).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static boolean canStackOpenGui(int guiID , ItemStack stack){
		ItemStack stack1 = Items.get(guiID);
		if (stack1.getItem() == stack.getItem() && stack.getItemDamage() == stack1.getItemDamage()){
			return true;
		}
		return false;
	}
	

	
	public static int getGuiIdForItem(ItemStack stack){
		for (int i=0;i<Guis.size();i++){
			if (canStackOpenGui(i , stack)){
				return i;
			}
		}
		return -1;
	}
	
	public static boolean canStackOpenGuis(ItemStack stack){
		return getGuiIdForItem(stack)!=-1;
	}
	
	public static void sendItemToServer(ISmartGui gui , int x , int y , int z , int worldID){
		
	}
	
	public static void sendToServer(){
		
	}
	
	public static void registerGui(Class<? extends ISmartGui> gui , ItemStack stack){
		Guis.add(gui);
		Items.add(stack);
	}
	
	public static int GUI_HEIGHT = 172;
	public static int GUI_WIDTH = 33;
	public static int GUI_X_OFFSET = 9;
	public static int GUI_Y_OFFSET = 44;
}
