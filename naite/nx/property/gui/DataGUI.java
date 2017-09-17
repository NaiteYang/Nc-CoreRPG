package nx.property.gui;

import nx.property.data.PlayerPropertyData;
import nx.property.config.PropertySettings;
import nx.property.language.GUILanguage;
import nx.property.util.GUIItemGenerator;
import nx.property.util.StringFormat;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.text.NumberFormat;
import java.util.List;

public class DataGUI{

	private static NumberFormat percent = StringFormat.percentFormat(2);

	// open gui
	public static void openInterface(Player p){
		Inventory inv = Bukkit.createInventory(null, 54, GUILanguage.DATA_DISPLAY_NAME);
		for(int i = 0; i < 54; i++){
			inv.setItem(i, GUIItemGenerator.getBlankItem());
		}
		PlayerPropertyData data = PlayerPropertyData.getPlayerData(p);
		inv.setItem(4, GUIItemGenerator.getPlayerSkull(p.getName()));
		inv.setItem(10, getLevelItem(data));
		inv.setItem(11, getExpItem(data));
		inv.setItem(13, getHealthItem(data));
		inv.setItem(14, getManaItem(data));
		inv.setItem(15, getMentalityItem(data));
		inv.setItem(16, getVitalityItem(data));
		inv.setItem(29, getAtkItem(data));
		inv.setItem(30, getDefItem(data));
		inv.setItem(31, getAarItem(data));
		inv.setItem(32, getAkbItem(data));
		inv.setItem(33, getAhitItem(data));
		inv.setItem(38, getMagItem(data));
		inv.setItem(39, getResItem(data));
		inv.setItem(40, getSarItem(data));
		inv.setItem(41, getSkbItem(data));
		inv.setItem(42, getShitItem(data));
		inv.setItem(53, GUIItemGenerator.getBackItem());
		p.openInventory(inv);
	}

	private static ItemStack getLevelItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_LEVEL_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_LEVEL_LORE, "%lvl%", p.getLevel()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getExpItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.EXP_BOTTLE, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_EXP_NAME);
		List<String> lore = StringFormat.stringListReplace(GUILanguage.ITEM_EXP_LORE, "%exp%", p.getExp());
		lore = StringFormat.stringListReplace(lore, "%needexp%", PropertySettings.getMaxExp(p.getLevel()) - p.getExp());
		lore = StringFormat.stringListReplace(lore, "%maxexp%", PropertySettings.getMaxExp(p.getLevel()));
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getHealthItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		PotionMeta pm = (PotionMeta) item.getItemMeta();
		pm.setDisplayName(GUILanguage.ITEM_HEALTH_NAME);
		List<String> lore = StringFormat.stringListReplace(GUILanguage.ITEM_HEALTH_LORE, "%hp%", p.getHealth());
		lore = StringFormat.stringListReplace(lore, "%rehp%", p.getRestoreHealth());
		lore = StringFormat.stringListReplace(lore, "%maxhp%", p.getMaxHealth());
		pm.setLore(lore);
		pm.setColor(Color.RED);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(pm);
		return item;
	}

	private static ItemStack getManaItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		PotionMeta pm = (PotionMeta) item.getItemMeta();
		pm.setDisplayName(GUILanguage.ITEM_MANA_NAME);
		List<String> lore = StringFormat.stringListReplace(GUILanguage.ITEM_MANA_LORE, "%mp%", p.getMana());
		lore = StringFormat.stringListReplace(lore, "%remp%", p.getRestoreMana());
		lore = StringFormat.stringListReplace(lore, "%maxmp%", p.getMaxMana());
		pm.setLore(lore);
		pm.setColor(Color.BLUE);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(pm);
		return item;
	}

	private static ItemStack getMentalityItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		PotionMeta pm = (PotionMeta) item.getItemMeta();
		pm.setDisplayName(GUILanguage.ITEM_MENTALITY_NAME);
		List<String> lore = StringFormat.stringListReplace(GUILanguage.ITEM_MENTALITY_LORE, "%men%", p.getMentality());
		lore = StringFormat.stringListReplace(lore, "%remen%", p.getRestoreMentality());
		lore = StringFormat.stringListReplace(lore, "%maxmen%", p.getMaxMentality());
		pm.setLore(lore);
		pm.setColor(Color.AQUA);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(pm);
		return item;
	}

	private static ItemStack getVitalityItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
		PotionMeta pm = (PotionMeta) item.getItemMeta();
		pm.setDisplayName(GUILanguage.ITEM_VITALITY_NAME);
		List<String> lore = StringFormat.stringListReplace(GUILanguage.ITEM_VITALITY_LORE, "%vit%", p.getVitality());
		lore = StringFormat.stringListReplace(lore, "%revit%", p.getRestoreVitality());
		lore = StringFormat.stringListReplace(lore, "%maxvit%", p.getMaxVitality());
		pm.setLore(lore);
		pm.setColor(Color.ORANGE);
		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(pm);
		return item;
	}

	private static ItemStack getAtkItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_ATK_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_ATK_LORE, "%atk%", p.getAtk()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getDefItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_DEF_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_DEF_LORE, "%def%", p.getDef()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getAarItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_AAR_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_AAR_LORE, "%aar%", percent.format(p.getAar())));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getAkbItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_AKB_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_AKB_LORE, "%akb%", percent.format(p.getAkb())));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getAhitItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.ARROW, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_AHIT_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_AHIT_LORE, "%ahit%", percent.format(p.getAhit())));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getMagItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_MAG_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_MAG_LORE, "%mag%", p.getMag()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getResItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_RES_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_RES_LORE, "%res%", p.getRes()));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getSarItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.FEATHER, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_SAR_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_SAR_LORE, "%sar%", percent.format(p.getSar())));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getSkbItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.FIREBALL, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_SKB_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_SKB_LORE, "%skb%", percent.format(p.getSkb())));
		item.setItemMeta(im);
		return item;
	}

	private static ItemStack getShitItem(PlayerPropertyData p){
		ItemStack item = new ItemStack(Material.SPECTRAL_ARROW, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(GUILanguage.ITEM_SHIT_NAME);
		im.setLore(StringFormat.stringListReplace(GUILanguage.ITEM_SHIT_LORE, "%shit%", percent.format(p.getShit())));
		item.setItemMeta(im);
		return item;
	}
}