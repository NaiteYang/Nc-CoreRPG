package nx.gui;

import nx.data.DefaultData;
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

import java.util.ArrayList;
import java.util.List;

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
		DefaultData data = DefaultData.getPlayerData(p);
		switch(item){
			case "Str":
				return str.replaceAll("%str%", String.valueOf(data.getStr()));
			case "Int":
				return str.replaceAll("%int%", String.valueOf(data.getInt()));
			case "Agi":
				return str.replaceAll("%agi%", String.valueOf(data.getAgi()));
			case "Luk":
				return str.replaceAll("%luk%", String.valueOf(data.getLuk()));
			case "Con":
				return str.replaceAll("%con%", String.valueOf(data.getCon()));
			case "Wis":
				return str.replaceAll("%wis%", String.valueOf(data.getWis()));
			default:
				return str;
		}
	}
	
	// open gui
	public static void openInterface(Player p)
	{
		setItem(p);
		Inventory inv = Bukkit.createInventory(null, 54, invName.replaceAll("%point%", String.valueOf(DefaultData.getPlayerData(p).getPoint())));
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
	}
	
	// set items
	public static void setItem(Player p)
	{
		setMeta(none, "None", p); // None
		setMeta(str, "Str", p); // Str
		setMeta(ints, "Int", p); // Int
		setMeta(agi, "Agi", p); // Agi
		setMeta(luk, "Luk", Color.GREEN, p); // Luk
		setMeta(con, "Con", p); // Con
		setMeta(wis, "Wis", p); // Wis
	}
}


