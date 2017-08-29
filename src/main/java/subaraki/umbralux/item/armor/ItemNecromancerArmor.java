package subaraki.umbralux.item.armor;

import lib.item.armor.ModeledArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import subaraki.umbralux.config.AMCC;
import subaraki.umbralux.config.ConfigurationHandler;
import subaraki.umbralux.handler.proxy.ClientProxy;
import subaraki.umbralux.item.UmbraLuxItems;
import subaraki.umbralux.mod.AddonUmbraLux;

public class ItemNecromancerArmor extends  ModeledArmor{

	private static AMCC nec = new AMCC(ConfigurationHandler.instance.paladin_armor);
	public static final ArmorMaterial necromancer = EnumHelper.addArmorMaterial("necro", AddonUmbraLux.MODID+":necromancer", nec.getDurability(), nec.getReduction(), nec.getEnchantibility(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, nec.getToughness());

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
