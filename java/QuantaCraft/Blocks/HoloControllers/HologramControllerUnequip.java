package QuantaCraft.Blocks.HoloControllers;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import QuantaCraft.Blocks.TileEntities.TileEntityConstructorTable;
import QuantaCraft.Blocks.TileEntities.TileEntityDirectionalStorage;
import QuantaCraft.Blocks.TileEntities.TileEntityHologramStand;
import QuantaCraft.Blocks.TileEntities.TileEntityPickStorage;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.IHologramController;
import QuantaCraft.main.Interfaces.IPickable;
import QuantaCraft.packets.WrenchCommand;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HologramControllerUnequip extends BlockContainer implements IPickable{
	
	public TileEntityHoloUnequipControl hi;
	public EntityPlayer p;
	public HologramControllerUnequip(int id, String name, Material blockMaterial, float hardness) {
		super(blockMaterial);
		setHarvestLevel("pickaxe", 1);
		setHardness(hardness);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		LanguageRegistry.addName(this, name);
		this.setCreativeTab(modMain.tab);
		this.setResistance(3000);
		this.setBlockUnbreakable();
	} 

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
    {
    	if (player.getHeldItem()!=null && player.getHeldItem().getItem() == modMain.darkGem){
    		modMain.sendCommand(new WrenchCommand(player , x , y , z));;

    	}

    	return true;


    
    }
	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" +"ControllerUnequip");
	   }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
    	this.hi=new TileEntityHoloUnequipControl();    
    	return hi;
    }

	@Override
	public EntityItem getEntityItem(int x, int y, int z, World world) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		float rx = rand.nextFloat() * 0.8F + 0.1F;
        float ry = rand.nextFloat() * 0.8F + 0.1F;
        float rz = rand.nextFloat() * 0.8F + 0.1F;
        ItemStack item = new ItemStack(modMain.holoContUnequip);
        EntityItem entityItem = new EntityItem(world,
                        x + rx, y + ry, z + rz,
                        new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
        float factor = 0.05F;
        entityItem.motionX = rand.nextGaussian() * factor;
        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
        entityItem.motionZ = rand.nextGaussian() * factor;
        return entityItem;
	}
	
}
