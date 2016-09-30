package subaraki.umbralux.item.armor;

import lib.item.armor.ModeledArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import subaraki.umbralux.handler.proxy.ClientProxy;
import subaraki.umbralux.item.UmbraLuxItems;
import subaraki.umbralux.mod.AddonUmbraLux;

public class ItemNecromancerArmor extends  ModeledArmor{

	public static final ArmorMaterial necromancer = EnumHelper.addArmorMaterial("necro", AddonUmbraLux.MODID+":necromancer", 250, new int[]{3,5,4,3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

	public ItemNecromancerArmor(EntityEquipmentSlot slot) {
		super(slot, necromancer, "necromancer_"+slot.getName());
	}

	@Override
	public String armorClassName() {
		return UmbraLuxItems.NECROMANCER_CLASS;
	}

	@Override
	public Item getLinkedShieldItem() {
		return UmbraLuxItems.necromancer_shield;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot) {
		switch (armorSlot) {
		case CHEST:
		case FEET:
			setArmorModel(AddonUmbraLux.proxy.getArmorModel(ClientProxy.NECROCHEST));
			return;
		case HEAD:
		case LEGS:
			setArmorModel(AddonUmbraLux.proxy.getArmorModel(ClientProxy.NECROREST));
			return;
		default:
			break;
		}
	}

}
