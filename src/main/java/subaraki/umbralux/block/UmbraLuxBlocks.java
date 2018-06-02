package subaraki.umbralux.block;

import lib.block.BlockRegistry;
import lib.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UmbraLuxBlocks {

	public static Block airLuminence;

	public static void loadBlocks(){
		airLuminence = new BlockLuminence();

		register();
	}

	private static void register(){
		BlockRegistry.registerBlock(airLuminence);
	}
}
