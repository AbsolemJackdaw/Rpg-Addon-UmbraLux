package subaraki.umbralux.entity;

import java.util.ArrayList;

import lib.entity.EntityRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import subaraki.umbralux.entity.minion.EntityMinionSkeleton;
import subaraki.umbralux.entity.minion.EntityMinionZombie;
import subaraki.umbralux.mod.AddonUmbraLux;

public class EntityHandler {

	public EntityHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		init();
	}

	ArrayList<EntityEntryBuilder> entities = new ArrayList<EntityEntryBuilder>();

	private void init()
	{
		EntityRegistry er = new EntityRegistry();

		EntityEntryBuilder necro_cloud = er.registerEntity(EntityNecroCloud.class, EntityNecroCloud::new, AddonUmbraLux.MODID, "necro_cloud", 0, 256, 4, true);
		EntityEntryBuilder zombie_minion = er.registerEntity(EntityMinionZombie.class, EntityMinionZombie::new, AddonUmbraLux.MODID, "zombie_minion", 1, 256, 4, true);
		EntityEntryBuilder skeleton_minion = er.registerEntity(EntityMinionSkeleton.class, EntityMinionSkeleton::new, AddonUmbraLux.MODID, "necro_cloud", 2, 256, 4, true);
		EntityEntryBuilder sword_special = er.registerEntity(EntitySwordSpecial.class, EntitySwordSpecial::new, AddonUmbraLux.MODID, "sword_special", 3, 256, 4, true);

		entities.add(necro_cloud);
		entities.add(zombie_minion);
		entities.add(skeleton_minion);
		entities.add(sword_special);
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> e){

		for(EntityEntryBuilder eeb : entities)
			e.getRegistry().register(eeb.build());
	}
}

