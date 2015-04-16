package QuantaCraft.Items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import QuantaCraft.main.createItem;
import QuantaCraft.main.modMain;

public class VoidGem extends createItem {

	public VoidGem(String name) {
		super(name);
	}
	
	public ItemStack onItemRightClick(ItemStack stack , World world , EntityPlayer player){
		if (player.isSneaking()){
			stack.stackTagCompound.setBoolean("ActivatedVoid", !stack.stackTagCompound.getBoolean("ActivatedVoid"));
			String add = EnumChatFormatting.GREEN+"Activated";
			if (stack.stackTagCompound.getBoolean("ActivatedVoid")==false){
				add = EnumChatFormatting.RED+"Disabled";
			}
			if (!player.worldObj.isRemote){
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Void Gem" + EnumChatFormatting.BLACK + " void" + EnumChatFormatting.AQUA + " ability " + add ));
			}
			}
		return stack;
	}
	
	@Override
	public void addInformation(ItemStack stack , EntityPlayer player , List list , boolean bool){
		ItemStack par1ItemStack = stack;
		 if (!par1ItemStack.hasTagCompound()){
			  par1ItemStack.stackTagCompound = new NBTTagCompound();
			  par1ItemStack.stackTagCompound.setBoolean("ActivatedVoid", false);
			  par1ItemStack.stackTagCompound.setInteger("VoidCount", 0);
		  }
		 if (!player.worldObj.isRemote){
			 list.add(EnumChatFormatting.GRAY + "Void Count: " + par1ItemStack.stackTagCompound.getInteger("VoidCount"));
		 }

	}
	
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		  super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		  if (!par1ItemStack.hasTagCompound()){
			  par1ItemStack.stackTagCompound = new NBTTagCompound();
			  par1ItemStack.stackTagCompound.setBoolean("ActivatedVoid", false);
			  par1ItemStack.stackTagCompound.setInteger("VoidCount", 0);
		  }
		  if (par1ItemStack.stackTagCompound.getBoolean("ActivatedVoid")&& par3Entity instanceof EntityPlayer && ((EntityPlayer)par3Entity).getHeldItem()==par1ItemStack){
			  EntityPlayer player = (EntityPlayer)par3Entity;
			  for (int i = 0;i<player.inventory.getSizeInventory()-13;i++){
				  if (player.inventory.getStackInSlot(i+9)!=null){
					  par1ItemStack.stackTagCompound.setInteger("VoidCount", par1ItemStack.stackTagCompound.getInteger("VoidCount")+player.inventory.getStackInSlot(i+9).stackSize);
				  }
				  player.inventory.setInventorySlotContents(i+9, null);
			  }
		  }
		  if (par3Entity instanceof EntityPlayer && par1ItemStack.stackTagCompound.getInteger("VoidCount")>=50000){
			  EntityPlayer player = (EntityPlayer)par3Entity;
			  player.setCurrentItemOrArmor(0, new ItemStack(modMain.soulKnow));
		  }
	  }

}
