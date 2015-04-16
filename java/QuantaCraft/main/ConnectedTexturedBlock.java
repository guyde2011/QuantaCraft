package QuantaCraft.main;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ConnectedTexturedBlock extends Block{




	int TexNum;
	public IIcon[] textures = new IIcon[17];
	public int getTexture(boolean s1, boolean s2,boolean s3,boolean s4,boolean s){
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
	String TexName;
	public ConnectedTexturedBlock( String blockName, Material blockMaterial, float hardness,boolean opaque,int lo,String textureName){

        super(blockMaterial);

        this.setCreativeTab(modMain.tab);
        this.setBlockName(blockName);
        this.setHardness(hardness);
        this.setStepSound(Block.soundTypeMetal);
        LanguageRegistry.addName(this, blockName);
        GameRegistry.registerBlock(this, blockName);
        this.TexName=textureName;



		}

    

    
    @SideOnly(Side.CLIENT)
    public static IIcon topIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon bottomIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon xPosIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon xNegIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon zPosIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon zNegIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
    	this.blockIcon = icon.registerIcon(modMain.modid.toLowerCase() + ":" + TexName + "1");
        for (int i = 0; i<17;i++) {

        	textures[i]=icon.registerIcon(modMain.modid.toLowerCase() + ":" + TexName + String.valueOf(i+1));
        }
      }

    


    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int blockX, int blockY, int blockZ, int side)
    {
		Block xPos=world.getBlock(blockX+1,blockY,blockZ);
		Block xNeg=world.getBlock(blockX-1,blockY,blockZ);
		Block yNeg=world.getBlock(blockX,blockY-1,blockZ);
		Block yPos=world.getBlock(blockX,blockY+1,blockZ);
		Block zNeg=world.getBlock(blockX,blockY,blockZ-1);
		Block zPos=world.getBlock(blockX,blockY,blockZ+1);
		boolean negX = (xNeg == this);
		boolean posX = (xPos == this);
		boolean negY = (yNeg == this);
		boolean posY = (yPos == this);
		boolean negZ = (zNeg == this);
		boolean posZ = (zPos == this);
		
		if (side ==5) TexNum = getTexture(posY, posZ, negY ,negZ, posX);	
		if (side ==4) TexNum = getTexture(posY, posZ, negY ,negZ, negX);
		if (side ==3) TexNum = getTexture(posY, posX, negY ,negX, posZ);
		if (side==2)  TexNum = getTexture(posY, posX, negY ,negX, negZ);
    	if (side==1)  TexNum = getTexture(negZ, posX, posZ ,negX, posY);
    	if (side==0)  TexNum = getTexture(negZ, posX, posZ ,negX, negY);
		return textures[TexNum];
       
}

	}

