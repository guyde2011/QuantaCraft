package QuantaCraft.main;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class QuantaTab extends CreativeTabs {

	public QuantaTab(String tabLabel)
	{
		super(tabLabel);
	}


	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return modMain.knowBook;
	    }
	}
