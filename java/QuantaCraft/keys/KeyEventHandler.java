package QuantaCraft.keys;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import QuantaCraft.Items.SoulArmor;
import QuantaCraft.main.ClientProxy;
import QuantaCraft.main.modMain;
import QuantaCraft.packets.ClientToServerMessage;
import QuantaCraft.packets.KeyPressedUpdateCommand;
import QuantaCraft.packets.MessageCommand;
import QuantaCraft.packets.SoulTPCommand;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyEventHandler {
	@SubscribeEvent
	public void onKeyPressed(InputEvent.KeyInputEvent event){
		if (ClientProxy.NoClip.isPressed()){
	    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    	ItemStack[] armor1 = player.inventory.armorInventory;
	    	boolean bool1 = true;
			for (int i = 0;i<4;i++){
				bool1 = armor1[i]!=null && armor1[i].getItem() instanceof SoulArmor && bool1;
			}
			if (bool1){
				modMain.networkWrapper.sendToServer(new ClientToServerMessage(new SoulTPCommand(player)));
			}
		}
		if (ClientProxy.speedSoul.isPressed()){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ItemStack[] armor1 = player.inventory.armorInventory;
			boolean bool1 = true;
			for (int i = 0;i<4;i++){
				bool1 = armor1[i]!=null && armor1[i].getItem() instanceof SoulArmor && bool1;
			}
			if (bool1){
				modMain.networkWrapper.sendToServer(new ClientToServerMessage(new KeyPressedUpdateCommand(ClientProxy.speedSoul , player)));
			}
		}
	}
}
