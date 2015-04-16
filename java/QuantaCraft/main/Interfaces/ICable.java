package QuantaCraft.main.Interfaces;

import net.minecraft.tileentity.TileEntity;

public interface ICable {
	public String getTexture();
	public boolean CanConnect(TileEntity ent);
}
