package QuantaCraft.packets;

import java.util.Random;

import cofh.api.energy.EnergyStorage;
import QuantaCraft.Blocks.TileEntities.TileEntitySmartContainer;
import QuantaCraft.GUIs.ISmartGui;
import QuantaCraft.GUIs.SmartGuisRegistry;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.IPickable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SmartGuiPacketCommand extends MessageCommand{

	private int guiID;
	private ISmartGui gui;
	private boolean item;
	private TileEntity te;
	
	public SmartGuiPacketCommand(ISmartGui Gui , boolean writeItem , TileEntity tileEntity){
		gui = Gui ;
		guiID = SmartGuisRegistry.getIDForGui(Gui.getClass());
		item = writeItem;
		te = tileEntity;
	}
	

	
	public SmartGuiPacketCommand(){
		
	}
	
	
	@Override
	public MessageCommand read(ByteBuf buf) {
		this.guiID = buf.readInt();
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		int worldID = buf.readInt();
		te = MinecraftServer.getServer().worldServers[worldID].getTileEntity(x,y,z);
		gui = SmartGuisRegistry.getGui(this.guiID);
		gui.read(buf);
		return this;
	}

	@Override
	public void write(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeInt(guiID);
		buf.writeInt(te.xCoord);
		buf.writeInt(te.yCoord);
		buf.writeInt(te.zCoord);
		buf.writeInt(te.getWorldObj().provider.dimensionId);
		if (item){
			buf.writeBytes(gui.sendItemStackToServer(te.xCoord, te.yCoord, te.zCoord, te.getWorldObj().provider.dimensionId));
		} else {
			buf.writeBytes(gui.sendInfoToServer(te.xCoord, te.yCoord, te.zCoord, te.getWorldObj().provider.dimensionId));
		}
	}

	@Override
	public void runCommand(MessageContext ctx) {
		((TileEntitySmartContainer)te).setSubGui(gui);
		((TileEntitySmartContainer)te).updateGuiItem(gui.getItemStack());
	}
}
