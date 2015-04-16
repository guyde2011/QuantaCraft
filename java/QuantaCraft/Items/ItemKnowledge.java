package QuantaCraft.Items;

import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import QuantaCraft.main.Knowledges;
import QuantaCraft.main.createItem;
import QuantaCraft.main.modMain;

public class ItemKnowledge extends Item implements KnowledgeGuiBase , IKnowledge{

	public Knowledges knowledge;
	public Knowledges[] knows;
	public int recipeInt;
	public ItemKnowledge(String name , Knowledges[] know , int recipeId) {
		super();
        this.setCreativeTab(modMain.tab);
        this.setUnlocalizedName("knowledge - "+name);
        LanguageRegistry.addName(this, "knowledge - "+name);
        GameRegistry.registerItem(this, "knowledge - "+name);
        setMaxStackSize(1);
		knows = know;
		recipeInt = recipeId;
	}
	
	public ItemKnowledge(String name , Knowledges know , int recipeId) {
		super();
        this.setCreativeTab(modMain.tab);
        this.setUnlocalizedName("knowledge - "+name);
        LanguageRegistry.addName(this, "knowledge - "+name);
        GameRegistry.registerItem(this, "knowledge - "+name);
        setMaxStackSize(1);
		knows = new Knowledges[]{know};
		recipeInt = recipeId;
	}
	
	public boolean canLearn(){
		return true;
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(modMain.modid + ":" + "Knowledge");
    }
    
    @SideOnly(Side.CLIENT)
    public static void showRecipeInGui(Gui gui , RenderItem render , FontRenderer fontRender , Map<Integer,ItemStack> ing , int x , int y){
    	for (int i = 0;i<3;i++){
    		for (int j=0;j<3;j++){
    			if (ing.get(i*3+j)!=null){
    				render.renderItemIntoGUI(fontRender, Minecraft.getMinecraft().getTextureManager(), ing.get(i*3+j),x+63+j*22+18 , y+96+i*22+27);		
    			}
    		}
    	}
    }
    
	@Override 
	public void onCreated(ItemStack stack, World world , EntityPlayer player){
		super.onCreated(stack,world,player);
		
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
	
	public static Knowledges getKnowledgeFromId(int id){
		for (Knowledges know : Knowledges.values()){
			if (know.getId()== id){
				return know;
			}
		}
		return null;
	}
	
	public Knowledges[] getKnowledges(ItemStack stack){
		return new Knowledges[]{Knowledges.NULL};
	}

	@Override
	public void renderGuiName(Gui gui, RenderItem render,
			FontRenderer fontRender, int x , int y) {
		fontRender.drawString(EnumChatFormatting.GRAY+knows[0].getName(), x+40 - knows[0].getName().length()*2, y, 0);
		
	}
    
	@Override
	public void renderGuiRecipe(Gui gui, RenderItem render,
			FontRenderer fontRender, int x , int y) {
		ItemKnowledge.showRecipeInGui(gui, render, fontRender,  modMain.nRecipes.getMap().get(recipeInt).getRecipeIngridients(), x , y);
		render.renderItemIntoGUI(fontRender, Minecraft.getMinecraft().getTextureManager(), (ItemStack) modMain.nRecipes.getMap().get(recipeInt).getResult(),x+104 , y+67);		
		
	}

	@Override
	public Knowledges[] getKnowledges() {
		// TODO Auto-generated method stub
		return new Knowledges[Knowledges.values().length];
	}
	
	public static Knowledges[] intToKnowledge(int[] array){
		Knowledges[] knows = new Knowledges[array.length];
		for (int i = 0;i<array.length;i++){
			knows[i] = getKnowledgeFromId(array[i]);
		}
		return knows;
	}
	
	public static int[] knowledgeToInt(Knowledges[] array){
		int[] knows = new int[array.length];
		for (int i = 0;i<array.length;i++){
			knows[i] = array[i].getId();
		}
		return knows;
	}

	public void addKnowledgeForStack(ItemStack stack){
		if (!stack.hasTagCompound()){stack.setTagCompound(new NBTTagCompound());}
		stack.stackTagCompound.setIntArray("KnowledgesForQuanta",knowledgeToInt(knows));
	}
	
	  public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		  if (stack.stackTagCompound==null) {stack.stackTagCompound = new NBTTagCompound();}
		  addKnowledgeForStack(stack);
	  }
	  
	  public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		  super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		  addKnowledgeForStack(par1ItemStack);
	  }
	  
	  public static Knowledges[] getKnowledgesFromStack(ItemStack stack){
		  if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("KnowledgesForQuanta")){
			  return intToKnowledge(stack.stackTagCompound.getIntArray("KnowledgesForQuanta"));
		  } else {
			  return null;
		  }
	  }
    
	
	
}
