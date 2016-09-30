package subaraki.umbralux.entity.minion;

import java.util.Comparator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MinionEntitySelectorAI implements Comparator<Entity> {

	private EntityPlayer owner;
    
	public MinionEntitySelectorAI(EntityPlayer owner) {
		this.owner = owner;
	}

	@Override
	public int compare(Entity o1, Entity o2) {
		
		return 0;
	}
}
