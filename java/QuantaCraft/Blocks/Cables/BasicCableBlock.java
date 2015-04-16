package QuantaCraft.Blocks.Cables;

import QuantaCraft.Blocks.TileEntities.TileEntityBasicCable;
import QuantaCraft.main.modMain;
import QuantaCraft.main.Interfaces.ICable;
import QuantaCraft.main.Interfaces.ICableItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BasicCableBlock extends BlockContainer implements ICable{


	
	
	private int getCurMode(boolean s1, boolean s2,boolean s3,boolean s4,boolean s){
		int r = 0 ;
		if (s) r=16;
		else{
		if (s1) ++r;
		if (s2) r=r+2;
		if (s3) r=r+4;
		if (s4) r=r+8;		
		}
		return r;
	}
	public BasicCableBlock( String blockName, Material blockMaterial, float hardness,boolean opaque,int lo,String textureName){

        super(blockMaterial);

        this.setCreativeTab(modMain.tab);
        this.setBlockName(blockName);
        this.setHardness(hardness);
        this.setStepSound(Block.soundTypeMetal);
        LanguageRegistry.addName(this, blockName);
        GameRegistry.registerBlock(this, blockName);


		}

	   @Override
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister icon) {
		   this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" +"Cable");
	   }
	
	
	   //It's not an opaque cube, so you need this.
	   @Override
	   public boolean isOpaqueCube() {
	           return false;
	   }
	  
	   //It's not a normal block, so you need this too.
	   public boolean renderAsNormalBlock() {
	           return false;
	   }

    
  
	   @Override
	   public int getRenderType() {
	           return 162;
	   }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityItemsCable();
	}
	@Override
	public String getTexture() {
		// TODO Auto-generated method stub
		return "Cable";
	}
	@Override
	public boolean CanConnect(TileEntity ent) {
		
		return (ent instanceof ICableItems || ent instanceof IInventory);
	}

	}

