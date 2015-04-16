package QuantaCraft.Blocks.Blocks;



import java.util.Random;




import QuantaCraft.Blocks.TileEntities.TileEntityVoidStorage;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import QuantaCraft.main.modMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class VoidStorage extends BlockContainer{
	public TileEntityVoidStorage hi;
    public VoidStorage(int par1, String name, Material blockMaterial,float hardness) {
    	super(blockMaterial);
		setHarvestLevel("pickaxe", 2);
            setHardness(hardness);
            setResistance(5.0F);
            setBlockName(name);
            setCreativeTab(modMain.tab);
            GameRegistry.registerBlock(this, name);
            LanguageRegistry.addName(this, name);
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
    {
    	if (player.getHeldItem()!=null && player.getHeldItem().getItem()==modMain.voidGem){
    		hi = (TileEntityVoidStorage) world.getTileEntity(x, y, z);
    		hi.storageMax +=5000;
    		player.setCurrentItemOrArmor(0, null);
    	}
    	if (!world.isRemote) {
    		hi = (TileEntityVoidStorage) world.getTileEntity(x, y, z);
    		player.openGui(modMain.instance, 3, world, x, y, z);
    		return true;
    	} else {
    		return false;
    	}

    
    }
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
    }
    @Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
    
        this.setDefaultDirection(par1World, x, y, z,par5EntityLivingBase);
}
    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4,EntityLivingBase par5EntityLivingBase)
    {
        if (!par1World.isRemote)
        {
            byte b0;
            EntityLivingBase player =  par5EntityLivingBase;

            if (Math.abs(player.posX-par2)>Math.abs(player.posZ-par4))
            {
            	if (player.posX-par2<0) {
                b0 = 4;
            } else {
            	b0 = 5;
            }
            } else {
            	if (player.posZ-par4<0) {
                    b0 = 2;
            	}   else {
                b0 = 3;
            }
            }
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }



    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */

	@Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z){
            Random rand = new Random();

            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (!(tileEntity instanceof IInventory)) {
                    return;
            }
            IInventory inventory = (IInventory) tileEntity;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    ItemStack item = inventory.getStackInSlot(i);

                    if (item != null && item.stackSize > 0) {
                            float rx = rand.nextFloat() * 0.8F + 0.1F;
                            float ry = rand.nextFloat() * 0.8F + 0.1F;
                            float rz = rand.nextFloat() * 0.8F + 0.1F;

                            EntityItem entityItem = new EntityItem(world,
                                            x + rx, y + ry, z + rz,
                                            new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                            if (item.hasTagCompound()) {
                                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                            }

                            float factor = 0.05F;
                            entityItem.motionX = rand.nextGaussian() * factor;
                            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                            entityItem.motionZ = rand.nextGaussian() * factor;
                            world.spawnEntityInWorld(entityItem);
                            item.stackSize = 0;
                    }
            }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
    	this.hi=new TileEntityVoidStorage();    
    	return hi;
    }
    @SideOnly(Side.CLIENT)
    public static IIcon topIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon bottomIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon sideIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon frontIcon;
    

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister icon) {
	   topIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" + this.getUnlocalizedName().substring(5) + "_top");
	   bottomIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" + this.getUnlocalizedName().substring(5) + "_bottom");
	   sideIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" + this.getUnlocalizedName().substring(5) + "_side");
	   frontIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" + this.getUnlocalizedName().substring(5) + "_front");
   }




        /**
         * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
         */
        @Override
        @SideOnly(Side.CLIENT)
        public IIcon getIcon(int par1, int par2)
        {
            return par1 == 1 ? this.topIcon : (par1 == 0 ? this.bottomIcon : (par1 != par2 ? this.sideIcon : this.frontIcon));
        }

}
