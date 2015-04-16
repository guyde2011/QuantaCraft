package QuantaCraft.main.Commands;

import java.util.ArrayList;
import java.util.List;

import QuantaCraft.Items.SpiritBlade;
import QuantaCraft.main.SoulHandler;
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

public class SoulCommand implements ICommand
{
  private List aliases;
  public SoulCommand()
  {
    this.aliases = new ArrayList();
    this.aliases.add("soul");
    this.aliases.add("souls");
  }

  @Override
  public String getCommandName()
  {
    return "setSouls";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "setSouls <amount>";
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
      icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid arguments"));
      return;
    }


    EntityPlayer player = ((EntityPlayer)icommandsender);
    if (player.getHeldItem()!=null && player.getHeldItem().getItem() instanceof ISoulItem){
    	player.setCurrentItemOrArmor(0, SoulHandler.setSouls(player.getHeldItem(),Integer.valueOf(astring[0])));
    } 
   
   
  }

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
  {
	  EntityPlayer player = ((EntityPlayer)icommandsender);
    return icommandsender.canCommandSenderUseCommand(4, this.getCommandName());
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