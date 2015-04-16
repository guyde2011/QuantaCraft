package QuantaCraft.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SoulTPCommand extends MessageCommand{

	private String playerName;
	private int worldID;
	
	public SoulTPCommand(EntityPlayer player){
		worldID = player.worldObj.provider.dimensionId;
		playerName = player.getDisplayName();
	}
	
	public SoulTPCommand(){
		
	}
	
	@Override
	public MessageCommand read(ByteBuf buf) {
		playerName = ByteBufUtils.readUTF8String(buf);
		worldID = buf.readInt();
		return this;
	}

	@Override
	public void write(ByteBuf buf) {
		// TODO Auto-generated method stub
		ByteBufUtils.writeUTF8String(buf,playerName);
		buf.writeInt(worldID);
	}

	@Override
	public void runCommand(MessageContext ctx) {
		EntityPlayer player = MinecraftServer.getServer().worldServers[worldID].getPlayerEntityByName(playerName);
		float yaw = (float) ((player.rotationYaw + 90)*Math.PI / 180f);
		float pitch = (float) ((player.rotationPitch + 90)*Math.PI / 180f);
		float x = (float) (Math.sin(pitch) * Math.cos(yaw));
		float z = (float) (Math.sin(pitch) * Math.sin(yaw));
		float y = (float) Math.cos(pitch);
		//float all = (float) Math.sqrt(x*x + y*y + z*z)/2;
		float all = 0.3f;
		player.setPositionAndUpdate(player.posX + x/all,player.posY + y/all,player.posZ + z/all);
		
	}

}
