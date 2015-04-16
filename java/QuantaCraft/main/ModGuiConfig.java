package QuantaCraft.main;


import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
public class ModGuiConfig extends GuiConfig
{
public ModGuiConfig(GuiScreen guiScreen)
{
super(guiScreen,
		new ConfigElement(ConfigHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
		modMain.modid,
			false,
			false,
			GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
	}
}

