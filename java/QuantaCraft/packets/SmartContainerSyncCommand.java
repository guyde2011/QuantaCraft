package QuantaCraft.packets;

import java.util.Random;

import cofh.api.energy.EnergyStorage;
import QuantaCraft.Blocks.TileEntities.TileEntitySmartContainer;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.IPickable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SmartContainerSyncCommand extends MessageCommand{

	private int maxEnergy;
	private int energy;
	private int x;
	private int y;
	private int z;
	private int fluAmount;
	private int fluCapacity;
	private int fluID;
	
	public SmartContainerSyncCommand(TileEntitySmartContainer smart){
		maxEnergy = smart.maxEnergy;
		energy = smart.energyStored;
		x = smart.xCoord;
		y = smart.yCoord;
		z = smart.zCoord;
		fluAmount = smart.getTank().getFluidAmount();
		fluID = smart.getTank().getFluid().fluidID;
		fluCapacity = smart.getTank().getCapacity();
		System.out.println(fluCapacity + " hello");
	}
	
	public static SmartContainerSyncCommand noFluid(TileEntitySmartContainer smart){
		SmartContainerSyncCommand a = new SmartContainerSyncCommand();
		a.maxEnergy = smart.maxEnergy;
		a.energy = smart.energyStored;
		a.x = smart.xCoord;
		a.y = smart.yCoord;
		a.z = smart.zCoord;
		a.fluAmount = 0;
		a.fluID = -777;
		a.fluCapacity = smart.getTank().getCapacity();
		return a;
	}
	
	public SmartContainerSyncCommand(){
		
	}
	
	
	@Override
	public MessageCommand read(ByteBuf buf) {
		maxEnergy = buf.readInt();
		energy = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		fluAmount = buf.readInt();
		fluID = buf.readInt();
		fluCapacity = buf.readInt();
		return this;
	}

	@Override
	public void write(ByteBuf buf) {
		// TODO Auto-generated method stub
		buf.writeInt(maxEnergy);
		buf.writeInt(energy);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(fluAmount);
		buf.writeInt(fluID);
		buf.writeInt(fluCapacity);
	}

	@Override
	public void runCommand(MessageContext ctx) {
		World world = Minecraft.getMinecraft().theWorld;
		TileEntitySmartContainer smart = (TileEntitySmartContainer) world.getTileEntity(x, y, z);
		if (smart==null) return;
		if (smart.store == null){
			smart.store = new EnergyStorage(10000000, 15000 , 15000);
		}
		smart.store.setEnergyStored(energy);
		smart.store.setCapacity(maxEnergy);
		smart.setTankCapacity(fluCapacity);
		if (fluID!=-777){
			smart.setFluidAndAmount(fluID, fluAmount);
		}
	}
}
