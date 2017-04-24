package subaraki.umbralux.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import subaraki.umbralux.entity.minion.IMinion;

public class EntityNecroCloud extends EntityThrowable{

	public EntityNecroCloud(World worldIn){
		super(worldIn);
	}
	public EntityNecroCloud(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	public EntityNecroCloud(World worldIn, EntityLivingBase livingBaseIn){
		super(worldIn, livingBaseIn);
		setSize(2, 2);
		this.setThrowableHeading(livingBaseIn.getLookVec().xCoord, livingBaseIn.getLookVec().yCoord, livingBaseIn.getLookVec().zCoord, 1.5F, 0.0f);
	}

	@Override
	protected void onImpact(RayTraceResult result) {

		if (result.entityHit != null){
			if(!(result.entityHit instanceof EntityLivingBase))
				return;

			EntityLivingBase elb = (EntityLivingBase)result.entityHit;

			if(!(elb instanceof IMinion) || elb instanceof EntityPlayer && elb.getEntityId()!=getThrower().getEntityId()){
				elb.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5);
				elb.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.WITHER, 20, 2)));
				this.setDead();
			}
		}
	}

	@Override
	protected float getGravityVelocity(){
		return 0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(ticksExisted > 20)
			this.setDead();

		if(world.isRemote){
			for(int i = 0 ; i < 7; i++)
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX - 1f + world.rand.nextFloat()*2f, -1f +posY+rand.nextDouble()*2f, posZ - 1f + world.rand.nextFloat()*2f, 0.0D, 0.0D, 0.0D, new int[0]);
			for(int i = 0 ; i < 4; i++)
				world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX - 1f + world.rand.nextFloat()*2F, -1f +posY+rand.nextDouble()*2f, posZ - 1f + world.rand.nextFloat()*2f, 0.0D, 0.0D, 0.0D, new int[0]);

		}

		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_ENDERDRAGON_AMBIENT, SoundCategory.HOSTILE, 0.5F, 1.0F, false);
	}
}

