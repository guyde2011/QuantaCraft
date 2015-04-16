package QuantaCraft.main.Commands;

import java.util.ArrayList;
import java.util.List;

import QuantaCraft.Items.SpiritBlade;
import QuantaCraft.main.modMain;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

public class SampleCommand implements ICommand
{
  private List aliases;
  public SampleCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("sample");
    this.aliases.add("sam");
  }

  @Override
  public String getCommandName()
  {
    return "spiritBlade";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "spiritBlade <tier>";
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
    if (player.getHeldItem()==null || (player.getHeldItem().getItem()==modMain.spiritBlade)){
    	player.setCurrentItemOrArmor(0, SpiritBlade.setTier(new ItemStack(modMain.spiritBlade),Integer.valueOf(astring[0])));
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