package nx.multisystem.gui;

import nx.multisystem.language.GUILanguage;
import nx.multisystem.util.GUIItemGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CoreGUI{

	// open gui
	public static void openInterface(Player p){
		Inventory inv = Bukkit.createInventory(null, 27, GUILanguage.CORE_DISPLAY_NAME);
		for(int i = 0; i < 27; i++){
			inv.setItem(i, GUIItemGenerator.getBlankItem());
		}
		inv.setItem(14, getPropertyItem());
		inv.setItem(12, getDataItem(p));
		p.openInventory(inv);
	}

	private static ItemStack getDataItem(Player p){
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_DATA_NAME);
		im.setLore(GUILanguage.ITEM_DATA_LORE);
		item.setItemMeta(GUIItemGenerator.setPlayerSkin(im, p.getName()));
		return item;
	}

	private static ItemStack getPropertyItem(){
		ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_PROPERTY_NAME);
		im.setLore(GUILanguage.ITEM_PROPERTY_LORE);
		item.setItemMeta(im);
		return item;
	}
}