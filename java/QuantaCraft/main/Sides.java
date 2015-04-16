package QuantaCraft.main;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public enum Sides {

	TOP(0,1,0),
	BOTTOM(0,-1,0),
	RIGHT(1,0,0),
	LEFT(-1,0,0),
	FORWARD(0,0,1),
	BACK(0,0,-1);
	
	private int xRel;	
	private int yRel;
	private int zRel;
	Sides(int x , int y , int z){
		xRel = x;
		yRel = y;
		zRel = z;
	}
	
	public int getX(){
		return xRel;
	}

	public int getY(){
		return yRel;
	}
	
	public int getZ(){
		return zRel;
	}
	
	public static Block getBlock(int x , int y , int z , World world , Sides side){
		return world.getBlock(x+ side.xRel, y + side.yRel, z + side.zRel);
	}
	
	public static Block getBlock(TileEntity ent , Sides side){
		return getBlock(ent.xCoord,ent.yCoord,ent.zCoord,ent.getWorldObj(),side);
	}
	
	public static TileEntity getTileEntity(int x , int y , int z , World world , Sides side){
		return world.getTileEntity(x+ side.xRel, y + side.yRel, z + side.zRel);
	}
	
	public static TileEntity getTileEntity(TileEntity ent , Sides side){
		return getTileEntity(ent.xCoord,ent.yCoord,ent.zCoord,ent.getWorldObj(),side);
	}
	
	public static Sides getOppositeSide(Sides side){
		if(side==Sides.TOP){
			return Sides.BOTTOM;
		}
		if(side==Sides.BOTTOM){
			return Sides.TOP;
		}
		if(side==Sides.LEFT){
			return Sides.RIGHT;
		}
		if(side==Sides.RIGHT){
			return Sides.LEFT;
		}
		if(side==Sides.FORWARD){
			return Sides.BACK;
		}
		if(side==Sides.BACK){
			return Sides.FORWARD;
		}
		return null;
	}
	
	public ForgeDirection getDir(){
		if (this==Sides.TOP){
			return ForgeDirection.UP;
		}
		if (this==Sides.BOTTOM){
			return ForgeDirection.DOWN;
		}
		if (this==Sides.BACK){
			return ForgeDirection.NORTH;
		}
		if (this==Sides.FORWARD){
			return ForgeDirection.SOUTH;
		}
		if (this==Sides.RIGHT){
			return ForgeDirection.EAST;
		}
		if (this==Sides.LEFT){
			return ForgeDirection.WEST;
		}
		return ForgeDirection.UNKNOWN;
	}
	
	public static Sides getSide(ForgeDirection dir){
		if (dir==ForgeDirection.UP){
			return Sides.TOP;
		}
		if (dir==ForgeDirection.DOWN){
			return Sides.BOTTOM;
		}
		if (dir==ForgeDirection.NORTH){
			return Sides.BACK;
		}
		if (dir==ForgeDirection.SOUTH){
			return Sides.FORWARD;
		}
		if (dir==ForgeDirection.EAST){
			return Sides.RIGHT;
		}
		if (dir==ForgeDirection.WEST){
			return Sides.LEFT;
		}
		return null;
	}



}