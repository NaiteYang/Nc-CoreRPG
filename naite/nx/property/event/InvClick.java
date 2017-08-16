package nx.property.event;

import nx.property.data.PlayerPropertyData;
import nx.property.gui.CoreGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import nx.property.gui.DataGUI;
import nx.property.gui.PropertyGUI;

public class InvClick implements Listener
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent invce)
	{
		Player p = (Player) invce.getWhoClicked();
		Inventory inv = invce.getInventory();
		
		if(inv.getName().equals(CoreGUI.invName)||inv.getName().equals(DataGUI.invName)||inv.getName().equals(PropertyGUI.invName.replaceAll("%point%", String.valueOf(PlayerPropertyData.getPlayerData(p).getPoint()))))
		{
			try{
				invce.getCurrentItem().equals(null);
				invce.getCurrentItem().getItemMeta().getDisplayName().equals(null);
			}catch (NullPointerException ex){
				invce.setCancelled(true);
				return;
			}
			
			if(invce.getClick() == ClickType.RIGHT || invce.getClick() == ClickType.LEFT)
			{
				invce.setCancelled(true);
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(CoreGUI.corePlayerSkull.getItemMeta().getDisplayName()))
				{
					p.closeInventory();
					DataGUI.openInterface(p);
				}
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(CoreGUI.property.getItemMeta().getDisplayName()))
				{
					p.closeInventory();
					PropertyGUI.openInterface(p);
				}
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(DataGUI.back.getItemMeta().getDisplayName()))
				{
					p.closeInventory();
					CoreGUI.openInterface(p);
				}
			}else invce.setCancelled(true);
		}
	}
}
