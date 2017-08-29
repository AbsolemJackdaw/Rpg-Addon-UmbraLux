package subaraki.umbralux.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;

public class ConfigurationHandler
{
	public static ConfigurationHandler instance = new ConfigurationHandler();

	public int shield_uses;
	public int paladin_special;

	public int sword_uses;
	public float sword_damage; 
	
	public int skull_cloud_damage;
	public float skull_damage; 
	
	public String necro_armor[];
	public String paladin_armor[];

	public void loadConfig(File file)
	{
		Configuration config = new Configuration(file);
		config.load();
		loadSettings(config);
		config.save();
	}

	private void loadSettings(Configuration config)
	{
		config.addCustomCategoryComment("Item Uses", "define the number of maximum uses for the items - min 100, max : " + Integer.MAX_VALUE);
		config.addCustomCategoryComment("Damage", "define the ammount of damage dealt by mentioned object");
		config.addCustomCategoryComment("Armor", "define the armor's uses, resistance, enchantabilty and toughness");

		shield_uses = config.getInt("shield uses", "Item Uses", 250, 1, Integer.MAX_VALUE, "appleis to both necromancer and paladin shield");
		
		paladin_special = config.getInt("paladin special damage", "Damage", 5, 0, Integer.MAX_VALUE, "minimum damage for the sword's special");
		sword_uses = config.getInt("paladin sword uses", "Damage", 500, 0, Integer.MAX_VALUE, "maximum uses for the sword");
		sword_damage = config.getFloat("paladin sword damage", "Damage", 5F, 0, Float.MAX_VALUE, "minimum damage for the sword");

		skull_cloud_damage = config.getInt("necro cloud damage", "Damage", 5, 0, Integer.MAX_VALUE, "physical direct damage for the necro cloud");
		skull_damage = config.getInt("necro skull damage", "Damage", 6, 0, Integer.MAX_VALUE, "damage when hitting an entity with the skull");

		necro_armor = config.getStringList("necromancer armor material", "armor", new String[]{"250", "3", "5", "4", "3", "10", "0.0"}, "uses, armor reduction(head, chest, legs, feet), toughness");
		paladin_armor = config.getStringList("paladin armor material", "armor", new String[]{"250", "3", "5", "4", "6", "25", "0.0"}, "uses, armor reduction(head, chest, legs, feet), toughness");
	
	}
}