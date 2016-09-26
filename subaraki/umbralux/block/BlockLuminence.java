package subaraki.umbralux.block;

import net.minecraft.block.BlockAir;
import subaraki.umbralux.mod.AddonUmbraLux;

public class BlockLuminence extends BlockAir{

	public BlockLuminence() {
		setLightLevel(2f);
		setUnlocalizedName(AddonUmbraLux.MODID+".airLuminence");
		setRegistryName("airLuminence");
	}
	
}
