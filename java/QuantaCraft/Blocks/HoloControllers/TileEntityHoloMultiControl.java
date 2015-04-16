package QuantaCraft.Blocks.HoloControllers;

import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;
import QuantaCraft.main.Sides;
import QuantaCraft.main.Interfaces.IHologramController;

import com.mojang.realmsclient.gui.ChatFormatting;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityHoloMultiControl extends AdvancedHoloController{
	
	public TileEntityHoloColorControl color;
	private boolean initialized;
	
	public TileEntityHoloMultiControl(){
		super();
	}

	public void onItemPlacedNoSide(TileEntityHologramStand holo, EntityPlayer player, Sides side1 , int times) {
		if (times>6){return;}
		for (Sides side : Sides.values()){
    		if (side!=side1 && Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					((TileEntityHoloMultiControl)cont).onItemPlacedNoSide(holo, player,side.getOppositeSide(side),times+1);
    				} else {
    					cont.onItemPlaced(holo, player);
    				}
    			}
    		}
    	}
			
	}

	public void onItemTakenNoSide(TileEntityHologramStand holo, EntityPlayer player, Sides side1 , int times) {
		if (times>6){return;}
		for (Sides side : Sides.values()){
    		if (side!=side1 && Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					((TileEntityHoloMultiControl)cont).onItemTakenNoSide(holo, player,side.getOppositeSide(side),times+1);
    				} else {
    					cont.onItemTaken(holo, player);
    				}
    			}
    		}
    	}
			
	}
	
	public void onItemPlaced(TileEntityHologramStand holo, EntityPlayer player) {
		
		for (Sides side : Sides.values()){
    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					((TileEntityHoloMultiControl)cont).onItemPlacedNoSide(holo, player,side.getOppositeSide(side),1);
    				} else {
    					cont.onItemPlaced(holo, player);
    				}
    			}
    		}
    	}
			
	}
	
	public void onItemTaken(TileEntityHologramStand holo, EntityPlayer player) {
		
		for (Sides side : Sides.values()){
    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					((TileEntityHoloMultiControl)cont).onItemTakenNoSide(holo, player,side.getOppositeSide(side),1);
    				} else {
    					cont.onItemTaken(holo, player);
    				}
    			}
    		}
    	}
			
	}
	
    public boolean hasColor(Sides side1 , int times){
    	if (times>6){
    		return false;
    	}
		for (Sides side : Sides.values()){
    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof TileEntityHoloColorControl){
    				return true;
    			} else if(ent instanceof TileEntityHoloMultiControl){
    				if (((TileEntityHoloMultiControl)ent).hasColor(side.getOppositeSide(side),times+1)){
    					return true;
    				}
    			}
    		}
		}
		return false;
    }
    
    public String getColor(Sides side1 , int times){
    	if (times>6){
    		return "0x-1";
    	}
		for (Sides side : Sides.values()){
    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof TileEntityHoloColorControl && ((TileEntityHoloColorControl)ent).color!=null){
    				return ((TileEntityHoloColorControl)ent).color;
    			} else if(ent instanceof TileEntityHoloMultiControl){
    				if (((TileEntityHoloMultiControl)ent).hasColor(side.getOppositeSide(side),times+1)){
    					return ((TileEntityHoloMultiControl)ent).getColor(side.getOppositeSide(side),times+1);
    				}
    			}
    		}
		}
		return "0x-1";
    }
	
	public boolean takeNoSide(TileEntityHologramStand holo, EntityPlayer player,Sides side1, int times){
		if (times>6){
			return true;
		}
		boolean bool = true;
		for (Sides side : Sides.values()){
    		if (side!=side1 && Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					
    					bool = bool && ((TileEntityHoloMultiControl)cont).takeNoSide(holo, player, Sides.getOppositeSide(side), 1);
    				} else {
    					bool = cont.canTakeItem(holo, player) && bool;
    				}
    			}
    		}
    	}
		return bool;
	}
	
    @Override 
    public boolean canTakeItem(TileEntityHologramStand holo, EntityPlayer player){
		boolean bool = true;
    	for (Sides side : Sides.values()){

    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					bool = bool && ((TileEntityHoloMultiControl)cont).takeNoSide(holo, player, Sides.getOppositeSide(side), 1);
    				} else {
    					bool = cont.canTakeItem(holo, player) && bool;
    				}
    			}
    		}
    	}
    	return bool;
    }
    
    public boolean placeNoSide(TileEntityHologramStand holo, EntityPlayer player,Sides side1, int times){
		if (times>6){
			return true;
		}
		boolean bool = true;
		for (Sides side : Sides.values()){
    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (side!=side1 && ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					
    					bool = bool && ((TileEntityHoloMultiControl)cont).placeNoSide(holo, player, Sides.getOppositeSide(side), 1);
    				} else {
    					bool = cont.canPlaceItem(holo, player) && bool;
    				}
    			}
    		}
    	}
		return bool;
	}
	
    @Override 
    public boolean canPlaceItem(TileEntityHologramStand holo, EntityPlayer player){
		boolean bool = true;
    	for (Sides side : Sides.values()){

    		if (Sides.getBlock(this, side).hasTileEntity()){
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					bool = bool && ((TileEntityHoloMultiControl)cont).placeNoSide(holo, player, Sides.getOppositeSide(side), 1);
    				} else {
    					bool = cont.canPlaceItem(holo, player) && bool;
    				}
    			}
    		}
    	}
    	return bool;
    }
    

	
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound tag = new NBTTagCompound();
    	this.writeToNBT(tag);
    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
	{
			this.readFromNBT(packet.func_148857_g());
	}
	
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
    	super.readFromNBT(tagCompound); 
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
    	super.writeToNBT(tagCompound);

    	
    }
    @Override

    public void updateEntity() {

    	if (!this.worldObj.isRemote) {
    		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    		if (!this.initialized && worldObj != null) {
    		}  
    		this.initialized=true;        
    	}               
    }
    
	public String onPlayerWalkOn(TileEntityHologramStand holo, EntityPlayer player){
		for (Sides side : Sides.values()){

    		if (Sides.getBlock(this, side).hasTileEntity()){
    			String str;
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					str = ((TileEntityHoloMultiControl)cont).onPlayerWalkOnNoSide(holo, player, side.getOppositeSide(side), 0);
    				} else {
    					str = cont.onPlayerWalkOn(holo, player);
    				}
    				if (str.equals("took")){
    					return "taken";
    				}
    				if (str.equals("taken")){
    					this.onItemTaken(holo, player);
    					return "void";
    				}
    			}
    		}
    	}
		return "void";
	}
	
	public String onPlayerWalkOnNoSide(TileEntityHologramStand holo, EntityPlayer player,Sides side1 , int times){
		if (times>6){return "void";}
		for (Sides side : Sides.values()){

    		if (Sides.getBlock(this, side).hasTileEntity()){
    			String str;
    			TileEntity ent = Sides.getTileEntity(this, side);
    			if (ent instanceof IHologramController && !(ent instanceof TileEntityHoloColorControl)){
    				IHologramController cont = (IHologramController)ent;
    				if (cont instanceof TileEntityHoloMultiControl){
    					str = ((TileEntityHoloMultiControl)cont).onPlayerWalkOnNoSide(holo, player, side.getOppositeSide(side), times+1);
    				} else {
    					str = cont.onPlayerWalkOn(holo, player);
    				}
    				if (str.equals("took")){
    					return "taken";
    				}
    				else if (str.equals("taken")){
    					return "taken";
    				}
    			}
    		}
    	}
		return "void";
	}

}
