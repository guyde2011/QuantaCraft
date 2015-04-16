package QuantaCraft.GUIs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public interface ISmartGui {
	@SideOnly(Side.CLIENT)
	public void drawBackground(int mouseX , int mouseY , float partialTickTime);
	
	@SideOnly(Side.CLIENT)
	public void drawForeground(int mouseX , int mouseY , float partialTickTime);
	
	@SideOnly(Side.CLIENT)
	public void showTooltips(int mouseX , int mouseY , float partialTickTime);
	
	@SideOnly(Side.CLIENT)
	public void update(int mouseX , int mouseY , float partialTickTime);
	
	@SideOnly(Side.CLIENT)
	public ByteBuf sendInfoToServer(int x , int y , int z , int worldID);
	
	@SideOnly(Side.CLIENT)
	public void updateItem(ItemStack stack);
	
	@SideOnly(Side.SERVER)
	public void read(ByteBuf buf);
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexturePath();
	
	@SideOnly(Side.SERVER)
	public ItemStack getItemStack();
	
	@SideOnly(Side.CLIENT)
	public ByteBuf sendItemStackToServer(int x , int y , int z , int worldID);
	

}
