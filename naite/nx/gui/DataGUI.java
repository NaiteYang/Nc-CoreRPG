package nx.gui;

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
import nx.event.ColorSwitch;
import nx.file.ClientDataGUI;

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
	public static void setMeta(ItemStack stack,String item)
	{
		ItemMeta im = stack.getItemMeta(); 
		im.setDisplayName(yaml.getString("Setting.Items." + item + ".DisplayName", " "));
		im.setLore(yaml.getStringList("Setting.Items." + item + ".Lore"));
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		stack.setItemMeta(im);
	}
	
	// get potion meta
	public static void setMeta(ItemStack stack,String item, Color color)
	{
		PotionMeta pm = (PotionMeta) stack.getItemMeta(); 
		pm.setDisplayName(yaml.getString("Setting.Items." + item + ".DisplayName", " "));
		pm.setLore(yaml.getStringList("Setting.Items." + item + ".Lore"));
		pm.setColor(color);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		stack.setItemMeta(pm);
	}
	
	// open gui
	public static void openInterface(Player p)
	{
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
		setItem();
	}
	
	// set items
	public static void setItem()
	{
		setMeta(none, "None"); // None
		setMeta(level, "Level"); // Level
		setMeta(exp, "Experience"); // Experience
		setMeta(health, "Health", Color.RED); // Health	
		setMeta(mana, "Mana", Color.BLUE); // Mana
		setMeta(mentality, "Mentality", Color.AQUA); // Mentality
		setMeta(vitality, "Vitality", Color.ORANGE); // Vitality
		setMeta(atk, "Atk"); // Attack
		setMeta(def, "Def"); // Defense
		setMeta(aar, "Aar"); // Physics avoidance rate
		setMeta(akb, "Akb"); // Physics kill burst
		setMeta(ahit, "Ahit"); // Physics hit
		setMeta(mag, "Mag"); // Magic
		setMeta(res, "Res"); // Sorcery defense
		setMeta(sar, "Sar"); // Sorcery avoidance
		setMeta(skb, "Skb"); // Sorcery kill burst
		setMeta(shit, "Shit"); // Sorcery hit
		setMeta(back, "Back"); //back the system gui
	}
}