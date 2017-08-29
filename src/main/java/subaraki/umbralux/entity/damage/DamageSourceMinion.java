package subaraki.umbralux.entity.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import subaraki.umbralux.entity.minion.IMinion;

public class DamageSourceMinion {
	
	public static final DamageSource minionDamage = new DamageSource("minion").setDamageBypassesArmor();
	public static final DamageSource ageDamage = new DamageSource("minionAge").setDamageIsAbsolute();

	public static DamageSource causeMinionDamage(IMinion minion){
		return new EntityDamageSource("minion", (EntityLivingBase)minion);
	}
	
	public static DamageSource causeMinionAgeDamage(IMinion minion){
		return new EntityDamageSource("minionAge", (EntityLivingBase)minion);
	}
}
