package QuantaCraft.main;



import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.lang.String;


public class createBlock extends Block
{
       public createBlock(int id , String name, Material blockMaterial, float hardness)
       {
   		super(blockMaterial);
   		setHardness(hardness);
   		setBlockName(name);
   		GameRegistry.registerBlock(this, name);
   		LanguageRegistry.addName(this, name);
   		this.setCreativeTab(modMain.tab);
       }
       
       public createBlock(int id , String name, Material blockMaterial, float hardness , String tool , int level)
       {
   		super(blockMaterial);
   		setHarvestLevel(tool, level);
   		setHardness(hardness);
   		setBlockName(name);
   		GameRegistry.registerBlock(this, name);
   		LanguageRegistry.addName(this, name);
   		this.setCreativeTab(modMain.tab);
       }
	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" + this.getUnlocalizedName().substring(5));
	   }
}

