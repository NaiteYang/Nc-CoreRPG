package nx.gui;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import nx.event.ColorSwitch;
import nx.file.ClientPropertyGUI;

public class PropertyGUI 
{
	// create item
	public static ItemStack none = new ItemStack(Material.STAINED_GLASS_PANE ,1 ,(short)15);
	public static ItemStack str = new ItemStack(Material.IRON_SWORD,1);
	public static ItemStack ints = new ItemStack(Material.STICK,1);
	public static ItemStack agi = new ItemStack(Material.FEATHER,1);
	public static ItemStack luk = new ItemStack(Material.TIPPED_ARROW,1);
	public static ItemStack con = new ItemStack(Material.BONE,1);
	public static ItemStack wis = new ItemStack(Material.TOTEM,1);
	
	
	// yaml connection path
	static YamlConfiguration yaml = (YamlConfiguration) ClientPropertyGUI.getPropertyGUI();

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
		inv.setItem(20, str);
		inv.setItem(22, ints);
		inv.setItem(24, agi);
		inv.setItem(38, luk);
		inv.setItem(40, con);
		inv.setItem(42, wis);
		inv.setItem(53, DataGUI.back);
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
		setMeta(str, "Str"); // Str
		setMeta(ints, "Int"); // Int
		setMeta(agi, "Agi"); // Agi
		setMeta(luk, "Luk", Color.GREEN); // Luk
		setMeta(con, "Con"); // Con
		setMeta(wis, "Wis"); // Wis
	}
}


