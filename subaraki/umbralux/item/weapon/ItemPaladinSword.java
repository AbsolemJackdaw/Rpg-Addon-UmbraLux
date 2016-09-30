package subaraki.umbralux.item.weapon;

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
import subaraki.umbralux.entity.EntitySwordSpecial;

public class ItemPaladinSword extends ItemSword{

	private static ToolMaterial paladin_sword = EnumHelper.addToolMaterial("paladin", 0, 500, 2.5F, 7F, 0);

	public ItemPaladinSword(){
		super(paladin_sword);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.getBlockState(pos.up()).equals(Blocks.AIR.getDefaultState()) && worldIn.getBlockState(pos.up(2)).equals(Blocks.AIR.getDefaultState()))
			if(worldIn.getBlockState(pos).getBlock().canPlaceTorchOnTop(worldIn.getBlockState(pos), worldIn, pos)){

				EntitySwordSpecial ess = new EntitySwordSpecial(worldIn);
				ess.setLocationAndAngles(pos.getX()+0.5d, pos.getY()+1, pos.getZ()+0.5d, 0, 0);
				ess.setOwnerId(playerIn.getUniqueID());
				ess.setItemStackToSlot(null, stack);
				if(!worldIn.isRemote)
					worldIn.spawnEntityInWorld(ess);

				playerIn.setHeldItem(hand, null);

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
}
