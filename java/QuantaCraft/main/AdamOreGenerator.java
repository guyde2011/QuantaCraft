package QuantaCraft.main;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class AdamOreGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId){
        case -1:
            generateNether(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 0:
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 1:
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
            break;
        }
	}

	private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {}

	private void generateLightGem(World world, Random rand, int chunkX, int chunkZ){
		for(int k = 0; k <20 ; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(40)+8;
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(modMain.lightOre, 6)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
	}
	
	private void generateDarkGem(World world, Random rand, int chunkX, int chunkZ){
		 for(int k = 0; k <18 ; k++){
	        	int firstBlockXCoord = chunkX + rand.nextInt(16);
	        	int firstBlockYCoord = rand.nextInt(20)+8;
	        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
	        	
	        	(new WorldGenMinable(modMain.darkOre, 7)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
	        }
	}
	private void generateAluminum(World world, Random rand, int chunkX, int chunkZ){
		 for(int k = 0; k <10 ; k++){
	        	int firstBlockXCoord = chunkX + rand.nextInt(16);
	        	int firstBlockYCoord = rand.nextInt(40)+8;
	        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
	        	
	        	(new WorldGenMinable(modMain.alumOre, 4)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
	        }
	}
	
	private void generateAdamantite(World world, Random rand, int chunkX, int chunkZ){
		for(int k = 0; k <rand.nextInt(1) ; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(10)+8;
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	(new WorldGenMinable(modMain.adamOre, 4 ,Blocks.stone)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
	}
	
	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        generateAdamantite(world, rand, chunkX, chunkZ);
        generateLightGem(world, rand, chunkX, chunkZ);
        generateDarkGem(world, rand, chunkX, chunkZ);
	}

	private void generateNether(World world, Random rand, int chunkX, int chunkZ) {}
}