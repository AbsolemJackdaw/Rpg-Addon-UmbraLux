package subaraki.umbralux.handler.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import subaraki.umbralux.entity.minion.MinionRegistry;

public class PlayerTracker {

	public PlayerTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void playerLoggedOut(PlayerLoggedOutEvent event){
		if(event.player != null){
			MinionRegistry.removePlayerFromList(event.player);
		}
	}
}
