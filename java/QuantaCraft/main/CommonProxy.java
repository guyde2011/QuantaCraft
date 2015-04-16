package QuantaCraft.main;


import java.util.HashMap;
import java.util.Map;

import QuantaCraft.Blocks.Containers.ContainerAdamantiteContainer;
import QuantaCraft.Blocks.Containers.ContainerConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.GUIs.AdamantiteContainerGui;
import QuantaCraft.GUIs.ConstructorTableGui;
import QuantaCraft.GUIs.SoulDrainGui;
import QuantaCraft.Items.ContainerSoulDrain;
import QuantaCraft.Items.InventorySoulDrain;
import QuantaCraft.Items.KnowledgeGuiBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.SidedProxy;

public class CommonProxy {


	public void registerRenderers() {
		// Nothing here as the server doesn't render graphics or entities!
		} 
	public void registerTERenderers(){
		
	}

		public void registerProxies(){
		}
		public void RegisterKeys(){
		
		}

		public ModelBiped getArmorModel(int id) {
			// TODO Auto-generated method stub
			return null;
		}
		

}
