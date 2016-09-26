package subaraki.umbralux.entity.minion;

import java.util.Comparator;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
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
