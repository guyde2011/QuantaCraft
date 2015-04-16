package QuantaCraft.packets;

import io.netty.buffer.ByteBuf;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public abstract class MessageCommand {
	
	public abstract MessageCommand read(ByteBuf buf);
	public abstract void write(ByteBuf buf);
	public List<Object> information = new ArrayList<Object>();
	public abstract void runCommand(MessageContext ctx);
}
