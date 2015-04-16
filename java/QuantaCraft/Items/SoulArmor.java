package QuantaCraft.Items;

import QuantaCraft.main.modMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class SoulArmor extends ItemArmor implements ISpecialArmor {
	private static int curDelay=0;
	private static int delay=20;
	public int isSpeed;
	public int isJump;
	public int isResistant;
	public int isFireRes; 
	
	public SoulArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_,String name,int speed,String RegName,int jump,int res,int fireRes) {
		  super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		  this.maxStackSize=1;
		  this.setUnlocalizedName(name);
		  LanguageRegistry.addName(this, name);
		  GameRegistry.registerItem(this, RegName);
		  this.isSpeed=speed;
		  this.isJump=jump;
		  this.isResistant=res;
		  this.isFireRes = fireRes;
		}
		//This is the MOST important method in this class! This gets called every time a player wearing your armor gets damaged, however it is called before the damage is applied so you can modify it.
		//@Override
		public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
			/* if (source.getDamageType().equals("fall")&&(this).isJump==1) 
				{
					return new ArmorProperties(0, 1.00, Integer.MAX_VALUE); 
				} 
				else if ((this).isResistant==1)
				{
					player.heal((float) (damage*0.3333333));
					return new ArmorProperties(0, 0.75, 40);

				
				} else {
		
					return new ArmorProperties(0, 0.95, 40);
				}
				*/
			return new ArmorProperties(0, 0.16, 40);
		}
		
		@Override
		public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
			/*
			if (this.isResistant==1){
				curDelay++;
				if (curDelay==delay) curDelay=0; armor.damageItem(-2,player);
				
				
			}
*/
		}



		@Override
		public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		  return 4; //How many half shields each piece of armor will fill up on the armor bar
		}
		@Override
		public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
			/*
		  stack.damageItem(damage * 2, entity); //Allows you to control the amount of damage done to each piece of armor, useful for nerfing some suits of armor.
		*/
		}

		@Override //This is pretty self explanatory
		public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
		  if(slot==2) {
		  // return "quantummech:textures/armor/man_layer_2.png";
			  return modMain.modid.toLowerCase()+":textures/models/soul_layer_2.png";
		  } else if (armor!=null && armor.getItem() instanceof SoulArmor){
		  // return "quantummech:textures/armor/modifier_layer_1.png";
			  return modMain.modid.toLowerCase()+":textures/models/soul_layer_1.png";
		  }
		  return null;
		}

		@Override
		public CreativeTabs[] getCreativeTabs() {
				return new CreativeTabs[] {CreativeTabs.tabCombat, modMain.tab}; //This lets me put my armor in as many create tabs as I want, pretty cool right?
			
		}
		@Override
		public boolean getIsRepairable(ItemStack armor, ItemStack stack) {
		  return stack.getItem() == modMain.SoulGem; //Allows certain items to repair this armor.
		}

    String armorFile;
 
    public SoulArmor(int par1, ArmorMaterial par2EnumArmorMaterial, int par3, int par4, String armorFile) {
        super(par2EnumArmorMaterial, par3, par4);
        this.armorFile = armorFile;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
    		
    		ItemStack itemStack, int armorSlot) {

	    ModelBiped armorModel = new ModelBiped();


    		if(itemStack != null){
    		if(itemStack.getItem() instanceof SoulArmor){
    		
    int type = ((ItemArmor)itemStack.getItem()).armorType;

    if(type == 0 ||type == 1 || type == 3){
    armorModel = modMain.proxy.getArmorModel(0);
    }else{
	    armorModel = modMain.proxy.getArmorModel(1);
    }
    		}	

	  
	    	armorModel.bipedHead.showModel = armorSlot == 0;
	    	armorModel.bipedHeadwear.showModel = armorSlot == 0	;
	    	armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
	    	armorModel.bipedRightArm.showModel = armorSlot == 1;
	    	armorModel.bipedLeftArm.showModel = armorSlot == 1;
	    	armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
	    	armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

	    	armorModel.isSneak = entityLiving.isSneaking();
	    	armorModel.isRiding = entityLiving.isRiding();
	    	armorModel.isChild = entityLiving.isChild();
	    	armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0)!= null ? 1 :0;
	    	if(entityLiving instanceof EntityPlayer){
	    	armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
	    	}
	    	return armorModel;
	    	}
	    	return null;
    		}

    		
    		

    		
	    	
    
    
 
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(modMain.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)));
    }
   
 

}
