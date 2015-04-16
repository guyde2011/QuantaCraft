package QuantaCraft.main;

import java.util.Random;





import com.mojang.realmsclient.gui.ChatFormatting;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import QuantaCraft.Items.SoulArmor;
import QuantaCraft.Items.SpiritBlade;
import QuantaCraft.Items.soulGem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EventHandlerHelper {
	public static void addSoulsOnKill(LivingHurtEvent event){
		if (event.source.getEntity()!=null && event.source.getEntity() instanceof EntityPlayer && event.entityLiving.getHealth()-event.ammount<=0){
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem()==modMain.spiritBlade){
				ItemStack spiritBlade = player.getCurrentEquippedItem();
				int Tier = SpiritBlade.getTier(spiritBlade);
				player.heal(Math.min(player.getMaxHealth()-player.getHealth(),Tier));
				if (player.inventory.hasItem(modMain.SoulGem)){
					for (int i=0;i<player.inventory.getSizeInventory();i++){
						if (player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).getItem() instanceof soulGem && SoulHandler.getSouls(player.inventory.getStackInSlot(i))<SoulHandler.getMaxCharge(player.inventory.getStackInSlot(i))){
							player.inventory.setInventorySlotContents(i,SoulHandler.addSouls(player.inventory.getStackInSlot(i),5));
							break;
						} 
						if (i==player.inventory.getSizeInventory()-1){
							player.setCurrentItemOrArmor(0, SpiritBlade.addSouls(spiritBlade, 5));
						}
					}
				} else {
					player.setCurrentItemOrArmor(0, SpiritBlade.addSouls(spiritBlade, 1));
				}
			}
		}
	}
	
	public static void addLightKnowledge(LivingUpdateEvent event){
    	if (event.entityLiving instanceof EntityPlayer){
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    		if (player.worldObj.getWorldTime()==1100){
    			if (player.posY>=150d){
    				Random rand = new Random();
    	    		if (rand.nextInt(12)==2){
    	    			player.inventory.addItemStackToInventory(new ItemStack(modMain.knowLight));
    	    			if (!player.worldObj.isRemote){
    	    				player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You feel the sun rises"));
    	    			}
    	    		}
    			}
    		}
    	}
    }
	
	public static void damageBackSoulArmor(LivingHurtEvent event){
		if (event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			boolean bool = true;
			for (int i = 0;i<4;i++){
				bool = bool && player.inventory.armorInventory[i]!=null && (player.inventory.armorInventory[i].getItem() instanceof SoulArmor);
			}
			if (bool &&  SoulHandler.getSoulsInInventory(player)>=event.ammount*2){
				SoulHandler.useSoulsFromInventory(player, (int)event.ammount*2);
				event.ammount = 0;
				event.setCanceled(true);
			}
		}
	}
}
