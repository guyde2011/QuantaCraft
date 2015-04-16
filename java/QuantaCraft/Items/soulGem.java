package QuantaCraft.Items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import QuantaCraft.main.Knowledges;
import QuantaCraft.main.SoulHandler;
import QuantaCraft.main.SoulTool;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.ISoulGatherer;
import QuantaCraft.main.Interfaces.ISoulStorage;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class soulGem extends SoulTool implements ISoulStorage{

	public soulGem(String name, int maxCharge) {
		super(name, maxCharge);
		
	}
	@Override
	public void addInformation(ItemStack stack , EntityPlayer player , List list , boolean bool){
		super.addInformation(stack,player,list,bool);
		 if (!stack.hasTagCompound()){
			  stack.stackTagCompound = new NBTTagCompound();
			  stack.stackTagCompound.setBoolean("ActivatedShare", false);
		  }
	}
	public double getDurabilityForDisplay(ItemStack stack){
		//stack.setItemDamage(SoulHandler.getMaxCharge(stack)-SoulHandler.getSouls(stack));
		return 1.0d-((double)SoulHandler.getSouls(stack))/((double)(SoulHandler.getMaxCharge(stack)));
	}
	@Override
	public boolean showDurabilityBar(ItemStack stack){
		return true;
	}
	
	
	
	public ItemStack onItemRightClick(ItemStack stack , World world , EntityPlayer player){
		if (player.isSneaking()){
			stack.stackTagCompound.setBoolean("ActivatedShare", !stack.stackTagCompound.getBoolean("ActivatedShare"));
			String add = EnumChatFormatting.GREEN+"Activated";
			if (stack.stackTagCompound.getBoolean("ActivatedShare")==false){
				add = EnumChatFormatting.RED+"Disabled";
			}
			if (!player.worldObj.isRemote){
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Soul Gem" + EnumChatFormatting.DARK_AQUA + " soul sharing" + EnumChatFormatting.AQUA + " ability " + add ));
			}
		}
		return stack;
	}
	
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		  super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		  if (!par1ItemStack.hasTagCompound()){
			  par1ItemStack.stackTagCompound = new NBTTagCompound();
			  par1ItemStack.stackTagCompound.setBoolean("ActivatedShare", false);
			  SoulHandler.setSouls(par1ItemStack, 0);
		  }
		  if (par1ItemStack.stackTagCompound.getBoolean("ActivatedShare")&& par3Entity instanceof EntityPlayer ){
			  EntityPlayer player = (EntityPlayer)par3Entity;
			  for (int i = 0;i<player.inventory.getSizeInventory()-4;i++){
				  if (player.inventory.getStackInSlot(i)!=null && SoulHandler.getSouls(par1ItemStack)>0 && player.inventory.getStackInSlot(i).getItem() instanceof ISoulGatherer && SoulHandler.getMaxCharge(player.inventory.getStackInSlot(i))>SoulHandler.getSouls(player.inventory.getStackInSlot(i))){
					  par1ItemStack = SoulHandler.setSouls(player.inventory.getStackInSlot(par4), SoulHandler.getSouls(player.inventory.getStackInSlot(par4))-1);
					  player.inventory.setInventorySlotContents(i, SoulHandler.setSouls(player.inventory.getStackInSlot(i), SoulHandler.getSouls(player.inventory.getStackInSlot(i))+1));
				  }

			  }
		  }
	  }
	
		
	
	
}
