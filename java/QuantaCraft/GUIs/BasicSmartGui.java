package QuantaCraft.GUIs;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public abstract class BasicSmartGui implements ISmartGui{

	
	private ResourceLocation resLocation;
	
	public BasicSmartGui(SmartContainerGui smart){
		te = smart.myMachine;
		gui = smart;
		side = Side.CLIENT;
	}
	
	public BasicSmartGui(SmartContainerGui smart , String resource){
		te = smart.myMachine;
		gui = smart;
		side = Side.CLIENT;
		resLocation = new ResourceLocation(resource);
	}
	
	public BasicSmartGui(boolean bool){
		if (bool){
			side = Side.SERVER;
		}
	}
	
	public void setTexture(String resource){
		resLocation = new ResourceLocation(resource);
	}
	
	public ResourceLocation getTexturePath(){
		return resLocation;
	}
	
	public BasicSmartGui(){
		
	}
	
	public ItemStack getItemStack(){
		return stack;
	}

	@Override 
	public void updateItem(ItemStack stack){
		this.setChanged();
	}
	
	@Override
	public abstract void drawBackground(int mouseX, int mouseY, float partialTickTime);

	@Override
	public abstract void drawForeground(int mouseX, int mouseY, float partialTickTime);

	@Override
	public abstract void showTooltips(int mouseX, int mouseY, float partialTickTime);
	

	@Override
	public void update(int mouseX, int mouseY, float partialTickTime) {
		if (changed){
			sendInfo();
		}
		
		if (itemChanged){
			sendItem();
		}
		
	}
	
	public void sendInfo(){
		this.sendInfoToServer(te.xCoord, te.yCoord, te.zCoord, te.getWorldObj().provider.dimensionId);
	}
	
	public void sendInfo(TileEntity send){
		this.sendInfoToServer(send.xCoord, send.yCoord, send.zCoord, send.getWorldObj().provider.dimensionId);
	}
	
	public void sendItem(){
		this.sendItemStackToServer(te.xCoord, te.yCoord, te.zCoord, te.getWorldObj().provider.dimensionId);
	}
	
	
	public void sendItem(TileEntity send){
		this.sendItemStackToServer(send.xCoord, send.yCoord, send.zCoord, send.getWorldObj().provider.dimensionId);
	}
	
	public Side getSide(){
		return side;
	}
	
	public void setChanged(){
		changed = true;
	}
	
	public void setItemChanged(){
		itemChanged = true;
	}
	
	private Side side;
	private TileEntity te;
	private boolean changed;
	private boolean itemChanged;
	private SmartContainerGui gui;
	private ItemStack stack;
	private World getWorld(int worldID){
		return MinecraftServer.getServer().worldServers[worldID];
	}
	
	@Override
	public ByteBuf sendInfoToServer(int x , int y , int z , int worldID) {
		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(worldID);
		buf.writeBoolean(false);
		return buf;
	}

	@Override
	public void read(ByteBuf buf) {
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		World world = getWorld(buf.readInt());
		te = world.getTileEntity(x, y, z);	
		if (buf.readBoolean()){
			stack = ByteBufUtils.readItemStack(buf);
		}
	}
	
	@Override
	public ByteBuf sendItemStackToServer(int x , int y , int z , int worldID) {
		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(worldID);
		buf.writeBoolean(true);
		ByteBufUtils.writeItemStack(buf, stack);
		return buf;
	}


}
