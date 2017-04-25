package subaraki.umbralux.handler.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import subaraki.umbralux.entity.EntityNecroCloud;
import subaraki.umbralux.entity.EntitySwordSpecial;
import subaraki.umbralux.entity.minion.EntityMinionSkeleton;
import subaraki.umbralux.entity.minion.EntityMinionZombie;
import subaraki.umbralux.mod.AddonUmbraLux;

public class ServerProxy {

	public ModelBiped getArmorModel(String id){return null;}
	public void registerRenders(){};
	public void registerClientEvents(){}
	public void registerColors() {};
	
	public void registerEntities(){
		EntityRegistry.registerModEntity(new ResourceLocation("umbralux","necro_cloud"), EntityNecroCloud.class, "necro_cloud", 0, AddonUmbraLux.MODID, 64, 250, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("umbralux","zombie_minion"),EntityMinionZombie.class, "zombie_minion", 1, AddonUmbraLux.MODID, 64, 250, false);
		EntityRegistry.registerModEntity(new ResourceLocation("umbralux","skeleton_minion"),EntityMinionSkeleton.class, "skeleton_minion", 2, AddonUmbraLux.MODID, 64, 250, false);
	
		EntityRegistry.registerModEntity(new ResourceLocation("umbralux","sword_special"),EntitySwordSpecial.class, "sword_special", 3, AddonUmbraLux.MODID, 64, 250, false);

	}
}
