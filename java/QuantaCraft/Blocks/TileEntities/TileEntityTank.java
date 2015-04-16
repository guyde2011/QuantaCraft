package QuantaCraft.Blocks.TileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


















import cpw.mods.fml.common.registry.GameRegistry;
import QuantaCraft.Crafting.LiquidCrafter.ForgeRecipe;
import QuantaCraft.Crafting.Table.InventoryDummy;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.main.modMain;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;




public class TileEntityTank extends TileEntity implements IFluidHandler{

		public boolean renderNormally;
		public boolean change;
		public boolean near;
		public TileEntityTank() {
			super();
			renderNormally = true;
		}
		
		
		
		 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);



		    /* IFluidHandler */
		    @Override
		    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
		    {
		    	if (tank.fill(resource, false)==0){
		    		if (this.worldObj.getBlock(xCoord, yCoord+1, zCoord).hasTileEntity() && this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityTank){
		    			return ((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).fill(from, resource, doFill);
		    		}
		    	}
		        return tank.fill(resource, doFill);
		    }

		    @Override
		    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
		    {
		        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
		        {
		            return null;
		        }
		        
		        return tank.drain(resource.amount, doDrain);
		    }

		    @Override
		    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
		    {
		        return tank.drain(maxDrain, doDrain);
		    }

		    @Override
		    public boolean canFill(ForgeDirection from, Fluid fluid)
		    {
		        return renderNormally;
		    }

		    @Override
		    public boolean canDrain(ForgeDirection from, Fluid fluid)
		    {
		        return renderNormally;
		    }

		    @Override
		    public FluidTankInfo[] getTankInfo(ForgeDirection from)
		    {
		        return new FluidTankInfo[] { tank.getInfo() };
		    }
		
		
	    public boolean initialized;


		public void readFromTank(NBTTagCompound tag){
			 tank.readFromNBT(tag);
		}
		
		@Override
		public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
		{
				readFromTank(packet.func_148857_g());
	        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	        if (this.worldObj.isRemote){
	        	Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
	        }
		}
        @Override
        public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
  
        }
        
    

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
                super.readFromNBT(tagCompound); 
		        tank.readFromNBT(tagCompound);

        }


        @Override
        public void writeToNBT(NBTTagCompound tagCompound) { 
                super.writeToNBT(tagCompound);
                tank.writeToNBT(tagCompound);
               
        }
        
        public boolean isUpTank(){
        	return this.worldObj.getBlock(xCoord, yCoord+1, zCoord).hasTileEntity() && this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityTank;
        }
        
        public boolean isDownTank(){
        	return this.worldObj.getBlock(xCoord, yCoord-1, zCoord).hasTileEntity() && this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof TileEntityTank;
        }

                @Override

                public void updateEntity() {
                	if (change){
                		renderNormally = true;
                		near = false;
                	}
                	change = false;
        			if (this.getBlockType()!=worldObj.getBlock(xCoord, yCoord, zCoord)){
        				return;
        			}
                	this.markDirty();
                	if (!this.worldObj.isRemote) {
                		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                		if (!this.initialized && worldObj != null) {
                	
                		}  
                		this.initialized=true;
                	}
                
                	if (renderNormally && this.worldObj.getBlock(xCoord, yCoord+1, zCoord).hasTileEntity() && this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityTank){
                		if (((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).tank.getFluid()!=null && this.tank.getCapacity()>this.tank.getFluidAmount()){
                			if (this.tank.getFluid()==null || this.tank.getFluid().getFluid() == ((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).tank.getFluid().getFluid()){
                				Fluid flu = ((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).tank.getFluid().getFluid();
                				if (((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).canDrain(ForgeDirection.DOWN, flu) && ((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).drain(ForgeDirection.DOWN, new FluidStack(flu , Math.min(100 , this.tank.getCapacity() - this.tank.getFluidAmount())),false)!=null){
                					this.tank.fill(((TileEntityTank)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).drain(ForgeDirection.DOWN, new FluidStack(flu , Math.min(100 , this.tank.getCapacity() - this.tank.getFluidAmount())),true),true);
                				}
                			}
                		}
                	}
                	this.markDirty();
                	if (!renderNormally){
                		change = true;
                		
                	}
                }
                
                @Override
        public void invalidate() {
        	if (!this.initialized && worldObj != null) {
        	}
        }

				

				
			


		}


