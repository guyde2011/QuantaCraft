package QuantaCraft.main.Commands;

import java.util.ArrayList;
import java.util.List;

import  java.util.Arrays;



import QuantaCraft.Items.SpiritBlade;
import QuantaCraft.main.SoulHandler;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.ISoulItem;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

public class TextPlateCommand implements ICommand
{
  private List aliases;
  public TextPlateCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("textName");
    this.aliases.add("plateName");
  }

  @Override
  public String getCommandName()
  {
    return "setName";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "setName <name>";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender icommandsender, String[] astring)
  {
    if(astring.length == 0)
    {
      icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments"));
      return;
    }
    EntityPlayer player = ((EntityPlayer)icommandsender);
    if (player.getHeldItem()!=null && player.getHeldItem().getItem()==modMain.textPlate){
    	String s = astring[0];
    	while (astring.length>1){
    		astring = (String[]) Arrays.copyOfRange(astring, 1 , astring.length);
    		s += " " + astring[0];
    	}
    	player.setCurrentItemOrArmor(0, player.getHeldItem().setStackDisplayName(s));
    } 
   
   
  }

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions(ICommandSender icommandsender,
      String[] astring)
  {
    return null;
  }

  @Override
  public boolean isUsernameIndex(String[] astring, int i)
  {
    return false;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
}