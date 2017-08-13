package nx.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import nx.core.Core;
import nx.event.ColorSwitch;
import nx.file.ClientCoreGUI;

public class CoreGUI 
{		
	// item
	public static ItemStack corePlayerSkull = new ItemStack(Material.SKULL_ITEM ,1 ,(short)3);
	public static ItemStack generalPlayerSkull = new ItemStack(Material.SKULL_ITEM ,1 ,(short)3);
	public static ItemStack property = new ItemStack(Material.ENDER_PEARL ,1 );
	public static ItemStack none = new ItemStack(Material.STAINED_GLASS_PANE ,1 ,(short)15);
	
	// yaml connection path
	private static YamlConfiguration config = (YamlConfiguration) Core.plugin.getConfig();
	static YamlConfiguration yaml = (YamlConfiguration) ClientCoreGUI.getCoreGUI();
	
	// string
	public static String invName = yaml.getString("Setting.Inventory.DisplayName");
	
	// get item meta
	public static void setMeta(ItemStack stack,String item)
	{
		ItemMeta meta = stack.getItemMeta(); 
		meta.setDisplayName(yaml.getString("Setting.Items." + item + ".DisplayName", " "));
		meta.setLore(yaml.getStringList("Setting.Items." + item + ".Lore"));
		stack.setItemMeta(meta);
	}
	
	// get core gui player skull owner
	public static void getCorePlayerSkull(Player p)
	{
		SkullMeta sm = (SkullMeta) corePlayerSkull.getItemMeta();
		sm.setDisplayName(yaml.getString("Setting.Items.Data.DisplayName"));
		sm.setLore(yaml.getStringList("Setting.Items.Data.Lore"));
		sm.setOwner(p.getName().toString());
		corePlayerSkull.setItemMeta(sm);		
	}
	
	// get all gui player skull owner
	public static void getGeneralPlayerSkull(Player p)
	{
		SkullMeta sm = (SkullMeta) generalPlayerSkull.getItemMeta();
		sm.setDisplayName(config.getString("Replice.PlayerSkull").replace("{Skull}", p.getName()));
		sm.setOwner(p.getName().toString());
		generalPlayerSkull.setItemMeta(sm);		
	}
	
	// open gui
	public static void openInterface(Player p)
	{
		Inventory Inv = Bukkit.createInventory(null ,27 ,invName);
		for (int i = 0;i<27;i++){Inv.setItem(i, none);}
		getCorePlayerSkull(p);
		Inv.setItem(14, property);
		Inv.setItem(12, corePlayerSkull);
		p.openInventory(Inv);
	}
	
	// ymal reload
	public static void reload()
	{
		ColorSwitch.replaceColor(yaml);
		ColorSwitch.replaceColor(config);
		invName = yaml.getString("Setting.Inventory.DisplayName");
		setItem();
	}
	
	// set items
	public static void setItem()
	{
		setMeta(property, "Property"); // property
		setMeta(none, "None"); // none
	}
}