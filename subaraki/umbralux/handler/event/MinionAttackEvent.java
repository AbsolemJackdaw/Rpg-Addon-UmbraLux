package subaraki.umbralux.handler.event;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.umbralux.entity.minion.EntityMinionSkeleton;
import subaraki.umbralux.entity.minion.EntityMinionZombie;

public class MinionAttackEvent {

	public MinionAttackEvent() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event){

		if(event.getSource().getEntity() instanceof EntityMinionZombie){
			if(event.getEntityLiving() instanceof EntityZombie){
				EntityZombie zombie = (EntityZombie)event.getEntityLiving();
				EntityMinionZombie minion = (EntityMinionZombie)event.getSource().getEntity();

				//if a zombie is attacked by a minion, and has half of his life left, transform it.
				if(zombie.getHealth() <= zombie.getMaxHealth()/2){
					if (!zombie.worldObj.isRemote){
						EntityMinionZombie emz = new EntityMinionZombie(zombie.worldObj);
						emz.setOwnerId(minion.getOwnerId());
						emz.setPositionAndRotation(zombie.posX, zombie.posY, zombie.posZ, zombie.getRotationYawHead(), zombie.rotationPitch);
						zombie.worldObj.spawnEntityInWorld(emz);
					}
					zombie.setDead();
				}
			}else if(event.getEntityLiving() instanceof EntitySkeleton){
				EntitySkeleton skeleton = (EntitySkeleton)event.getEntityLiving();
				EntityMinionZombie minion = (EntityMinionZombie)event.getSource().getEntity();

				//if a zombie is attacked by a minion, and has half of his life left, transform it.
				if(skeleton.getHealth() <= skeleton.getMaxHealth()/2 && skeleton.worldObj.rand.nextDouble() > 0.7D){
					if (!skeleton.worldObj.isRemote){
						EntityMinionSkeleton ems = new EntityMinionSkeleton(skeleton.worldObj);
						ems.setOwnerId(minion.getOwnerId());
						ems.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
						ems.setPositionAndRotation(skeleton.posX, skeleton.posY, skeleton.posZ, skeleton.getRotationYawHead(), skeleton.rotationPitch);
						skeleton.worldObj.spawnEntityInWorld(ems);
					}
					skeleton.setDead();
				}
			}
		}

	}

	@SubscribeEvent
	public void onMinionDeath(LivingDeathEvent event){
		//		System.out.println(event.getEntityLiving());
		//
		//		if(event.getEntityLiving() instanceof EntityMinionZombie || event.getEntityLiving() instanceof EntityMinionSkeleton){
		//			EntityTameable entityMinion = (EntityTameable)event.getEntityLiving();
		//
		//			if (entityMinion.getOwner() != null && entityMinion.getOwner() instanceof EntityPlayer) {
		//				if (MinionRegistry.playerMinions.containsKey(entityMinion.getOwner().getName())) {
		//					List<IMinion> list = MinionRegistry.playerMinions.get(entityMinion.getOwner().getName());
		//					if (list.contains(this)) {
		//						list.remove(this);
		//					}
		//				}
		//			}
		//		}
	}
}
