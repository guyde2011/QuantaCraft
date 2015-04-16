package QuantaCraft.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.settings.KeyBinding;
import QuantaCraft.packets.KeyPressedUpdateCommand;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



public class KeyBindings {
	
	public static KeyBindings instance = new KeyBindings();
	public static List<serverKeyBinding> ServerKeys = new ArrayList<serverKeyBinding>();
	@SideOnly(Side.CLIENT)
	private static List<KeyBinding> keys = new ArrayList<KeyBinding>();
	private static Map<KeyBinding,Integer> keyIDs = new HashMap<KeyBinding,Integer>();
	
	public static KeyBinding getKey(int id){
		return keys.get(id);
	}
	
	public static int getID(KeyBinding key){
		return keyIDs.get(key);
	}
	
	public static void registerKey(KeyBinding key){
		keys.add(key);
		keyIDs.put(key, keys.size()-1);
	}
	
	public abstract class serverKeyBinding {

		private int ID;
		
		public serverKeyBinding(int id){
			ID = id;
			KeyBindings.ServerKeys.add(this);
		}
		
		public int getID(){
			return ID;
		}
		
		public abstract void onActivated(KeyPressedUpdateCommand cmd);
		
	}
	
	
}
