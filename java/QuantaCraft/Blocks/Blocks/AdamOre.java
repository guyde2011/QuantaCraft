package QuantaCraft.Blocks.Blocks;

import java.util.Random;

import QuantaCraft.main.modMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.TileFluidHandler; 
public class AdamOre extends Block{
	
	public AdamOre(int id, String name, Material blockMaterial, float hardness) {
		super(blockMaterial);
		setHarvestLevel("pickaxe", 3);
		setHardness(hardness);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		LanguageRegistry.addName(this, name);
		this.setCreativeTab(modMain.tab);
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

	            if (Math.abs(player.posX-par2)>Math.max(Math.abs(player.posZ-par4),Math.abs(player.posY-par3)))
	            {
	            	if (player.posX-par2<0) {
	                b0 = 4;
	            } else {
	            	b0 = 5;
	            }
	            } else {
	            	if (Math.abs(player.posZ-par4)>Math.abs(player.posY-par3)){
	            	if (player.posZ-par4<0) {
	                    b0 = 2;
	            	}   else {
	                b0 = 3;
	            }} else{
	            	if (player.posY-par4<0) {
	                    b0 = 0;
	            	}   else {
	                b0 = 1;
	            }}
	            }
	            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
	        }
	    }

	    

	    /**
	     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	     */

		@Override
	    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {

	            super.breakBlock(world, x, y, z, par5, par6);
	    }




	    

	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid + ":" + this.getUnlocalizedName().substring(5));
	   }
}
