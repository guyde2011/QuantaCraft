package QuantaCraft.Items;

import QuantaCraft.main.modMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class AdamPickaxe extends ItemPickaxe {

	public AdamPickaxe(ToolMaterial mat , String name){
        super(mat);
        this.setCreativeTab(modMain.tab);
        this.setUnlocalizedName(name);
        LanguageRegistry.addName(this, name);
        GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(modMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
}
