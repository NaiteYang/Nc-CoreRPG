package nx.multisystem.gui;

import nx.multisystem.data.PlayerPropertyData;
import nx.multisystem.language.GUILanguage;
import nx.multisystem.util.GUIItemGenerator;
import nx.multisystem.util.StringFormat;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class PropertyGUI{

	// open gui
	public static void openInterface(Player p){
		Inventory inv = Bukkit.createInventory(null, 54, GUILanguage.PROPERTY_DISPLAY_NAME.replaceAll("%point%", String.valueOf(PlayerPropertyData.getPlayerData(p).getPoint())));
		for(int i = 0; i < 54; i++){
			inv.setItem(i, GUIItemGenerator.getBlankItem());
		}
		inv.setItem(4, GUIItemGenerator.getPlayerSkull(p.getName()));
		PlayerPropertyData data = PlayerPropertyData.getPlayerData(p);
		inv.setItem(20, getStrItem(data));
		inv.setItem(22, getIntItem(data));
		inv.setItem(24, getAgiItem(data));
		inv.setItem(38, getLukItem(data));
		inv.setItem(40, getConItem(data));
		inv.setItem(42, getWisItem(data));
		inv.setItem(53, GUIItemGenerator.getBackItem());
		p.openInventory(inv);
	}

	private static ItemStack getStrItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_STR_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_STR_LORE, "%str%", p.getStr()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getIntItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_INT_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_INT_LORE, "%int%", p.getInt()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getAgiItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_AGI_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_AGI_LORE, "%agi%", p.getAgi()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getLukItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.TIPPED_ARROW);
		PotionMeta pm = (PotionMeta) item.getItemMeta();
		pm.setDisplayName(GUILanguage.ITEM_LUK_NAME);
		pm.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_LUK_LORE, "%luk%", p.getLuk()));
		pm.setColor(Color.GREEN);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(pm);
		return item;
	}

	private static ItemStack getConItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.BONE, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_CON_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_CON_LORE, "%con%", p.getCon()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getWisItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.TOTEM, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_WIS_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_WIS_LORE, "%wis%", p.getWis()));
		item.setItemMeta(im);
		return item;
	}
}


