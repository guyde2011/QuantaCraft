package QuantaCraft.packets;

import QuantaCraft.keys.KeyBindings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class KeyPressedUpdateCommand extends MessageCommand{

	private int KeyID;
	private boolean Active;
	private String playerName;
	private int worldID;
	
	public KeyPressedUpdateCommand(KeyBinding key , EntityPlayer player){
		KeyID = KeyBindings.getID(key);
		Active = key.isPressed();
		playerName = player.getDisplayName();
		worldID = player.worldObj.provider.dimensionId;
	}
	
	public KeyPressedUpdateCommand(){
		
	}
	
	public EntityPlayer getPlayer(){
		return MinecraftServer.getServer().worldServers[worldID].getPlayerEntityByName(playerName);
	}
	
	@Override
	public MessageCommand read(ByteBuf buf) {
		playerName = ByteBufUtils.readUTF8String(buf);
		worldID = buf.readInt();
		KeyID = buf.readInt();
		Active = buf.readBoolean();
		return this;
	}

	@Override
	public void write(ByteBuf buf) {
		// TODO Auto-generated method stub
		ByteBufUtils.writeUTF8String(buf,playerName);
		buf.writeInt(worldID);
		buf.writeInt(KeyID);
		buf.writeBoolean(Active);
	}

	@Override
	public void runCommand(MessageContext ctx) {
		KeyBindings.ServerKeys.get(KeyID).onActivated(this);
		
	}

}
