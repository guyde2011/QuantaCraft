package QuantaCraft.Blocks.Blocks;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;







import QuantaCraft.Blocks.TileEntities.TileEntityPickStorage;
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
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class PickStorage extends BlockContainer{
	public ItemStack placedBy;
	public TileEntityPickStorage hi;
    public PickStorage(int par1, String name, Material blockMaterial,float hardness) {
    	super(blockMaterial);
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
    	
    	if (!world.isRemote) {
    		hi = (TileEntityPickStorage) world.getTileEntity(x, y, z);
    		player.openGui(modMain.instance, 5, world, x, y, z);
    		return true;
    	} else {
    		return false;
    	}
    	

    
    }
    
    public ArrayList<ItemStack> getDrops(World world , int x , int y , int z , int meta , int fortune){	
    	ArrayList<ItemStack> stack1 = new ArrayList<ItemStack>();
    	return stack1;
    }
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
    }
    @Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
    	this.placedBy = par6ItemStack;
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
           this.saveItems(world, x, y, z, world.getBlock(x, y, z));
            super.breakBlock(world, x, y, z, par5, par6);
    }

    private void saveItems(World world, int x, int y, int z , Block block){
            Random rand = new Random();

            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (!(tileEntity instanceof IInventory)) {
                    return;
            }
            
            IInventory inv = (IInventory) tileEntity;
            
            ItemStack item = new ItemStack(modMain.picksStorage);
            
            NBTTagCompound Items = new NBTTagCompound();
            
            NBTTagList items = new NBTTagList();
            
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
            	if (inv.getStackInSlot(i) != null) {
            		NBTTagCompound itemA = new NBTTagCompound();
            		itemA.setByte("Slot", (byte) i);
            		inv.getStackInSlot(i).writeToNBT(itemA);
            		items.appendTag(itemA);
            	}
            }
            Items.setTag("Inventory", items);
            item.stackTagCompound = Items;
           
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

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
    	this.hi=new TileEntityPickStorage();    
    	if (this.placedBy!=null){
    		hi.PlacedBy = this.placedBy;
    	}
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
