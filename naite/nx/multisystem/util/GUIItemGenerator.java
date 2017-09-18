package nx.multisystem.util;

import nx.multisystem.language.GUILanguage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIItemGenerator{

	private static ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
	private static ItemStack back = new ItemStack(Material.SPRUCE_DOOR_ITEM, 1);

	static{
		ItemMeta blankMeta = blank.getItemMeta();
		blankMeta.setDisplayName("");
		blank.setItemMeta(blankMeta);

		ItemMeta backMeta = back.getItemMeta();
		backMeta.setDisplayName(GUILanguage.ITEM_BACK_NAME);
		backMeta.setLore(GUILanguage.ITEM_BACK_LORE);
		back.setItemMeta(backMeta);
	}

	public static ItemMeta setPlayerSkin(ItemMeta meta, String name){
		SkullMeta sm = (SkullMeta) meta;
		sm.setOwner(name);
		return sm;
	}

	public static ItemStack getPlayerSkull(String name){
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(setPlayerSkin(im, name));
		return item;
	}

	public static ItemStack getBlankItem(){
		return blank;
	}

	public static ItemStack getBackItem(){
		return back;
	}

}
