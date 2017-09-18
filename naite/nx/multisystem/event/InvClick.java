package nx.multisystem.event;

import nx.multisystem.data.PlayerPropertyData;
import nx.multisystem.gui.CoreGUI;
import nx.multisystem.language.GUILanguage;
import nx.multisystem.language.MessageLanguage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import nx.multisystem.gui.DataGUI;
import nx.multisystem.gui.PropertyGUI;

public class InvClick implements Listener{

	@EventHandler
	public void onInventoryClick(InventoryClickEvent invce){
		Player p = (Player) invce.getWhoClicked();
		Inventory inv = invce.getInventory();

		if(inv.getName().equals(GUILanguage.CORE_DISPLAY_NAME) || inv.getName().equals(GUILanguage.DATA_DISPLAY_NAME) || inv.getName().equals(GUILanguage.PROPERTY_DISPLAY_NAME.replaceAll("%point%", String.valueOf(PlayerPropertyData.getPlayerData(p).getPoint())))){
			try{
				invce.getCurrentItem().equals(null);
				invce.getCurrentItem().getItemMeta().getDisplayName().equals(null);
			}
			catch(NullPointerException ex){
				invce.setCancelled(true);
				return;
			}

			if(invce.getClick() == ClickType.RIGHT || invce.getClick() == ClickType.LEFT){
				invce.setCancelled(true);
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_DATA_NAME)){
					p.closeInventory();
					DataGUI.openInterface(p);
				}
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_PROPERTY_NAME)){
					p.closeInventory();
					PropertyGUI.openInterface(p);
				}
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_BACK_NAME)){
					p.closeInventory();
					CoreGUI.openInterface(p);
				}
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_STR_NAME) ||
						invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_INT_NAME) ||
						invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_AGI_NAME) ||
						invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_LUK_NAME) ||
						invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_CON_NAME) ||
						invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_WIS_NAME)){
					if(PlayerPropertyData.getPlayerData(p).getPoint() == 0){
						p.sendMessage(MessageLanguage.POINT_LACK);
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_STR_NAME)){
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1){
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addStr(1);
							p.sendMessage(MessageLanguage.ADD_STR);
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_INT_NAME)){
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1){
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addInt(1);
							p.sendMessage(MessageLanguage.ADD_INT);
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_AGI_NAME)){
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1){
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addAgi(1);
							p.sendMessage(MessageLanguage.ADD_AGI);
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_LUK_NAME)){
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1){
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addLuk(1);
							p.sendMessage(MessageLanguage.ADD_LUK);
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_CON_NAME)){
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1){
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addCon(1);
							p.sendMessage(MessageLanguage.ADD_CON);
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(GUILanguage.ITEM_WIS_NAME)){
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1){
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addWis(1);
							p.sendMessage(MessageLanguage.ADD_WIS);
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
				}
			}
			else invce.setCancelled(true);
		}
	}
}
