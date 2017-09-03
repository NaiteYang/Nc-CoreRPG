package nx.property.gui;

import nx.property.data.PlayerPropertyData;
import nx.property.data.PropertySettings;
import nx.property.event.ColorSwitch;
import nx.property.file.ClientDataGUI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DataGUI 
{
	// create item
	public static ItemStack none = new ItemStack(Material.STAINED_GLASS_PANE ,1 ,(short)15);
	public static ItemStack level = new ItemStack(Material.NETHER_STAR ,1);
	public static ItemStack exp = new ItemStack(Material.EXP_BOTTLE ,1);
	public static ItemStack health = new ItemStack(Material.SPLASH_POTION ,1);
	public static ItemStack mana = new ItemStack(Material.SPLASH_POTION ,1);
	public static ItemStack mentality = new ItemStack(Material.SPLASH_POTION ,1);
	public static ItemStack vitality = new ItemStack(Material.SPLASH_POTION ,1);
	public static ItemStack atk = new ItemStack(Material.IRON_SWORD,1);
	public static ItemStack def = new ItemStack(Material.IRON_CHESTPLATE,1);
	public static ItemStack aar = new ItemStack(Material.IRON_BOOTS,1);
	public static ItemStack akb = new ItemStack(Material.BLAZE_POWDER,1);
	public static ItemStack ahit = new ItemStack(Material.ARROW,1);
	public static ItemStack mag = new ItemStack(Material.STICK,1);
	public static ItemStack res = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
	public static ItemStack sar = new ItemStack(Material.FEATHER,1);
	public static ItemStack skb = new ItemStack(Material.FIREBALL,1);
	public static ItemStack shit = new ItemStack(Material.SPECTRAL_ARROW,1);
	public static ItemStack back = new ItemStack(Material.SPRUCE_DOOR_ITEM,1);
	
	// yaml connection path
	static YamlConfiguration yaml = (YamlConfiguration) ClientDataGUI.getDataGUI();
	
	// string
	public static String invName = yaml.getString("Setting.Inventory.DisplayName");
	
	// get item meta
	public static void setMeta(ItemStack stack,String item, Player p)
	{
		ItemMeta im = stack.getItemMeta(); 
		im.setDisplayName(yaml.getString("Setting.Items." + item + ".DisplayName", " "));
		im.setLore(replaceData(yaml.getStringList("Setting.Items." + item + ".Lore"), item, p));
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		stack.setItemMeta(im);
	}

	// get potion meta
	public static void setMeta(ItemStack stack,String item, Color color, Player p)
	{
		PotionMeta pm = (PotionMeta) stack.getItemMeta();
		pm.setDisplayName(yaml.getString("Setting.Items." + item + ".DisplayName", " "));
		pm.setLore(replaceData(yaml.getStringList("Setting.Items." + item + ".Lore"), item, p));
		pm.setColor(color);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		stack.setItemMeta(pm);
	}

	private static List<String> replaceData(List<String> list, String item, Player p){
		List<String> replace = new ArrayList<>();
		for(String str : list){
			replace.add(replaceData(str, item, p));
		}
		return replace;
	}

	private static String replaceData(String str, String item, Player p){
		PlayerPropertyData data = PlayerPropertyData.getPlayerData(p);
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMaximumFractionDigits(2);
		switch(item){
			case "Level":
				return str.replaceAll("%lvl%", String.valueOf(PlayerPropertyData.getPlayerData(p).getLevel()));
			case "Experience":
				return str.replaceAll("%exp%", String.valueOf(data.getExp()))
						.replaceAll("%maxexp%", String.valueOf(PropertySettings.getMaxExp(data.getLevel())))
						.replaceAll("%needexp%", String.valueOf(PropertySettings.getMaxExp(data.getLevel()) - data.getExp()));
			case "Health":
				return str.replaceAll("%hp%", String.valueOf(data.getHealth()))
						.replaceAll("%rehp%", String.valueOf(data.getRestoreHealth()))
						.replaceAll("%maxhp%", String.valueOf(data.getMaxHealth()));
			case "Mana":
				return str.replaceAll("%mp%", String.valueOf(data.getMana()))
						.replaceAll("%remp%", String.valueOf(data.getRestoreMana()))
						.replaceAll("%maxmp%", String.valueOf(data.getMaxMana()));
			case "Mentality":
				return str.replaceAll("%va%", String.valueOf(data.getMentality()))
						.replaceAll("%reva%", String.valueOf(data.getRestoreMentality()))
						.replaceAll("%maxva%", String.valueOf(data.getMaxMentality()));
			case "Vitality":
				return str.replaceAll("%ma%", String.valueOf(data.getVitality()))
						.replaceAll("%rema%", String.valueOf(data.getRestoreVitality()))
						.replaceAll("%maxma%", String.valueOf(data.getMaxVitality()));
			case "Atk":
				return str.replaceAll("%atk%", String.valueOf(data.getAtk()));
			case "Def":
				return str.replaceAll("%def%", String.valueOf(data.getDef()));
			case "Aar":
				return str.replaceAll("%aar%", percent.format(data.getAar()));
			case "Akb":
				return str.replaceAll("%akb%", percent.format(data.getAkb()));
			case "Ahit":
				return str.replaceAll("%ahit%", percent.format(data.getAhit()));
			case "Mag":
				return str.replaceAll("%mag%", String.valueOf(data.getMag()));
			case "Res":
				return str.replaceAll("%res%", String.valueOf(data.getRes()));
			case "Sar":
				return str.replaceAll("%sar%", percent.format(data.getSar()));
			case "Skb":
				return str.replaceAll("%skb%", percent.format(data.getSkb()));
			case "Shit":
				return str.replaceAll("%shit%", percent.format(data.getShit()));
			default:
				return str;
		}
	}
	
	// open gui
	public static void openInterface(Player p)
	{
		setItem(p);

		Inventory inv = Bukkit.createInventory(null, 54, invName);
		for (int i = 0;i<54;i++){inv.setItem(i, none);}
		CoreGUI.getGeneralPlayerSkull(p);
		inv.setItem(4, CoreGUI.generalPlayerSkull);
		inv.setItem(10, level);
		inv.setItem(11, exp);
		inv.setItem(13, health);
		inv.setItem(14, mana);
		inv.setItem(15, mentality);
		inv.setItem(16, vitality);
		inv.setItem(29, atk);
		inv.setItem(30, def);
		inv.setItem(31, aar);
		inv.setItem(32, akb);
		inv.setItem(33, ahit);
		inv.setItem(38, mag);
		inv.setItem(39, res);
		inv.setItem(40, sar);
		inv.setItem(41, skb);
		inv.setItem(42, shit);
		inv.setItem(53, back);
		p.openInventory(inv);
	}
	
	// ymal reload
	public static void reload()
	{
		ColorSwitch.replaceColor(yaml);
		invName = yaml.getString("Setting.Inventory.DisplayName");
	}
	
	// set items
	public static void setItem(Player p)
	{
		setMeta(none, "None", p); // None
		setMeta(level, "Level", p); // Level
		setMeta(exp, "Experience", p); // Experience
		setMeta(health, "Health", Color.RED, p); // Health
		setMeta(mana, "Mana", Color.BLUE, p); // Mana
		setMeta(mentality, "Mentality", Color.AQUA, p); // Mentality
		setMeta(vitality, "Vitality", Color.ORANGE, p); // Vitality
		setMeta(atk, "Atk", p); // Attack
		setMeta(def, "Def", p); // Defense
		setMeta(aar, "Aar", p); // Physics avoidance rate
		setMeta(akb, "Akb", p); // Physics kill burst
		setMeta(ahit, "Ahit", p); // Physics hit
		setMeta(mag, "Mag", p); // Magic
		setMeta(res, "Res", p); // Sorcery defense
		setMeta(sar, "Sar", p); // Sorcery avoidance
		setMeta(skb, "Skb", p); // Sorcery kill burst
		setMeta(shit, "Shit", p); // Sorcery hit
		setMeta(back, "Back", p); //back the system gui
	}
}