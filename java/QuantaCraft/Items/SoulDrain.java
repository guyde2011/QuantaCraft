package QuantaCraft.Items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import QuantaCraft.main.createItem;
import QuantaCraft.main.modMain;

public class SoulDrain extends createItem{

	private ItemStack inv[] = new ItemStack[2];
	public SoulDrain(String name) {
		super(name);
		this.maxStackSize = 1;
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (!player.isSneaking()) {
				player.openGui(modMain.instance, 1, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}
		return stack;
	}

}
