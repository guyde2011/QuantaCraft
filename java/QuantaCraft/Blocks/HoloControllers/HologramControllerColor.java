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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
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

public class HologramControllerColor extends BlockContainer implements IPickable{
	
	public TileEntityHoloColorControl hi;
	public EntityPlayer p;
	
	public HologramControllerColor(int id, String name, Material blockMaterial, float hardness) {
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
    	hi = (TileEntityHoloColorControl) world.getTileEntity(x, y, z);
    	if (player.getHeldItem()!=null && player.getHeldItem().getItem()==modMain.textPlate){
    		ItemStack stack = player.getHeldItem();
    		String name = stack.getDisplayName();
    		if (name.length()==8){
    			if (name.substring(0, 2).equals("0x")){
    				if (isHex(name.substring(2))){
    					hi.color = name;
    					hi.markDirty();
    					System.out.println(name);
    				} else {
    					
    				}
    				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Hex Chars"));
    			} else {
    				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Color Format"));
    			}
    		} else {
    			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Color Format / Length"));
    		}
    	}

    	if (player.getHeldItem()!=null && player.getHeldItem().getItem() == modMain.darkGem){
    		modMain.sendCommand(new WrenchCommand(player , x , y , z));

    	}
    	
    	return true;


    
    }
    private boolean isHex(String value) {
        boolean ret;
        try {
            int t = Integer.parseInt(value, 16);
            ret = true;
        } catch (NumberFormatException e) {
            ret = false;
        }
        return (ret);
    } 
    
	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" +"ControllerColor");
	   }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
    	this.hi=new TileEntityHoloColorControl();    
    	return hi;
    }
    
	@Override
	public EntityItem getEntityItem(int x, int y, int z, World world) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		float rx = rand.nextFloat() * 0.8F + 0.1F;
        float ry = rand.nextFloat() * 0.8F + 0.1F;
        float rz = rand.nextFloat() * 0.8F + 0.1F;
        ItemStack item = new ItemStack(modMain.holoContColor);
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
