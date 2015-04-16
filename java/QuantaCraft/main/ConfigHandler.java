package QuantaCraft.main;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;



public class ConfigHandler {
	
	public static Configuration configuration;
	public static boolean testValue = false;
	public static void init(File configFile)
	{
		// Create the configuration object from the given configuration file
		if (configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	private static void loadConfiguration()
	{
		testValue = configuration.getBoolean("Use Experimental Settings", Configuration.CATEGORY_GENERAL, false, "Experimental Block , Items and recipes , may crash your game and may have alot of bugs");
		if (configuration.hasChanged())
		{
			configuration.save();
		}
	}
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(modMain.modid))
		{
			loadConfiguration();
		}
	}
}