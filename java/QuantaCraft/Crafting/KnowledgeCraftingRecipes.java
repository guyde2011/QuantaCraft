package QuantaCraft.Crafting;

import QuantaCraft.main.Knowledges;
import QuantaCraft.main.modMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum KnowledgeCraftingRecipes {
	
	ADAMANTITE_SHOVEL(Knowledges.ADAMANTITE_CRAFTING,modMain.adamantiteTools[0]),
	ADAMANTITE_HOE(Knowledges.ADAMANTITE_CRAFTING,modMain.adamantiteTools[1]),
	ADAMANTITE_AXE(Knowledges.ADAMANTITE_CRAFTING,modMain.adamantiteTools[2]),
	ADAMANTITE_PICKAXE(Knowledges.ADAMANTITE_CRAFTING,modMain.adamantiteTools[3]),
	ADAMANTITE_SWORLD(Knowledges.ADAMANTITE_CRAFTING,modMain.adamantiteTools[4]);
	
	private final Knowledges reqKnow;
	private final Item result;
	private final int dmg;
	
	
	KnowledgeCraftingRecipes(Knowledges know , Item item , int dmgValue){
		reqKnow = know;
		dmg = dmgValue;
		result = item;
	}
	
	KnowledgeCraftingRecipes(Knowledges know , Item item){
		this(know , item , 0);
	}
	
	public Knowledges getRequiredKnowledge(){
		return reqKnow;
	}
	
	public boolean isResultKnowledge(ItemStack itemStack){
		return (itemStack.getItem()==result && itemStack.getItemDamage() == dmg);
	}
	
}
