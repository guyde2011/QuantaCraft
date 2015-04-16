package QuantaCraft.Items;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import QuantaCraft.main.SoulHandler;
import QuantaCraft.main.createItem;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.ISoulGatherer;
import QuantaCraft.main.Interfaces.ISoulItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class SpiritAxe extends ItemAxe implements ISoulGatherer {

	public SpiritAxe(String name , ToolMaterial toolMat) {
		  super(toolMat);
          this.setCreativeTab(modMain.tab);
          this.setUnlocalizedName(name);
          LanguageRegistry.addName(this, name);
          GameRegistry.registerItem(this, this.getUnlocalizedName());


		// TODO Auto-generated constructor stub
	}
	@Override
	public EnumRarity getRarity(ItemStack stack){
		return EnumRarity.rare;
	}
	
	   public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
	    {
	        return true;
	    }	
		@Override
		public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
	    {
	        return true;
	    }

	@Override
	public void addInformation(ItemStack stack , EntityPlayer player , List list , boolean bool){
		if (stack.stackTagCompound!=null && stack.stackTagCompound.hasKey("soulsQuanta")){
			int str = stack.stackTagCompound.getInteger("soulsQuanta")+1;
			EnumChatFormatting format;
			int tier = 0;
			format = EnumChatFormatting.DARK_GRAY;
			if (str>500){
				format = EnumChatFormatting.GRAY;
				tier = 1;
			}
			if (str>1000){
				format = EnumChatFormatting.GREEN;
				tier = 2;
			}
			if (str>2000){
				format = EnumChatFormatting.RED;
				tier = 3;
			}
			if (str>4000){
				format = EnumChatFormatting.YELLOW;
				tier = 4;
			}
			if (str>8000){
				format = EnumChatFormatting.BLUE;
				tier = 5;
			}
			if (str>16000){
				format = EnumChatFormatting.AQUA;
				tier = 6;
			}
			if (str>32000){
				format = EnumChatFormatting.GOLD;
				tier = 7;
			}
			stack.stackTagCompound.setInteger("Tier" , tier);

			list.add("Souls: " + format + (str-1));
			list.add("Tier: " + format + stack.stackTagCompound.getInteger("Tier"));
			
		} else {
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setInteger("soulsQuanta", 0);
			stack.stackTagCompound.setInteger("Tier" , 0);
			stack.stackTagCompound.setInteger("maxSoulCharge", 64000);
			list.add("Souls: " + EnumChatFormatting.DARK_GRAY + 0);
			list.add("Tier: " + EnumChatFormatting.DARK_GRAY + 0);
		}
	}
	@Override 
	public void onCreated(ItemStack stack, World world , EntityPlayer player){
		stack.stackTagCompound.setInteger("soulsQuanta", 0);
		stack.stackTagCompound.setInteger("Tier" , 0);
	}
	
	 @SideOnly(Side.CLIENT)
	 public void registerIcons(IIconRegister par1IconRegister)
	 {
		 this.itemIcon = par1IconRegister.registerIcon(modMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	 }
	 
	 public static int getTier(ItemStack stack){
		 return stack.stackTagCompound.getInteger("Tier");
	 }
	 public static ItemStack setTier(ItemStack stack , int tier){
		 if (stack.stackTagCompound==null){
			 stack.stackTagCompound = new NBTTagCompound();
		 }
		 stack.stackTagCompound.setInteger("Tier" , tier);
		 stack.stackTagCompound.setInteger("soulsQuanta" , (int) (500*Math.pow(2, tier)));
		 return stack;
		 
	 }
	 public static ItemStack addSouls(ItemStack stack , int souls){
		stack.stackTagCompound.setInteger("soulsQuanta", stack.stackTagCompound.getInteger("soulsQuanta")+souls);
		return stack;
	 }
	@Override
	public void onRecharge(ItemStack stack) {
		
		
	}
	@Override
	public boolean canCharge(ItemStack stack) {
		// TODO Auto-generated method stub
		return stack.getItemDamage()>0;
	}
	

}
