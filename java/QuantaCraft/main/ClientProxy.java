package QuantaCraft.main;

import org.lwjgl.input.Keyboard;

import QuantaCraft.Blocks.Cables.TileEntityExtractCable;
import QuantaCraft.Blocks.Cables.TileEntityItemsCable;
import QuantaCraft.Blocks.Cables.TileEntityItemsInvCable;
import QuantaCraft.Blocks.Cables.TileEntityRoundRobinCable;
import QuantaCraft.Blocks.Cables.TileEntityRFCable;
import QuantaCraft.Blocks.Containers.ContainerConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityBasicCable;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;
import QuantaCraft.Blocks.TileEntities.TileEntitySmartContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityTank;
import QuantaCraft.GUIs.AdamantiteContainerGui;
import QuantaCraft.GUIs.ConstructorTableGui;
import QuantaCraft.GUIs.SoulBar;
import QuantaCraft.GUIs.SoulDrainGui;
import QuantaCraft.Items.InventorySoulDrain;
import QuantaCraft.Items.KnowledgeGuiBase;
import QuantaCraft.keys.KeyBindings;
import QuantaCraft.keys.KeySoulSpeed;
import QuantaCraft.render.BasicCableRenderer;
import QuantaCraft.render.ConTableRenderer;
import QuantaCraft.render.HologramRenderer;
import QuantaCraft.render.ModelSoulArmor;
import QuantaCraft.render.RenderItemHologramRenderer;
import QuantaCraft.render.SmartChestRenderer;
import QuantaCraft.render.TankRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	 public void registerRenderers() {
		MinecraftForge.EVENT_BUS.register(new SoulBar(Minecraft.getMinecraft()));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHologramStand.class, new HologramRenderer());
		 MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(modMain.hologramStand), new RenderItemHologramRenderer());
		 RenderingRegistry.registerBlockHandler(161,new HologramRenderer());
		 RenderingRegistry.registerBlockHandler(162,new BasicCableRenderer());
		 RenderingRegistry.registerBlockHandler(163,new ConTableRenderer());
		 RenderingRegistry.registerBlockHandler(164,new SmartChestRenderer());
		 RenderingRegistry.registerBlockHandler(165,new TankRenderer());
		 
			addCable(TileEntityItemsCable.class);
			addCable(TileEntityItemsInvCable.class);
			addCable(TileEntityExtractCable.class);
			addCable(TileEntityRoundRobinCable.class);
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new TankRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmartContainer.class, new SmartChestRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConstructorTable.class, new ConTableRenderer());
	}
	
	public void registerTERenderers(){
		addCable(TileEntityRFCable.class);
	}
	
	private void addCable(Class class1){
		ClientRegistry.bindTileEntitySpecialRenderer(class1, new BasicCableRenderer());
	}
	public static KeyBinding NoClip = new KeyBinding("keys.QuantaCraft.noClip", Keyboard.KEY_C,"keys.QuantaCraft.category");
	public static KeyBinding pickBlock = new KeyBinding("keys.QuantaCraft.pickUp", Keyboard.KEY_V,"keys.QuantaCraft.category");
	public static KeyBinding speedSoul = new KeyBinding("keys.QuantaCraft.soulSpeed", Keyboard.KEY_P,"keys.QuantaCraft.category");
	
	public void RegisterKeys(){
		ClientRegistry.registerKeyBinding(NoClip);
		ClientRegistry.registerKeyBinding(pickBlock);
		ClientRegistry.registerKeyBinding(speedSoul);
		KeyBindings.registerKey(speedSoul);
    	new KeySoulSpeed();
	}


	private static final ModelSoulArmor ChestPiece = new ModelSoulArmor(1.0f);
	private static final ModelSoulArmor Leggings = new ModelSoulArmor(0.5f);
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(int id){
		switch (id) {
		case 0:
			return ChestPiece;
		case 1:
			return Leggings;
		default:
			break;
		}
		return ChestPiece; //default, if whenever you should have passed on a wrong id
	}


	

}	



		

