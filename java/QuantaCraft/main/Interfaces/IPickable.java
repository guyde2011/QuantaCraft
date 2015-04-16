package QuantaCraft.main.Interfaces;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;

public interface IPickable {
	public EntityItem getEntityItem(int x , int y , int z , World world);
}
