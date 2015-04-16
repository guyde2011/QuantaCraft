package QuantaCraft.main;

import java.util.List;

import QuantaCraft.main.Interfaces.ISoulItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class SoulTool extends Item implements ISoulItem{

	public int MaxCharge = 0;
	public SoulTool(String name , int maxCharge){
		super();
		this.MaxCharge = maxCharge; 
        this.setCreativeTab(modMain.tab);
        this.setUnlocalizedName(name);
        LanguageRegistry.addName(this, name);
        GameRegistry.registerItem(this, this.getUnlocalizedName());
        this.setMaxDamage(maxCharge);
        this.maxStackSize = 1;
	}
	
	@Override
	public void addInformation(ItemStack stack , EntityPlayer player , List list , boolean bool){
		if (stack.stackTagCompound==null){
			stack.stackTagCompound = new NBTTagCompound();
		}	
		if (stack.stackTagCompound==null || !stack.stackTagCompound.hasKey("soulsQuanta")){
			stack.stackTagCompound.setInteger("maxSoulCharge", this.MaxCharge);
			stack.stackTagCompound.setInteger("soulsQuanta", 0);
		}
		list.add(EnumChatFormatting.GRAY + "Souls: " + EnumChatFormatting.RED + stack.stackTagCompound.getInteger("soulsQuanta") + " / " + stack.stackTagCompound.getInteger("maxSoulCharge"));
	}
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(modMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

	@Override
	public boolean canCharge(ItemStack stack) {
		return stack.getItemDamage()>0;
	}
}
