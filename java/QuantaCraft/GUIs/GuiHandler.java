package QuantaCraft.GUIs;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import QuantaCraft.Blocks.Containers.ContainerAdamantiteContainer;
import QuantaCraft.Blocks.Containers.ContainerConstructorFluidModifier;
import QuantaCraft.Blocks.Containers.ContainerConstructorTable;
import QuantaCraft.Blocks.Containers.ContainerDirectionalStorage;
import QuantaCraft.Blocks.Containers.ContainerPickStorage;
import QuantaCraft.Blocks.Containers.ContainerSmartContainer;
import QuantaCraft.Blocks.Containers.ContainerVoidPickStorage;
import QuantaCraft.Blocks.Containers.ContainerVoidStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorFluidModifier;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityDirectionalStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntitySmartContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidStorage;
import QuantaCraft.Items.ContainerSoulDrain;
import QuantaCraft.Items.InventorySoulDrain;
import QuantaCraft.Items.KnowledgeGuiBase;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	Object tileEntity; 
	switch(ID){
		default: return null;
		case 0:
			tileEntity = world.getTileEntity(x, y, z);
			return new AdamantiteContainerGui(player.inventory , (TileEntityAdamantiteContainer)tileEntity);
		case 1:
			tileEntity = new InventorySoulDrain(player.getHeldItem());
			return new SoulDrainGui(player.inventory , (InventorySoulDrain)tileEntity , (KnowledgeGuiBase)player.getHeldItem().getItem());
		case 2:
			tileEntity = world.getTileEntity(x, y, z);
		
			return new ConstructorTableGui(player.inventory , (TileEntityConstructorTable)tileEntity);
		case 3:
			tileEntity = world.getTileEntity(x, y, z);
			return new VoidStorageGui(player.inventory , (TileEntityVoidStorage)tileEntity);
		case 4:
			tileEntity = world.getTileEntity(x, y, z);
			return new DirectionalStorageGui(player.inventory , (TileEntityDirectionalStorage)tileEntity);
		case 5:
			tileEntity = world.getTileEntity(x, y, z);
			return new PickStorageGui(player.inventory , (TileEntityPickStorage)tileEntity);
		case 6:
			tileEntity = world.getTileEntity(x, y, z);
			return new VoidPickStorageGui(player.inventory , (TileEntityVoidPickStorage)tileEntity);
		
	case 7:
		tileEntity = world.getTileEntity(x, y, z);
		return new ConstructorFluidModifierGui(player.inventory , (TileEntityConstructorFluidModifier)tileEntity);
	
	case 8:
		tileEntity = world.getTileEntity(x, y, z);
		return new SmartContainerGui(player.inventory , (TileEntitySmartContainer)tileEntity);
	}
	
	}

	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	Object tileEntity; 
	switch(ID){
		default: return null;
		case 0:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerAdamantiteContainer(player.inventory , (TileEntityAdamantiteContainer)tileEntity);
		case 1:
			tileEntity = new InventorySoulDrain(player.getHeldItem());
			return new ContainerSoulDrain(player.inventory , (InventorySoulDrain)tileEntity );
		case 2:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerConstructorTable(player.inventory , (TileEntityConstructorTable)tileEntity);
		case 3:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerVoidStorage(player.inventory , (TileEntityVoidStorage)tileEntity);
		case 4:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerDirectionalStorage(player.inventory , (TileEntityDirectionalStorage)tileEntity);
		case 5:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerPickStorage(player.inventory , (TileEntityPickStorage)tileEntity);
		case 6:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerVoidPickStorage(player.inventory , (TileEntityVoidPickStorage)tileEntity);
		case 7:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerConstructorFluidModifier(player.inventory , (TileEntityConstructorFluidModifier)tileEntity);
		case 8:
			tileEntity = world.getTileEntity(x, y, z);
			return new ContainerSmartContainer(player.inventory , (TileEntitySmartContainer)tileEntity);
		}
	
	
	}
}
