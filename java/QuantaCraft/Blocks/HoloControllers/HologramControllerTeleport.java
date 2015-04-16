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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
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

public class HologramControllerTeleport extends BlockContainer implements IPickable{
	
	public TileEntityHoloTeleportControl hi;
	public EntityPlayer p;
	public HologramControllerTeleport(int id, String name, Material blockMaterial, float hardness) {
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


    private boolean isNumeric(String value) {
        boolean ret;
        try {
            double t = Double.parseDouble(value);
            ret = true;
        } catch (NumberFormatException e) {
            ret = false;
        }
        return (ret);
    } 
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
    {
    	hi = (TileEntityHoloTeleportControl) world.getTileEntity(x, y, z);
    	if (player.getHeldItem()!=null && player.getHeldItem().getItem() == modMain.darkGem){
    		modMain.sendCommand(new WrenchCommand(player , x , y , z));;

    	}
    	if (player.getHeldItem()!=null && player.getHeldItem().getItem()==modMain.textPlate){
    		ItemStack stack = player.getHeldItem();
    		String name = stack.getDisplayName();
    		if (name.contains(" ") && isNumeric(name.substring(0,name.indexOf(" ")))){
    			String name1 = name.substring(name.indexOf(" ")+1);
    			System.out.println(name1);
    			if (name1.contains(" ") && isNumeric(name1.substring(0,name1.indexOf(" ")))){
    				String name2 = name1.substring(name1.indexOf(" ")+1);
    				if (isNumeric(name2)){
    					hi.xCord = Double.parseDouble(name.substring(0,name.indexOf(" ")));
    					hi.yCord = Double.parseDouble(name1.substring(0,name1.indexOf(" ")));
    					hi.zCord = Double.parseDouble(name2);
    					hi.canTP = true;
    					if (!player.worldObj.isRemote){
    						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Succesfully put in coords in the controller"));
    					}
    				} else {
    					if (!player.worldObj.isRemote){
    						player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Z-Coord"));
    					}
    				}
    			} else {
					if (!player.worldObj.isRemote){
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Y-Coord"));
					}
    			}
    		} else {
				if (!player.worldObj.isRemote){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid X-Coord"));
				}
    		}
    	}
    	
    	
    	return true;
    }
    
	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" +"ControllerTeleport");
	   }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
    	this.hi=new TileEntityHoloTeleportControl();    
    	return hi;
    }
	@Override
	public EntityItem getEntityItem(int x, int y, int z, World world) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		float rx = rand.nextFloat() * 0.8F + 0.1F;
        float ry = rand.nextFloat() * 0.8F + 0.1F;
        float rz = rand.nextFloat() * 0.8F + 0.1F;
        ItemStack item = new ItemStack(modMain.holoContTeleport);
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
