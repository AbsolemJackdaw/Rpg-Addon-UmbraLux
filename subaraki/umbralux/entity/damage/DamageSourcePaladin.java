package subaraki.umbralux.entity.damage;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import subaraki.umbralux.entity.EntitySwordSpecial;

public class DamageSourcePaladin {
	
	public static final DamageSource paladinDamage = new DamageSource("holy").setDifficultyScaled().setFireDamage();

	public static DamageSource causeHolySwordDamage(EntitySwordSpecial sword){
		return new EntityDamageSource("holy", sword);
	}
}
