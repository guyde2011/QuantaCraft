package QuantaCraft.Blocks.Blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;
import QuantaCraft.Blocks.TileEntities.TileEntityPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidStorage;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.IHologramController;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HologramStand extends BlockContainer{
	
	public TileEntityHologramStand hi;
	public HologramStand(int id, String name, Material blockMaterial, float hardness) {
		super(blockMaterial);
		setHarvestLevel("pickaxe", 1);
		setHardness(hardness);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		LanguageRegistry.addName(this, name);
		this.setCreativeTab(modMain.tab);
		setBlockBounds(0f,0f,0f,1f , (float) 0.0625d,  1f);
		this.setResistance(3000);

	} 
	
	public boolean hasComparatorInputOverride(){
		return hi!=null && hi.initialized;
	}
	public int tickRate(World world)
	{
	    return 1;
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent) {
			if (world.isRemote){ return;}
			/*	hi = (TileEntityHologramStand) world.getTileEntity(x, y, z);
				hi.markDirty();
				world.markBlockForUpdate(x, y, z);
				return;
		}	*/
		hi = (TileEntityHologramStand) world.getTileEntity(x, y, z);
				Block bl = world.getBlock(x, y-1, z);
				if (bl.hasTileEntity() && world.getTileEntity(x, y-1, z) instanceof IHologramController){
					IHologramController cont = (IHologramController) world.getTileEntity(x, y-1, z);
					if (ent instanceof EntityPlayer){
						if (hi==null){
							hi = (TileEntityHologramStand) world.getTileEntity(x, y, z);
						}
						if (!hi.initialized){
							return;
						}
						cont.onPlayerWalkOn(hi, (EntityPlayer)ent);
						hi.markDirty();
					}
				}
		
	}
	
	 public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity)
	   {
	      AxisAlignedBB aabb = this.getCollisionBoundingBoxFromPool(world, x, y, z);
	      if (aabb != null && mask.intersectsWith(aabb))
	      {
	         list.add(aabb);
	         double help = 0.0625;
	         list.add(AxisAlignedBB.getBoundingBox(x + help*3,y + help,z + help*3,x + help*13,y + help*2,z + help*4));
	         list.add(AxisAlignedBB.getBoundingBox(x + help*3,y + help,z + help*3,x + help*4,y + help*2,z + help*13));
	         list.add(AxisAlignedBB.getBoundingBox(x + help*13,y + help,z + help*13,x + help*13,y + help*2,z + help*4));
	         list.add(AxisAlignedBB.getBoundingBox(x + help*13,y + help,z + help*13,x + help*4,y + help*2,z + help*13));
	      }
	   }

	
	public int getComparatorInputOverride(World world , int x , int y , int z , int unknown){

       
		
		return 0;
	}
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
    {
    		hi = (TileEntityHologramStand) world.getTileEntity(x, y, z);
			hi.markDirty();

   
			if (world.getBlock(x, y-1, z)!=null){
				Block bl = world.getBlock(x, y-1, z);
				if (bl.hasTileEntity() && world.getTileEntity(x, y-1, z) instanceof IHologramController){
					IHologramController cont = (IHologramController) world.getTileEntity(x, y-1, z);
					cont.onPlayerInteract(hi, player);
					return true;
				}
			}
			Block bl = world.getBlock(x, y-1, z);
			
			if (bl.hasTileEntity() && world.getTileEntity(x, y-1, z) instanceof TileEntityVoidStorage){
				TileEntityVoidStorage voidS = (TileEntityVoidStorage) world.getTileEntity(x, y-1, z);
				if (player.getHeldItem()==null && player.isSneaking() && voidS.storage>0){

					player.setCurrentItemOrArmor(0, new ItemStack(voidS.getStackInSlot(0).getItem(),Math.min(voidS.storage,voidS.getStackInSlot(0).getMaxStackSize())));
					voidS.storage-=Math.min(voidS.storage,voidS.getStackInSlot(0).getMaxStackSize());
    				hi.bool = false;
    				return true;
    			} else if (player.getHeldItem()!=null && !player.isSneaking() && ItemStack.areItemStackTagsEqual(voidS.getStackInSlot(0),player.getHeldItem())){
    				voidS.storage+=player.getHeldItem().stackSize;
    				player.setCurrentItemOrArmor(0, null);
    				hi.bool = true;
    				return true;
    			}
			}
			if (bl.hasTileEntity() && world.getTileEntity(x, y-1, z) instanceof TileEntityVoidPickStorage){
				TileEntityVoidPickStorage voidS = (TileEntityVoidPickStorage) world.getTileEntity(x, y-1, z);
				if (player.getHeldItem()==null && player.isSneaking() && voidS.storage>0){

					player.setCurrentItemOrArmor(0, new ItemStack(voidS.getStackInSlot(0).getItem(),Math.min(voidS.storage,voidS.getStackInSlot(0).getMaxStackSize())));
					voidS.storage-=Math.min(voidS.storage,voidS.getStackInSlot(0).getMaxStackSize());
    				hi.bool = false;
    				return true;
    			} else if (player.getHeldItem()!=null && !player.isSneaking() && ItemStack.areItemStackTagsEqual(voidS.getStackInSlot(0),player.getHeldItem())){
    				voidS.storage+=player.getHeldItem().stackSize;
    				player.setCurrentItemOrArmor(0, null);
    				hi.bool = true;
    				return true;
    			}
			}
    			if (hi.bool && player.getHeldItem()==null && player.isSneaking()){
    				player.setCurrentItemOrArmor(0, hi.getStackInSlot(0));
    				hi.setInventorySlotContents(0, null);
    				hi.bool = false;
    			} else if (!hi.bool && player.getHeldItem()!=null && !player.isSneaking()){
    				hi.setInventorySlotContents(0 , player.getHeldItem());
    				player.setCurrentItemOrArmor(0, null);
    				hi.bool = true;
    			}
			
    			hi.markDirty();
    		return true;


    
    }

	   @Override
	   public int getRenderType() {
	           return 161;
	   }

	   //It's not an opaque cube, so you need this.
	   @Override
	   public boolean isOpaqueCube() {
	           return false;
	   }
	  
	   //It's not a normal block, so you need this too.
	   public boolean renderAsNormalBlock() {
	           return false;
	   }
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
    	this.hi=new TileEntityHologramStand();    
    	return hi;
    }
	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" +"Holo");
	   }
	
}
