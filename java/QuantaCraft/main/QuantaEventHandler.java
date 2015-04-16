package QuantaCraft.main;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;








import QuantaCraft.Items.SoulArmor;
import QuantaCraft.Items.SpiritBlade;
import QuantaCraft.Items.soulGem;
import QuantaCraft.main.Interfaces.ISoulGatherer;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class QuantaEventHandler{

	
	public static void writeArmor(boolean on,EntityPlayer player)
	{
		float f = 1;
		NBTTagCompound tag = new NBTTagCompound();
		if (on){f=2;}
	    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	    player.capabilities.writeCapabilitiesToNBT(nbttagcompound1);
	    nbttagcompound1.setBoolean("invulnerable", player.capabilities.disableDamage);
    	nbttagcompound1.setBoolean("mayfly", on);
	    if (!on){
		    nbttagcompound1.setBoolean("flying", on);
	    }
	    nbttagcompound1.setBoolean("instabuild",player.capabilities.isCreativeMode);
	    nbttagcompound1.setBoolean("mayBuild", player.capabilities.allowEdit);
	    if (!on){
	    	nbttagcompound1.setFloat("flySpeed", f*0.05f);
	    	nbttagcompound1.setFloat("walkSpeed", f*0.1f);
	    	speed.put(player.getDisplayName(), false);
	    } 
	    tag.setTag("abilities", nbttagcompound1);
	    player.capabilities.readCapabilitiesFromNBT(tag);
	   // player.
		player.stepHeight=f-0.5f;
	    
	}
	
	public static void writeSpeed(boolean on , EntityPlayer player){
		float f = 1;
		NBTTagCompound tag = new NBTTagCompound();
		if (on){f=2;}
	    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	    nbttagcompound1.setBoolean("invulnerable", player.capabilities.disableDamage);
    	nbttagcompound1.setBoolean("mayfly", player.capabilities.allowFlying);
		nbttagcompound1.setBoolean("flying", player.capabilities.isFlying);
	    nbttagcompound1.setBoolean("instabuild",player.capabilities.isCreativeMode);
	    nbttagcompound1.setBoolean("mayBuild", player.capabilities.allowEdit);
	    nbttagcompound1.setFloat("flySpeed", f*0.075f);
	    nbttagcompound1.setFloat("walkSpeed", f*0.2f);
	    tag.setTag("abilities", nbttagcompound1);
	    player.capabilities.readCapabilitiesFromNBT(tag);
	}
  
	public static Map<String,Boolean> flight = new HashMap<String,Boolean>();
	public static Map<String,Boolean> speed = new HashMap<String,Boolean>();
    
	@SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event){
    	EventHandlerHelper.addLightKnowledge(event);
    	if (event.entityLiving instanceof EntityPlayer){
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    		ItemStack[] armor1 = player.inventory.armorInventory;
			boolean bool1 = true;
			for (int i = 0;i<4;i++){
				bool1 = armor1[i]!=null && armor1[i].getItem() instanceof SoulArmor && bool1;
			}
			if (bool1){

			}
			if (bool1 && flight.containsKey(player.getDisplayName()) && !flight.get(player.getDisplayName())){
				flight.put(player.getDisplayName(), true);
				writeArmor(true, player);
				return;
			}
			
			if (!flight.containsKey(player.getDisplayName())){
				flight.put(player.getDisplayName(), false);
			}
			if (flight.get(player.getDisplayName()) && !player.capabilities.allowFlying){
				flight.put(player.getDisplayName(), false);
			}
    		if (player.capabilities.allowFlying==true && flight.containsKey(player.getDisplayName()) && flight.get(player.getDisplayName())){
    			ItemStack[] armor = player.inventory.armorInventory;
    			boolean bool = true;
    			for (int i = 0;i<4;i++){
    				bool = armor[i]!=null && armor[i].getItem() instanceof SoulArmor && bool;
    			}
    			if (!bool){
    				flight.put(player.getDisplayName(), false);
    				writeArmor(false, player);
    				if (player.capabilities.isCreativeMode){
    					player.capabilities.isFlying = true;
    					player.capabilities.allowFlying = true;
    				}
    			}
    		}
    	}
    }
    
    @SubscribeEvent
    public void breakBlock(BreakEvent event){
    	if (event.getPlayer()!=null){
    		ItemStack stack = event.getPlayer().getHeldItem();
    		EntityPlayer player = event.getPlayer();
    		if (stack!=null && stack.getItem() instanceof ISoulGatherer && stack.getItem() instanceof ItemTool){
    			if (player.inventory.hasItem(modMain.SoulGem)){
					for (int i=0;i<player.inventory.getSizeInventory();i++){
						if (player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).getItem() instanceof soulGem && SoulHandler.getSouls(player.inventory.getStackInSlot(i))<SoulHandler.getMaxCharge(player.inventory.getStackInSlot(i))){
							player.inventory.setInventorySlotContents(i,SoulHandler.addSouls(player.inventory.getStackInSlot(i),1));
							break;
						} 
						if (i==player.inventory.getSizeInventory()-1){
							player.setCurrentItemOrArmor(0, SpiritBlade.addSouls(stack, 1));
						}
					}
    			} else {
    				player.setCurrentItemOrArmor(0, SpiritBlade.addSouls(stack, 1));
    			}
    		} 
    		
    	}
    }
    @SubscribeEvent
    public void onEntityDeath(LivingDropsEvent event){
    	if (event.entityLiving.isEntityUndead()){
    		Random rand = new Random();
    		if (rand.nextInt(250)==2){
    			EntityItem ent = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(modMain.darkSorceryKnow));
    			event.entity.worldObj.spawnEntityInWorld(ent);
    		}
    	}
    	if (event.entityLiving instanceof EntityWither){
    		EntityItem ent = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(modMain.darkSorceryKnow));
			event.entity.worldObj.spawnEntityInWorld(ent);
    	}
    }
	@SubscribeEvent
	public void onEntityBeingHurt(LivingHurtEvent event){
		EventHandlerHelper.addSoulsOnKill(event);
		EventHandlerHelper.damageBackSoulArmor(event);
	}
	
}
