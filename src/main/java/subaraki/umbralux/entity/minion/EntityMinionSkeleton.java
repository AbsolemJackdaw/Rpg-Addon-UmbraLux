package subaraki.umbralux.entity.minion;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import subaraki.umbralux.entity.damage.DamageSourceMinion;

public class EntityMinionSkeleton extends EntityTameable  implements IMob, IMinion, IRangedAttackMob{

	private EntityPlayer owner;
	boolean invulnerable = false;

	private static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityMinionSkeleton.class, DataSerializers.VARINT);

	public EntityMinionSkeleton(World world) {
		super(world);
		this.setSize(0.7F, 1.7F);

		this.getNavigator().setPath((Path) null, 0D);
		this.Following();
		this.setAttackTarget((EntityLivingBase) null);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 0.5f, 15, 10.0F));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 0.5f, 10.0F, 2.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 0.5f));
		// This lets the mobs look around at any living entity mobs, to see if
		// they need to attack it or not. Also makes minions appear curious ;)
		this.tasks.addTask(5,new EntityAIWatchClosest(this, Entity.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		// This makes minions aggressive to aggressive mobs and players, except
		// for their owner.
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this,EntityLiving.class, 0, true, false, new Predicate<Entity>()
		{
			public boolean apply(@Nullable Entity target)
			{
				if(target instanceof IMinion)
					return false;
				if(target instanceof IMob)
					return true;
				if(target instanceof EntityPlayer)
					return target.equals(owner) ? false : true;

				return false;
			}
		}));
	}

	@Override //prevents them from attacking the other class
	public boolean isOnSameTeam(Entity entityIn) {
		if(entityIn instanceof EntityMinionZombie || entityIn instanceof EntityMinionSkeleton)
			return true;

		return false;
	}

	@Override
	public void setOwnerId(UUID theUUID) {
		EntityPlayer player = world.getPlayerEntityByUUID(theUUID);

		if(player != null)
			MinionRegistry.addMinionForPlayer(player, this);

		super.setOwnerId(theUUID);
	}
	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(AGE, 0);
	}

	@Override
	public void Harvest() {
		this.attackEntityFrom(DamageSourceMinion.causeMinionAgeDamage(this), 10);
		if(getMaster() != null)
			getMaster().heal(1);
	}

	private EntityPlayer getMaster(){
		return getOwner() instanceof EntityPlayer ? (EntityPlayer)getOwner() : null ;
	}

	@Override
	public void setInvulnerable(boolean val) {
		invulnerable = val;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public boolean attackEntityAsMob(Entity victim) {
		return victim.attackEntityFrom(DamageSourceMinion.causeMinionDamage(this), 5);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float amount) {
		Entity sourceDirect = damageSource.getTrueSource();
		Entity source = damageSource.getImmediateSource();

		//doubles resistance on anything but player and arrow damage

		if (sourceDirect != null && !(sourceDirect instanceof EntityPlayer)) {
			amount = (amount + 1) / 2;
		}
		
		if (source != null && !(source instanceof EntityArrow)) {
			amount = (amount + 1) / 2;
		}

		return super.attackEntityFrom(damageSource, amount);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public int getGrowingAge() {
		return getDataManager().get(AGE);
	}

	public void setAge(int time){
		getDataManager().set(AGE, time);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return invulnerable;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		MinionRegistry.removeMinion(getMaster(), this);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if(this.getAttackTarget() != null)
			if(this.getAttackTarget() instanceof IMinion)
				this.setAttackTarget((EntityLivingBase)null);

		if (getMaster() != null){
			int age = this.getGrowingAge();

			if ((age >= 0) && (age < (1000))){
				++age;
				this.setAge(age);
			} else if (age >= (1000)){
				this.attackEntityFrom(DamageSourceMinion.causeMinionAgeDamage(this), this.getHealth()*5);
				age = 0;
				MinionRegistry.removeMinion(getMaster(), this);
			}
		} else {
			// player logged out, kill his minions.
			this.attackEntityFrom(DamageSourceMinion.causeMinionAgeDamage(this), this.getHealth()*5);
			this.setAge(0);
			//minion gets removed from list in player logged out event
		}
	}
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////

	private void Following() {
		float distanceToMaster = 18.0F;
		if(this.getAttackTarget() == null){
			if (getMaster() != null) {
				distanceToMaster = (float) this.getDistanceSq(getMaster());
				if ((distanceToMaster > 3.0F) && (distanceToMaster < 18.0F)) {
					Path var2 = getNavigator().getPathToEntityLiving(owner);
					this.getNavigator().setPath(var2, this.getAIMoveSpeed()*1.5f);

				} else {
					this.getNavigator().setPath(null, 0);
				}
			}
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
		double d0 = target.posX - this.posX;
		double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entitytippedarrow.posY;
		double d2 = target.posZ - this.posZ;
		double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
		entitytippedarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 0.5f);
		DifficultyInstance difficultyinstance = this.world.getDifficultyForLocation(new BlockPos(this));
		entitytippedarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.world.getDifficulty().getDifficultyId() * 0.11F));

		this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(entitytippedarrow);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}
}
