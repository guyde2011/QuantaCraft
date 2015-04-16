package QuantaCraft.packets;

import java.util.Random;

import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.IPickable;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class WrenchCommand extends MessageCommand{

	private String playerName;
	private int worldID;
	private int x;
	private int y;
	private int z;
	
	public WrenchCommand(EntityPlayer player , int X , int Y , int Z){
		worldID = player.worldObj.provider.dimensionId;
		playerName = player.getDisplayName();
		x = X;
		y = Y;
		z = Z;
	}
	
	public WrenchCommand(){
		
	}
	
	
	@Override
	public MessageCommand read(ByteBuf buf) {
		playerName = ByteBufUtils.readUTF8String(buf);
		worldID = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		return this;
	}

	@Override
	public void write(ByteBuf buf) {
		// TODO Auto-generated method stub
		ByteBufUtils.writeUTF8String(buf,playerName);
		buf.writeInt(worldID);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public void runCommand(MessageContext ctx) {
		
		EntityPlayer player = MinecraftServer.getServer().worldServers[worldID].getPlayerEntityByName(playerName);
		Block block = player.worldObj.getBlock(x, y, z);
		if (!(block instanceof IPickable)){
			return;
		}
		IPickable pickable = ((IPickable)block);
		EntityItem ent = pickable.getEntityItem(x, y, z, player.worldObj);
		player.worldObj.spawnEntityInWorld(ent);
        block.breakBlock(player.worldObj, x, y, z, block,1);
        player.worldObj.removeTileEntity(x, y, z);
        player.worldObj.setBlock(x, y, z, Blocks.air);
	}
}
