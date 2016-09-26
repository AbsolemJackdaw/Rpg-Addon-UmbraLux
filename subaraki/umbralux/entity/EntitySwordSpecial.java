package subaraki.umbralux.entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import subaraki.umbralux.block.UmbraLuxBlocks;
import subaraki.umbralux.entity.damage.DamageSourcePaladin;
import subaraki.umbralux.entity.minion.IMinion;

public class EntitySwordSpecial extends EntityLivingBase{

	private ItemStack[] inventory = new ItemStack[1];
	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityTameable.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public EntitySwordSpecial(World worldIn) {
		super(worldIn);
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return true;
	}

	@Override
	public void setLocationAndAngles(double posx, double posy, double posz, float yaw, float pitch) {
		super.setLocationAndAngles(posx, posy, posz, yaw, pitch);

		BlockPos pos = new BlockPos(posX, posY, posZ);
		if(worldObj.isAirBlock(pos))
			worldObj.setBlockState(pos, UmbraLuxBlocks.airLuminence.getDefaultState());
	}

	@Override
	public void onLivingUpdate() {
		motionX = motionY = motionZ = 0;
		super.onLivingUpdate();

		attackSurroundingEntities();

		spawnParticles();
		
		if(ticksExisted > 250 || getOwner() == null){
			this.setDead();
			removeSword();
		}
	}
	
	private void attackSurroundingEntities(){
		List<EntityLivingBase> enemiesInRange = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().expand(5, 2, 5));
		for(EntityLivingBase elb : enemiesInRange){
			if(elb instanceof IMinion){
				if(elb.ticksExisted%5==0){
					elb.attackEntityFrom(DamageSourcePaladin.causeHolySwordDamage(this), 12);
					elb.setFire(20);
				}
			}
			else if(elb instanceof EntityMob){
				if(elb.ticksExisted%5==0){
					elb.attackEntityFrom(DamageSourcePaladin.causeHolySwordDamage(this), 5);
					if(((EntityMob)elb).getCreatureAttribute().equals(EnumCreatureAttribute.UNDEAD)){
						elb.setFire(10);
					}
				}
			}
			else if(elb instanceof EntityPlayer){
				if(!((EntityPlayer)elb).equals(getOwner())){
					if(elb.ticksExisted%5==0){
						elb.setFire(5);
						elb.attackEntityFrom(DamageSourcePaladin.causeHolySwordDamage(this), 4);
					}
				}
			}
		}
	}

	private void spawnParticles(){
		if(ticksExisted == 5){
			worldObj.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1.0f, 1.0f, true);
			for(int i = 0; i < 3; i++)
				worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX, posY, posZ, 0, 0, 0, new int[0]);
		}

		if(ticksExisted > 5){
			rotationYawHead += 2f;
			for(float f = 0; f < 360; f+=45){
				Vec3d vec = getVectorForRotation(0, f + rotationYawHead);
				worldObj.spawnParticle(EnumParticleTypes.FLAME, posX, posY+0.4, posZ, vec.xCoord/5, 0, vec.zCoord/5, new int[0]);
			}

			for(float f = 0; f < 360; f+=2){
				Vec3d vec = getVectorForRotation(0, f + rotationYawHead);
				worldObj.spawnParticle(EnumParticleTypes.FLAME, posX+ vec.xCoord*8, posY + rand.nextDouble()*2d - 1d, posZ+ vec.zCoord*8, rand.nextDouble()/50d, 0.1, rand.nextDouble()/50d, new int[0]);
			}
		}
	}
	
	private void removeSword(){
		if(getOwner() == null)
			if(inventory[0] != null)
				if(!worldObj.isRemote)
					worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, inventory[0].copy()));

		if(getOwner()!= null && inventory[0] != null){
			if(!worldObj.isRemote)
				getOwner().inventory.addItemStackToInventory(inventory[0].copy());
			getOwner().getCooldownTracker().setCooldown(inventory[0].getItem(), 250);
		}

		BlockPos pos = new BlockPos(posX, posY, posZ);
		if(worldObj.getLightFor(EnumSkyBlock.BLOCK, pos) != 0){
			if(worldObj.getBlockState(pos).equals(UmbraLuxBlocks.airLuminence.getDefaultState())){
				worldObj.setBlockToAir(pos);
			}
		}
	}
	
	/////////////////////////////////////////////////////////
	////////////////Base stuff be here///////////////////////
	/////////////////////////////////////////////////////////

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return Arrays.asList(this.inventory);
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		return inventory[0];
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
		if(stack == null)
			return;
		inventory[0] = stack.copy();
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		String uuid = "";

		if (compound.hasKey("OwnerUUID", 8))
			uuid = compound.getString("OwnerUUID");
		if (!uuid.isEmpty())
			this.setOwnerId(UUID.fromString(uuid));

		NBTTagCompound stacktag = new NBTTagCompound();
		if(inventory[0] != null){
			inventory[0].writeToNBT(stacktag);
			compound.setTag("stack", stacktag);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (this.getOwnerId() == null)
			compound.setString("OwnerUUID", "");
		else
			compound.setString("OwnerUUID", this.getOwnerId().toString());

		if(compound.hasKey("stack")){
			NBTTagCompound stackTag = compound.getCompoundTag("stack");
			inventory[0] = ItemStack.loadItemStackFromNBT(stackTag);
		}
	}

	@Nullable
	public UUID getOwnerId(){
		return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setOwnerId(@Nullable UUID owner){
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(owner));
	}

	public EntityPlayer getOwner(){
		UUID uuid = this.getOwnerId();
		return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
	}

	@Override
	protected void entityInit(){
		super.entityInit();
		this.dataManager.register(OWNER_UNIQUE_ID, Optional.<UUID>absent());
	}
}
