package subaraki.umbralux.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UmbraLuxBlocks {

	public static Block airLuminence;
	
	public static void loadBlocks(){
		airLuminence = new BlockLuminence();
	}
	
	private static void register(){
		GameRegistry.register(airLuminence);
	}
}
