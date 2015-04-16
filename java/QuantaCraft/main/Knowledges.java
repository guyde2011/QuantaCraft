package QuantaCraft.main;

public enum Knowledges {

	ADAMANTITE_CRAFTING(0,"Adamantite Crafting"),
	SMART_STORAGE(1,"Smart Storage"),
	SOUL_CONTROL(2,"Soul Control"),
	LIGHT_SORCERY(3,"Light Sorcery"),
	VIS(6,"Vis Manipulating"),
	DARK_SORCERY(4,"Dark Sorcery"),
	ADVANCED_MACHINARY(7,"Advanced Machinary"),
	NULL(5,"Nothing");
	
	
	private final int id;
	private final String name;
	Knowledges(int i , String a){
		id = i;
		name = a;
	}
	
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	
	public String toString(){
		return name;
	}
}
