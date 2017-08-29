package subaraki.umbralux.item.weapon;

import lib.playerclass.capability.PlayerClass;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import subaraki.umbralux.config.ConfigurationHandler;
import subaraki.umbralux.entity.EntitySwordSpecial;
import subaraki.umbralux.item.UmbraLuxItems;

public class ItemPaladinSword extends ItemSword{

	private static ToolMaterial paladin_sword = EnumHelper.addToolMaterial("paladin", 0, ConfigurationHandler.instance.sword_uses, 2.5F, ConfigurationHandler.instance.sword_damage, 0);

	public ItemPaladinSword(){
		super(paladin_sword);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(!PlayerClass.get(playerIn).isPlayerClass(UmbraLuxItems.PALADIN_CLASS))
			return EnumActionResult.FAIL;

		ItemStack stack = playerIn.getHeldItem(hand);

		if(worldIn.getBlockState(pos.up()).equals(Blocks.AIR.getDefaultState()) && worldIn.getBlockState(pos.up(2)).equals(Blocks.AIR.getDefaultState()))
			if(worldIn.getBlockState(pos).getBlock().canPlaceTorchOnTop(worldIn.getBlockState(pos), worldIn, pos) ||
					worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)){

				EntitySwordSpecial ess = new EntitySwordSpecial(worldIn);
				ess.setLocationAndAngles(pos.getX()+0.5d, pos.getY()+1, pos.getZ()+0.5d, 0, 0);
				ess.setOwnerId(playerIn.getUniqueID());
				ess.setItemStackToSlot(null, stack);
				if(!worldIn.isRemote)
					worldIn.spawnEntity(ess);

				playerIn.setHeldItem(hand, ItemStack.EMPTY);

				return EnumActionResult.SUCCESS; //super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
			}

		return EnumActionResult.FAIL;
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer)
			if(!((EntityPlayer)entityLiving).getCooldownTracker().hasCooldown(this))
				((EntityPlayer)entityLiving).getCooldownTracker().setCooldown(this, 7);

		return super.onEntitySwing(entityLiving, stack);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem().equals(UmbraLuxItems.craftLeather) && repair.getMetadata() == 1 ? true : super.getIsRepairable(toRepair, repair);
	}
}
