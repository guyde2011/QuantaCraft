package QuantaCraft.main;

import org.apache.logging.log4j.Level;

import thaumcraft.api.aspects.Aspect;
import ic2.api.recipe.RecipeInputItemStack;
import QuantaCraft.Blocks.Blocks.AdamOre;
import QuantaCraft.Blocks.Blocks.AdamantiteContainer;
import QuantaCraft.Blocks.Blocks.AlumOre;
import QuantaCraft.Blocks.Blocks.ConstructorLiquidModifierTable;
import QuantaCraft.Blocks.Blocks.ConstructorTable;
import QuantaCraft.Blocks.Blocks.DarkOre;
import QuantaCraft.Blocks.Blocks.DirectionalStorage;
import QuantaCraft.Blocks.Blocks.HologramStand;
import QuantaCraft.Blocks.Blocks.LightOre;
import QuantaCraft.Blocks.Blocks.PickStorage;
import QuantaCraft.Blocks.Blocks.SmartContainer;
import QuantaCraft.Blocks.Blocks.VoidPickStorage;
import QuantaCraft.Blocks.Blocks.VoidStorage;
import QuantaCraft.Blocks.Cables.BasicCableBlock;
import QuantaCraft.Blocks.Cables.ExtractCableBlock;
import QuantaCraft.Blocks.Cables.ItemsInvCableBlock;
import QuantaCraft.Blocks.Cables.RFCableBlock;
import QuantaCraft.Blocks.Cables.RoundRobinCableBlock;
import QuantaCraft.Blocks.Cables.TileEntityExtractCable;
import QuantaCraft.Blocks.Cables.TileEntityItemsCable;
import QuantaCraft.Blocks.Cables.TileEntityItemsInvCable;
import QuantaCraft.Blocks.Cables.TileEntityRFCable;
import QuantaCraft.Blocks.Cables.TileEntityRoundRobinCable;
import QuantaCraft.Blocks.HoloControllers.HologramControllerArmor;
import QuantaCraft.Blocks.HoloControllers.HologramControllerColor;
import QuantaCraft.Blocks.HoloControllers.HologramControllerEquip;
import QuantaCraft.Blocks.HoloControllers.HologramControllerMulti;
import QuantaCraft.Blocks.HoloControllers.HologramControllerOwner;
import QuantaCraft.Blocks.HoloControllers.HologramControllerRedstone;
import QuantaCraft.Blocks.HoloControllers.HologramControllerTeleport;
import QuantaCraft.Blocks.HoloControllers.HologramControllerUnequip;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloArmorControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloColorControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloEquipControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloMultiControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloOwnerControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloRedstoneControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloTeleportControl;
import QuantaCraft.Blocks.HoloControllers.TileEntityHoloUnequipControl;
import QuantaCraft.Blocks.TileEntities.TileEntityAdamantiteContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityBasicCable;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorFluidModifier;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityDirectionalStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;
import QuantaCraft.Blocks.TileEntities.TileEntityPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntitySmartContainer;
import QuantaCraft.Blocks.TileEntities.TileEntityTank;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidPickStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityVoidStorage;
import QuantaCraft.Crafting.BookKnowledgeCrafting;
import QuantaCraft.Crafting.KnowledgeCrafting;
import QuantaCraft.Crafting.LiquidCrafter.ConstructorLiquidRecipesHandler;
import QuantaCraft.Crafting.Table.KnowledgeRecipesHandler;
import QuantaCraft.GUIs.GuiHandler;
import QuantaCraft.Items.AdamAxe;
import QuantaCraft.Items.AdamHoe;
import QuantaCraft.Items.AdamPickaxe;
import QuantaCraft.Items.AdamShovel;
import QuantaCraft.Items.AdamSword;
import QuantaCraft.Items.ItemKnowledge;
import QuantaCraft.Items.KnowledgeBook;
import QuantaCraft.Items.SoulArmor;
import QuantaCraft.Items.SoulDrain;
import QuantaCraft.Items.SpiritAxe;
import QuantaCraft.Items.SpiritBlade;
import QuantaCraft.Items.SpiritPickaxe;
import QuantaCraft.Items.SpiritShovel;
import QuantaCraft.Items.VoidGem;
import QuantaCraft.Items.soulGem;
import QuantaCraft.keys.KeyBindings;
import QuantaCraft.keys.KeyEventHandler;
import QuantaCraft.keys.KeySoulSpeed;
import QuantaCraft.main.Commands.SampleCommand;
import QuantaCraft.main.Commands.SoulCommand;
import QuantaCraft.main.Commands.TextPlateCommand;
import QuantaCraft.neiRecipes.ConstructorLiquidRecipe;
import QuantaCraft.neiRecipes.ConstructorShapedRecipe;
import QuantaCraft.neiRecipes.ConstructorShapelessRecipe;
import QuantaCraft.packets.ClientToServerMessage;
import QuantaCraft.packets.CommandsHandler;
import QuantaCraft.packets.KeyPressedUpdateCommand;
import QuantaCraft.packets.MessageCommand;
import QuantaCraft.packets.PacketsCommandsRegistry;
import QuantaCraft.packets.ServerCommandsHandler;
import QuantaCraft.packets.ServerToClientMessage;
import QuantaCraft.packets.SmartContainerSyncCommand;
import QuantaCraft.packets.SoulTPCommand;
import QuantaCraft.packets.WrenchCommand;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = modMain.modid,  name = "QuantaCraft", version = modMain.version , guiFactory = modMain.guifactory)
public class modMain
{
	@SidedProxy(clientSide = "QuantaCraft.main.ClientProxy", serverSide = "QuantaCraft.main.CommonProxy")
	public static CommonProxy proxy;
	public static final String guifactory = "QuantaCraft.main.GuiFactory";
	public static final String modid = "QuantaCraft";
    public static final String version = "0.1.4 r1";
    @Instance("QuantaCraft")
    public static modMain instance;
    public static final String Channel = modid + ":" + "ChannelMain";
    public static PacketsCommandsRegistry packetCommands;
    public static Aspect Reflection;
	public static Aspect Refraction;
	public static Aspect Division;
    public static Item lightGem , darkGem;
    public static Item aluminum;
    public static Item alumPlate;
    public static Block adamantiteContainer;
    public static Block dirStorage;
    public static Block darkOre , lightOre , adamOre, alumOre;
    public static Block constructorTable;
    public static Block fluidTable;
    public static Block voidStorage;
    public static Block picksStorage;
    public static Block hologramStand;
    public static Block pickVoidStorage;
    public static Block lightBlock , darkBlock;
    public static Block holoContOwner;
    public static Block holoContMulti;
    public static Block holoContArmor;
    public static Block holoContColor;
    public static Block holoContTeleport;
    public static Block cable;
    public static Block cableExtract;
    public static Block cableRoundRobin;
    public static Block tank;
    public static Block cableInv;
    public static Block BaseCont;
    public static Block holoContRedstone;
    public static Block holoContEquip;
    public static Block holoContUnequip;
    public static Block CableRF;
    public static Item voidGem , EthGem;
    public static Item adamantite , lightningAdamantite , adamDust;
    public static Item voidBucket;
    public static Item SoulGem;
    public static Item knowBook;
    public static Item knowSto;
    public static Item knowVis;
    public static Item lightningAdamantiteUp , voidBucketUp;
    public static Item spiritBlade , soulPickaxe , soulAxe , soulShovel;
    public static Item soulShard;
    public static Item soulDrain;
    public static Item soulKnow;
    public static Item darkSorceryKnow;
    public static Item textPlate;
    public static Block SmartCont;
	public static ToolMaterial soulGem;
	public static ArmorMaterial soulArmorMat;
	public static ToolMaterial adamantiteToolMat;
	public static KnowledgeRecipesHandler nRecipes = new KnowledgeRecipesHandler();
	public static ConstructorLiquidRecipesHandler fRecipes = new ConstructorLiquidRecipesHandler();
    public static Item[] adamantiteTools = new Item[5];
	public static Item knowLight;
	public static CreativeTabs tab = new QuantaTab("QuantaTab");
	public static boolean TELoaded;
	public static Item soulHelm , soulLeggings , soulChest , soulBoots;
	public static SimpleNetworkWrapper networkWrapper;
	
	public static void sendCommand(WrenchCommand cmd){
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT && ClientProxy.pickBlock.isPressed()){
			networkWrapper.sendToServer(new ClientToServerMessage(cmd));
		}
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    	FMLCommonHandler.instance().bus().register(new KeyEventHandler());
    	textPlate = new createItem("Text Plate");
    	voidStorage = new VoidStorage(0,"Void Storage Unit" , Material.ice,2f);
    	picksStorage = new PickStorage(0,"Pickable Storage Unit" , Material.wood,2f);
    	darkOre = new DarkOre(0,"Dark Gem Ore",Material.rock,2.5f);
    	lightOre = new LightOre(0,"Light Gem Ore",Material.rock,2.5f);
    	adamOre = new AdamOre(0,"Adamantite Ore",Material.rock,5f);
    	alumOre = new AlumOre(0,"Aluminum Ore",Material.rock,3.5f);
    	lightGem = new createItem("Light Gem");
    	darkGem = new createItem("Dark Gem");
    	constructorTable = new ConstructorTable(0,"Constructor's Table",Material.wood,1f);
    	soulGem = EnumHelper.addToolMaterial("Soul Gem", 5, 2000, 12f, 6, 24);
    	adamantiteToolMat = EnumHelper.addToolMaterial("Adamantite", 4, 4000, 10f, 5 , 17);
    	adamantiteContainer = new AdamantiteContainer(0, "Storage Unit" , Material.wood, 3f);
    	soulShovel = new SpiritShovel("Spiritual Shovel" , soulGem);
    	soulAxe = new SpiritAxe("Spiritual Axe" , soulGem);
    	soulPickaxe = new SpiritPickaxe("Spiritual Pickaxe" , soulGem);
		// some example code
    	adamantiteTools[0] = new AdamPickaxe(adamantiteToolMat ,"Adamantite Pickaxe" );
    	adamantiteTools[1] = new AdamSword(adamantiteToolMat ,"Adamantite Sword" );
    	adamantiteTools[2] = new AdamAxe(adamantiteToolMat ,"Adamantite Axe" );
    	adamantiteTools[3] = new AdamShovel(adamantiteToolMat ,"Adamantite Shovel" );
    	adamantiteTools[4] = new AdamHoe(adamantiteToolMat ,"Adamantite Hoe" );
    	soulDrain = new ItemKnowledge("Adamantite Reforging", Knowledges.ADAMANTITE_CRAFTING,0);
    	voidGem = new VoidGem("Void Gem");
    	EthGem = new createItem("Ethereal Gem");
    	//voidBucket = new createItem("Void Bucket");
    	adamantite = new createItem("Adamantite");
    	aluminum = new createItem("Aluminum");
    	spiritBlade = new SpiritBlade("Spiritual Blade", soulGem);
    	cable = new BasicCableBlock("Items Cable", Material.iron, 2f, false, 0, "Cable1");
    	cableInv = new ItemsInvCableBlock("Inventory Priority Cable", Material.iron, 2f, false, 0, "Cable4");
    	cableExtract = new ExtractCableBlock("Extract Cable", Material.iron, 2f, false, 0, "Cable3");
    	cableRoundRobin = new RoundRobinCableBlock("Round Robin Cable", Material.iron, 2f, false, 0, "Cable2");
    	knowBook = new KnowledgeBook("Knowledge Book");
    	lightBlock = new createBlock(0, "Light Gem Block", Material.ice, 2.0f, "pickaxe", 2);
    	darkBlock = new createBlock(0, "Dark Gem Block", Material.ice, 2.0f, "pickaxe", 2);
    	dirStorage = new DirectionalStorage(0,"Directional Storage Unit" ,Material.wood, 2.0f);
    	SoulGem = new soulGem("Soul Gem", 32000);
    	darkSorceryKnow = new ItemKnowledge("Dark Sorcery",Knowledges.DARK_SORCERY,1);
    	pickVoidStorage = new VoidPickStorage(0, "Pickable Void Storage Unit", Material.iron, 3.0f);
    	alumPlate = new createItem("Aluminum Plate");
    	/*         ----==Game Registry Recipes==----         */
    	/* Book + Pages = Book */GameRegistry.addRecipe(new BookKnowledgeCrafting());
    	/* Pages + Pages = Book */GameRegistry.addRecipe(new KnowledgeCrafting());
    	/* Adamantite Smelting */GameRegistry.addSmelting(adamOre, new ItemStack(adamantite),2.0f);
    	/* Constructor Table */GameRegistry.addRecipe(new ItemStack(constructorTable),new Object[] { "BAB", "ACA","BAB", 'A',Item.getItemFromBlock(Blocks.log), 'B' ,Item.getItemFromBlock(Blocks.iron_block),'C' , Item.getItemFromBlock(Blocks.crafting_table)});
    	
    	/* Aluminum Plate*/GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(alumPlate,12 ), true ,new Object[] { "AAA", "AAA", 'A',"ingotAluminum"}));
    	/* Constructor's Table Recipes */
    	/* Adam Pickaxe */nRecipes.addRecipe(new ItemStack(adamantiteTools[0],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{"BBB"," A "," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Dark Gem */nRecipes.addRecipe(new ItemStack(voidGem,1), Knowledges.DARK_SORCERY, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , darkGem , "B" ,Items.diamond});
    	/* Dark Gem Block */nRecipes.addRecipe(new ItemStack(darkBlock,1), Knowledges.DARK_SORCERY, new String[]{"AAA","AAA","AAA"}, new Object [] {"A" , darkGem});
    	/* Light Gem Block */nRecipes.addRecipe(new ItemStack(lightBlock,1), Knowledges.LIGHT_SORCERY, new String[]{"AAA","AAA","AAA"}, new Object [] {"A" , lightGem});
    	/* Adam Hoe */nRecipes.addRecipe(new ItemStack(adamantiteTools[4],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{"BB "," A "," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Adam Hoe */nRecipes.addSecretRecipe(new ItemStack(adamantiteTools[4],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{" BB"," A "," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Adam Shovel */nRecipes.addRecipe(new ItemStack(adamantiteTools[3],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{" B "," A "," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Adam Axe */nRecipes.addRecipe(new ItemStack(adamantiteTools[2],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{"BB ","BA "," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Adam Axe */nRecipes.addSecretRecipe(new ItemStack(adamantiteTools[2],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{" BB"," AB"," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Adam Sword */nRecipes.addRecipe(new ItemStack(adamantiteTools[1],1), Knowledges.ADAMANTITE_CRAFTING, new String[]{" B "," B "," A "}, new Object [] {"A" , Items.stick , "B" ,adamantite});
    	/* Soul Blade */nRecipes.addRecipe(new ItemStack(this.spiritBlade,1), new Knowledges[]{Knowledges.ADAMANTITE_CRAFTING,Knowledges.SOUL_CONTROL}, new String[]{" A "," A "," B "}, new Object [] {"A" , SoulGem , "B" ,adamantite});
    	/* Soul Pickaxe */nRecipes.addRecipe(new ItemStack(this.soulPickaxe,1), new Knowledges[]{Knowledges.ADAMANTITE_CRAFTING,Knowledges.SOUL_CONTROL}, new String[]{"AAA"," B "," B "}, new Object [] {"A" , SoulGem , "B" ,adamantite});
    	/* Soul Axe */nRecipes.addRecipe(new ItemStack(this.soulAxe,1), new Knowledges[]{Knowledges.ADAMANTITE_CRAFTING,Knowledges.SOUL_CONTROL}, new String[]{"AA ","AB "," B "}, new Object [] {"A" , SoulGem , "B" ,adamantite});
    	/* Soul Axe */nRecipes.addSecretRecipe(new ItemStack(this.soulAxe,1), new Knowledges[]{Knowledges.ADAMANTITE_CRAFTING,Knowledges.SOUL_CONTROL}, new String[]{" AA"," BA"," B "}, new Object [] {"A" , SoulGem , "B" ,adamantite});
    	/* Soul Shovel */nRecipes.addRecipe(new ItemStack(this.soulShovel,1), new Knowledges[]{Knowledges.ADAMANTITE_CRAFTING,Knowledges.SOUL_CONTROL}, new String[]{" A "," B "," B "}, new Object [] {"A" , SoulGem , "B" ,adamantite});
    	BaseCont = new createBlock(0,"Controller",Material.ground,2f);
    	/* Ethereal Gem */nRecipes.addRecipe(new ItemStack(EthGem,1), Knowledges.LIGHT_SORCERY, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , lightGem , "B" ,Items.diamond});
    	knowLight = new ItemKnowledge("Light Sorcery", Knowledges.LIGHT_SORCERY, nRecipes.getMap().keySet().size()-1);
    	/* Soul Gem */nRecipes.addRecipe(new ItemStack(SoulGem), Knowledges.SOUL_CONTROL,new String[]{" A ", " C ", " B "} , new Object[] {"A", voidGem , "B" , EthGem, "C" , Item.getItemFromBlock(Blocks.soul_sand)});
    	soulKnow = new ItemKnowledge("Soul Control", Knowledges.SOUL_CONTROL, nRecipes.getMap().keySet().size()-1);
    	/* Void Storage Unit */nRecipes.addRecipe(new ItemStack(voidStorage,1), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.DARK_SORCERY}, new String[]{"ABA","BCB","ABA"}, new Object [] {"A" ,alumPlate , "B" ,Item.getItemFromBlock(darkBlock),"C",Item.getItemFromBlock(this.adamantiteContainer)});
    	/* Storage Unit */nRecipes.addRecipe(new ItemStack(adamantiteContainer,1), new Knowledges[]{Knowledges.SMART_STORAGE}, new String[]{"ABA","B B","ABA"}, new Object [] {"A" ,alumPlate , "B" ,Item.getItemFromBlock(Blocks.log)});
    	knowSto = new ItemKnowledge("Smart Storage", Knowledges.SMART_STORAGE, nRecipes.getMap().keySet().size()-1);
    	/* Directional Storage Unit */nRecipes.addRecipe(new ItemStack(dirStorage,1), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.LIGHT_SORCERY}, new String[]{"ABA","BCB","ABA"}, new Object [] {"A" ,alumPlate , "B" ,Item.getItemFromBlock(lightBlock),"C",Item.getItemFromBlock(this.adamantiteContainer)});
    	/* Pickable Storage Unit */nRecipes.addRecipe(new ItemStack(picksStorage,1), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.LIGHT_SORCERY,Knowledges.ADVANCED_MACHINARY}, new String[]{"ABA","BCB","ABA"}, new Object [] {"A" ,alumPlate , "B" ,EthGem,"C",Item.getItemFromBlock(this.adamantiteContainer)});
    	/* Pickable Void Storage Unit */nRecipes.addRecipe(new ItemStack(pickVoidStorage,1), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.LIGHT_SORCERY,Knowledges.DARK_SORCERY,Knowledges.ADVANCED_MACHINARY}, new String[]{"ABA","BCB","ABA"}, new Object [] {"A" ,alumPlate , "B" ,EthGem,"C",Item.getItemFromBlock(this.voidStorage)});
    	//nRecipes.addShapelessRecipe(new ItemStack(soulKnow,1), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.DARK_SORCERY,Knowledges.DARK_SORCERY}, new String[]{"ABA","BCB","ABA"}, new Object [] {"A" ,Item.getItemFromBlock(Blocks.planks) , "B" ,EthGem,"C",Item.getItemFromBlock(this.voidStorage)});
    	MinecraftForge.EVENT_BUS.register(new QuantaEventHandler());
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(soulDrain),1,1,3));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(soulDrain),1,1,5));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(soulDrain),1,1,15));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(knowSto),1,1,10));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(knowSto),1,1,5));
        RecipeSorter.register("QuantaCraft:Adding Knowledge to Book Crafting",BookKnowledgeCrafting.class,Category.UNKNOWN,"after:");
        RecipeSorter.register("QuantaCraft:Knowledge to Book Crafting",KnowledgeCrafting.class,Category.UNKNOWN,"after:");
        soulArmorMat = EnumHelper.addArmorMaterial("soul Armor",500, new int[]{4, 9, 7, 4}, 50);
        soulChest = new SoulArmor(soulArmorMat, 15, 1, "Soul Chestplate",0,"Soul Chestplate", 0, 0, 0);
		soulLeggings = new SoulArmor(soulArmorMat, 15, 2, "Soul Leggings",0,"Soul Leggings", 0, 0, 0);
		soulBoots = new SoulArmor(soulArmorMat, 15, 3, "Soul Boots",0,"Soul Boots", 0, 0, 0);
		soulHelm = new SoulArmor(soulArmorMat, 15, 0, "Soul Helmet",0,"Soul Helmet", 0, 0, 0);
		hologramStand = new HologramStand(0,"Hologram Stand",Material.iron,1f);
		holoContOwner = new HologramControllerOwner(0,"Hologram Safe Controller",Material.iron,2f);
		holoContMulti = new HologramControllerMulti(0,"Hologram Multi Controller",Material.iron,2f);
		holoContArmor = new HologramControllerArmor(0,"Hologram Armor Controller",Material.iron,2f);
		holoContColor = new HologramControllerColor(0,"Hologram Color Controller",Material.iron,2f);
		holoContEquip = new HologramControllerEquip(0,"Hologram Equip Controller",Material.iron,2f);
		holoContUnequip = new HologramControllerUnequip(0,"Hologram Unequip Controller",Material.iron,2f);
		holoContTeleport = new HologramControllerTeleport(0,"Hologram Teleport Controller",Material.iron,2f);
		holoContRedstone = new HologramControllerRedstone(0,"Hologram Redstone Controller",Material.iron,2f);
		/* 4 * Holo Stand */nRecipes.addRecipe(new ItemStack(hologramStand,4), new Knowledges[]{Knowledges.LIGHT_SORCERY,Knowledges.ADVANCED_MACHINARY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , Item.getItemFromBlock(darkBlock) , "B" ,EthGem});
		/* 3 * Controller */nRecipes.addRecipe(new ItemStack(BaseCont,3), new Knowledges[]{Knowledges.LIGHT_SORCERY,Knowledges.ADAMANTITE_CRAFTING,Knowledges.ADVANCED_MACHINARY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , Item.getItemFromBlock(Blocks.stone) , "B" ,adamantite});
		/* Owner Controller */nRecipes.addRecipe(new ItemStack(holoContOwner), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , Items.gold_nugget , "B" ,Item.getItemFromBlock(BaseCont)});
		/* Multi Controller */nRecipes.addRecipe(new ItemStack(holoContMulti,4), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{" A ","ABA"," A "}, new Object [] {"B" , Items.ender_pearl , "A" ,Item.getItemFromBlock(BaseCont)});
		/* Armor Controller */nRecipes.addRecipe(new ItemStack(holoContArmor), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{" A ","ABA"," A "}, new Object [] {"A" , Item.getItemFromBlock(Blocks.crafting_table) , "B" ,Item.getItemFromBlock(BaseCont)});
		/* Color Controller */nRecipes.addRecipe(new ItemStack(holoContColor), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , Items.dye, "B" ,Item.getItemFromBlock(BaseCont)});
		/* Equip Controller */nRecipes.addRecipe(new ItemStack(holoContEquip), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{" A "," B ","   "}, new Object [] {"A" , Items.golden_boots , "B" ,Item.getItemFromBlock(holoContArmor)});
		/* Unequip Controller */nRecipes.addRecipe(new ItemStack(holoContUnequip), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{" A "," B ","   "}, new Object [] {"A" , Items.fermented_spider_eye , "B" ,Item.getItemFromBlock(holoContEquip)});
		/* Redstone Controller */nRecipes.addRecipe(new ItemStack(holoContRedstone), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"A" , Items.redstone , "B" ,Item.getItemFromBlock(BaseCont)});
		/* Soul Boots */nRecipes.addRecipe(new ItemStack(this.soulBoots,1), new Knowledges[]{Knowledges.SOUL_CONTROL}, new String[]{"   ","B B","BAB"}, new Object [] {"B" , SoulGem , "A" ,Items.nether_star});
		/* Soul Helmet */nRecipes.addRecipe(new ItemStack(this.soulHelm,1), new Knowledges[]{Knowledges.SOUL_CONTROL}, new String[]{"   ","BBB","BAB"}, new Object [] {"B" , SoulGem , "A" ,Items.nether_star});
		/* Soul Plate */nRecipes.addRecipe(new ItemStack(this.soulChest,1), new Knowledges[]{Knowledges.SOUL_CONTROL}, new String[]{"BAB","BBB","BBB"}, new Object [] {"B" , SoulGem , "A" ,Items.nether_star});
		/* Soul Leggings */nRecipes.addRecipe(new ItemStack(this.soulLeggings,1), new Knowledges[]{Knowledges.SOUL_CONTROL}, new String[]{"BBB","B B","BAB"}, new Object [] {"B" , SoulGem , "A" ,Items.nether_star});
		/* Text Plate */nRecipes.addRecipe(new ItemStack(this.textPlate,16), new Knowledges[]{Knowledges.LIGHT_SORCERY}, new String[]{" B ","BAB"," B "}, new Object [] {"B" , alumPlate , "A" ,Items.paper});
		/* Inv Cables */nRecipes.addRecipe(new ItemStack(this.cableInv,8), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.ADVANCED_MACHINARY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"B" , alumPlate , "A" ,Item.getItemFromBlock(cable)});
		/* Extract Cables */nRecipes.addRecipe(new ItemStack(this.cableExtract,8), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.ADVANCED_MACHINARY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"B" , Items.gold_ingot , "A" ,Item.getItemFromBlock(cable)});
		/* Round Robin Cables */nRecipes.addRecipeStacks(new ItemStack(this.cableRoundRobin,8), new Knowledges[]{Knowledges.SMART_STORAGE,Knowledges.ADVANCED_MACHINARY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"B" , new ItemStack(Items.dye,1,4) , "A" ,new ItemStack(cable)});
		/* Teleport Controller */nRecipes.addRecipe(new ItemStack(this.holoContTeleport,8), new Knowledges[]{Knowledges.SOUL_CONTROL,Knowledges.LIGHT_SORCERY}, new String[]{"AAA","ABA","AAA"}, new Object [] {"B" , Items.ender_pearl , "A" ,Item.getItemFromBlock(BaseCont)});
		adamDust = new createItem("Adamantite Dust");
    	GameRegistry.addSmelting(adamDust, new ItemStack(adamantite),2.0f);
    	OreDictionary.registerOre("oreAluminum", alumOre);
    	OreDictionary.registerOre("ingotAluminum", aluminum);
    	GameRegistry.addSmelting(alumOre, new ItemStack(aluminum), 2f);
    	fluidTable = new ConstructorLiquidModifierTable(0,"Constructor's Modifier Table",Material.iron,2f);
    	fRecipes.addShapelessRecipe(new ItemStack[]{new ItemStack(Blocks.glass_pane),new ItemStack(lightGem)}, new ItemStack(cable,32) , 100, new Knowledges[]{Knowledges.LIGHT_SORCERY,Knowledges.SMART_STORAGE}, FluidRegistry.WATER);
    	/* Constructor Fluid Table */GameRegistry.addRecipe(new ItemStack(fluidTable),new Object[] { "BDB", "ACA","BAB", 'A',alumPlate, 'B' ,Item.getItemFromBlock(Blocks.iron_block),'C' , Item.getItemFromBlock(Blocks.crafting_table),'D',Items.bucket});
    	SmartCont = new SmartContainer(0,"Smart Container" , Material.iron , 4f);
    	tank = new TankBlock("Tank", Material.glass, 2f, false, 0, "glass8");
    }
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
	{
    	networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Channel);
    	networkWrapper.registerMessage(CommandsHandler.class, ClientToServerMessage.class, 0, Side.SERVER);
    	networkWrapper.registerMessage(ServerCommandsHandler.class, ServerToClientMessage.class, 0, Side.CLIENT);
    	packetCommands = new PacketsCommandsRegistry();
    	packetCommands.registerMessageCommand(SoulTPCommand.class);
    	packetCommands.registerMessageCommand(WrenchCommand.class);
    	packetCommands.registerMessageCommand(KeyPressedUpdateCommand.class);
    	packetCommands.registerMessageCommand(SmartContainerSyncCommand.class);
    	proxy.RegisterKeys();
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
    	FMLCommonHandler.instance().bus().register(new ConfigHandler());
    	GameRegistry.registerWorldGenerator(new AdamOreGenerator(), 1);
    	TileEntity.addMapping(TileEntityAdamantiteContainer.class, "Adamantite Container");
    	TileEntity.addMapping(TileEntityConstructorTable.class, "Constructor Table");
    	TileEntity.addMapping(TileEntityVoidStorage.class, "Void Storage Unit");
    	TileEntity.addMapping(TileEntityVoidPickStorage.class, "Void Pick Storage Unit");
    	TileEntity.addMapping(TileEntityPickStorage.class, "Pick Storage Unit");
    	TileEntity.addMapping(TileEntityHologramStand.class, "Hologram Stand");
    	TileEntity.addMapping(TileEntityHoloOwnerControl.class, "Hologram Stand Cont1");
    	TileEntity.addMapping(TileEntityHoloArmorControl.class, "Hologram Stand Cont2");
    	TileEntity.addMapping(TileEntityHoloColorControl.class, "Hologram Stand Cont3");
    	TileEntity.addMapping(TileEntityHoloRedstoneControl.class, "Hologram Stand Cont4");
    	TileEntity.addMapping(TileEntityHoloMultiControl.class, "Hologram Stand Cont5");
    	TileEntity.addMapping(TileEntityHoloEquipControl.class, "Hologram Stand Cont6");
    	TileEntity.addMapping(TileEntityHoloUnequipControl.class, "Hologram Stand Cont7");
    	TileEntity.addMapping(TileEntityHoloTeleportControl.class, "Hologram Stand Cont8");
    	TileEntity.addMapping(TileEntityBasicCable.class, "Basic Quanta Cable");
    	TileEntity.addMapping(TileEntityItemsCable.class, "Items Quanta Cable");
    	TileEntity.addMapping(TileEntityRoundRobinCable.class, "Round Robin Quanta Cable");
    	TileEntity.addMapping(TileEntityExtractCable.class, "Extract Quanta Cable");
    	TileEntity.addMapping(TileEntityItemsInvCable.class, "Inventory Quanta Cable");
    	TileEntity.addMapping(TileEntityRFCable.class, "RF Quanta Cable");
    	TileEntity.addMapping(TileEntityDirectionalStorage.class, "Directional Storage Unit");
    	TileEntity.addMapping(TileEntityConstructorFluidModifier.class, "Constructor Fluid Modifier");
    	TileEntity.addMapping(TileEntitySmartContainer.class, "Smart Container");
    	TileEntity.addMapping(TileEntityTank.class, "Tank");
    	instance = this;
    	proxy.registerRenderers();
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

	}
    
    
    public void postInitThaumcraft(){
    	soulShard = new createItem("Soul Shard");
    	Item thShard = thaumcraft.api.ItemApi.getItem("itemShard",0).getItem();
    	nRecipes.addRecipeStacks(new ItemStack(soulShard,1), new Knowledges[]{Knowledges.VIS,Knowledges.SOUL_CONTROL}, new String[]{" B ","BAB"," B "}, new Object [] {"A" , new ItemStack(thShard) , "B" ,new ItemStack(SoulGem)});
    	knowVis = new ItemKnowledge("Vis Manipulating", Knowledges.VIS, nRecipes.getMap().keySet().size()-1);
    	Reflection = new ReflexioAspect();
		Refraction = new Aspect("Refractionis",0xb5b5cd, new Aspect[]{Reflection,Aspect.ENTROPY},new ResourceLocation(modMain.modid.toLowerCase() ,"textures/aspects/Refraction.png"),771);
		Division = new Aspect("Dissensio",0xb5b5cd, new Aspect[]{Reflection,Refraction},new ResourceLocation(modMain.modid.toLowerCase() ,"textures/aspects/Division.png"),1);
		
    }
    
    public void postInitTE4(){
    	TELoaded = true;
    	thermalexpansion.util.crafting.PulverizerManager.addRecipe(10000,new ItemStack(adamOre), new ItemStack(adamDust,2));
    	CableRF = new RFCableBlock("RF Cable", Material.iron, 2f, false, 0, "Cable2");
    	/* RF Cables */fRecipes.addRecipe(new ItemStack[]{new ItemStack(cable), new ItemStack(lightGem)}, new ItemStack(CableRF) , 100, new Knowledges[]{Knowledges.LIGHT_SORCERY , Knowledges.SMART_STORAGE}, thermalfoundation.fluid.TFFluids.fluidRedstone);
    }
    
    public void postInitIC2(){
    	ic2.api.recipe.Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(adamOre)), null, new ItemStack(adamDust,2));

    }
    

    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
	{
    	
    	TELoaded = false;
        if (Loader.isModLoaded("NotEnoughItems") && FMLCommonHandler.instance().getSide()==Side.CLIENT){
        	codechicken.nei.api.API.registerRecipeHandler(new ConstructorShapedRecipe());
        	codechicken.nei.api.API.registerUsageHandler(new ConstructorShapedRecipe());
        	codechicken.nei.api.API.registerRecipeHandler(new ConstructorShapelessRecipe());
        	codechicken.nei.api.API.registerUsageHandler(new ConstructorShapelessRecipe());
        	codechicken.nei.api.API.registerUsageHandler(new ConstructorLiquidRecipe());
        	codechicken.nei.api.API.registerRecipeHandler(new ConstructorLiquidRecipe());
        	log("Succesfully used NEI Intergretion");
        }
        if (Loader.isModLoaded("Thaumcraft")){
        	postInitThaumcraft();
        	log("Succesfully used Thaumcraft Intergretion");
        }
        
        if (Loader.isModLoaded("IC2")|| Loader.isModLoaded("IndustrialCraft 2")){
        	postInitIC2();
        	log("Succesfully used IC2 Intergretion");
        }
        if (Loader.isModLoaded("ThermalExpansion")|| Loader.isModLoaded("Thermal Expansion")){
        	postInitTE4();
        	proxy.registerTERenderers();
        	log("Succesfully used Thermal Expansion Intergretion");
        }
    	
	}
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    	//event.registerServerCommand(new SampleCommand());
    	event.registerServerCommand(new SoulCommand());
    	event.registerServerCommand(new TextPlateCommand());
    }
    
    public static void printSideObject(Object obj , World world){
    	if (!world.isRemote){
    		System.out.println("Server: "+obj);
    	}else{
    		System.out.println("Client: "+obj);
    	}
    }
    
    public static void log(String logString){
    	FMLLog.log("QuantaCraft",Level.INFO, logString);
    }
    
    public static void logObject(Object logString){
    	FMLLog.log("QuantaCraft",Level.INFO, logString.toString());
    }
}
