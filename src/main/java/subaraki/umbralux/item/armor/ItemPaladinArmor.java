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

public class ItemPaladinArmor extends  ModeledArmor{

	private static AMCC pal = new AMCC(ConfigurationHandler.instance.paladin_armor);
	public static final ArmorMaterial paladin = EnumHelper.addArmorMaterial("paladin", AddonUmbraLux.MODID+":paladin", pal.getDurability(), pal.getReduction(), pal.getEnchantibility(), SoundEvents.ITEM_ARMOR_EQUIP_IRON, pal.getToughness());

	public ItemPaladinArmor(EntityEquipmentSlot slot) {
		super(slot, paladin, "paladin_"+slot.getName());
	}

	@Override
	public String armorClassName() {
		return UmbraLuxItems.PALADIN_CLASS;
	}

	@Override
	public Item getLinkedShieldItem() {
		return UmbraLuxItems.paladin_shield;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot) {
		switch (armorSlot) {
		case CHEST:
		case FEET:
			setArmorModel(AddonUmbraLux.proxy.getArmorModel(ClientProxy.PALACHEST));
			return;
		case HEAD:
		case LEGS:
			setArmorModel(AddonUmbraLux.proxy.getArmorModel(ClientProxy.PALAREST));
			return;

		default:
			break;
		}
	}
}
