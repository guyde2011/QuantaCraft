package QuantaCraft.keys;

import net.minecraft.entity.player.EntityPlayer;
import QuantaCraft.keys.KeyBindings.serverKeyBinding;
import QuantaCraft.main.ClientProxy;
import QuantaCraft.main.QuantaEventHandler;
import QuantaCraft.packets.KeyPressedUpdateCommand;

public class KeySoulSpeed extends serverKeyBinding{



	public KeySoulSpeed() {
		KeyBindings.instance.super(KeyBindings.getID(ClientProxy.speedSoul));
	}

	@Override
	public void onActivated(KeyPressedUpdateCommand cmd) {
		EntityPlayer player = cmd.getPlayer();
		boolean bool = true;
		if (QuantaEventHandler.speed.containsKey(player.getDisplayName())){
			bool = !QuantaEventHandler.speed.get(player.getDisplayName());
		}
		QuantaEventHandler.speed.put(player.getDisplayName(), bool);
		QuantaEventHandler.writeSpeed(bool, player);
	}

}
