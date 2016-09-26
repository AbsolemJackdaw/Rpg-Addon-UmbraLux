package subaraki.umbralux.handler.proxy;

import net.minecraft.client.model.ModelBiped;
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
		EntityRegistry.registerModEntity(EntityNecroCloud.class, "necro_cloud", 0, AddonUmbraLux.MODID, 64, 250, true);
		
		EntityRegistry.registerModEntity(EntityMinionZombie.class, "zombie_minion", 1, AddonUmbraLux.MODID, 64, 250, false);
		EntityRegistry.registerModEntity(EntityMinionSkeleton.class, "skeleton_minion", 2, AddonUmbraLux.MODID, 64, 250, false);
	
		EntityRegistry.registerModEntity(EntitySwordSpecial.class, "sword_special", 3, AddonUmbraLux.MODID, 64, 250, false);

	}
}
