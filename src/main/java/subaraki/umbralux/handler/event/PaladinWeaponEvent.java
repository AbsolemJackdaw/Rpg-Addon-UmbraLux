package subaraki.umbralux.handler.event;

import java.util.Map;

import lib.playerclass.capability.PlayerClass;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.umbralux.item.UmbraLuxItems;

public class PaladinWeaponEvent {

	public PaladinWeaponEvent() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onPlayerUpdateTick(LivingUpdateEvent event){
		calculatePaladinBonus(event);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void calculatePaladinBonus(LivingUpdateEvent event){
		if(!(event.getEntityLiving() instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer)event.getEntityLiving();
		if(player == null)
			return;

		ItemStack heldItem = player.getHeldItemMainhand();

		if(heldItem == ItemStack.EMPTY)
			return;
		if(heldItem.getItem().equals(UmbraLuxItems.paladin_sword))
		{
			if(PlayerClass.get(player).isPlayerClass(UmbraLuxItems.PALADIN_CLASS))
			{
				if(PlayerClass.get(player).isShielded())
					addEnchantment(Enchantments.SMITE, 4, heldItem);
				else
					addEnchantment(Enchantments.SMITE, 2, heldItem);
			}
			else
				removeEnchantment(Enchantments.SMITE, heldItem);
		}
	}

	private void addEnchantment(Enchantment ench, int level, ItemStack stack){
		Map<Enchantment, Integer> tmp = EnchantmentHelper.getEnchantments(stack);

		if(!tmp.containsKey(ench))
			tmp.put(ench, level);
		else if(tmp.get(ench) != level)
			tmp.put(ench, level);

		EnchantmentHelper.setEnchantments(tmp,stack);
	}

	private void removeEnchantment(Enchantment ench, ItemStack stack){
		Map<Enchantment, Integer> tmp = EnchantmentHelper.getEnchantments(stack);
		if(!tmp.containsKey(ench))
			return;
		tmp.remove(ench);
		EnchantmentHelper.setEnchantments(tmp, stack);
	}
}
