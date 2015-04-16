package QuantaCraft.Items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import QuantaCraft.main.Knowledges;
import QuantaCraft.main.createItem;

public class KnowledgeBook extends createItem implements IKnowledge{

	public KnowledgeBook(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public static ItemStack addKnowledgeForStack(ItemStack stack,Knowledges[] knows){
		if (!stack.hasTagCompound()){stack.setTagCompound(new NBTTagCompound());}
		stack.stackTagCompound.setIntArray("KnowledgesForQuanta",ItemKnowledge.knowledgeToInt(knows));
		return stack;
	}
	
	

	@Override
	public Knowledges[] getKnowledges() {
		// TODO Auto-generated method stub
		return null;
	}
	
	  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		  par3List.add(EnumChatFormatting.GRAY+"Contains:");
		  if (par1ItemStack.hasTagCompound() && par1ItemStack.stackTagCompound.hasKey("KnowledgesForQuanta")){
			  int[] know = par1ItemStack.stackTagCompound.getIntArray("KnowledgesForQuanta");
		  	for (int i = 0;i<know.length;i++){
			  	par3List.add(EnumChatFormatting.GRAY + "-" + ItemKnowledge.getKnowledgeFromId(know[i]).getName());
		  	}
		  }else {
			  par3List.add(EnumChatFormatting.GRAY+"Nothing");
		  }
	  }

}
