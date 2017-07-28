package nx.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import nx.gui.Client;

public class InvClick implements Listener
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent invce)
	{
		Player p = (Player) invce.getWhoClicked();
		Inventory inv = invce.getInventory();
		
		if(inv.getName().equals(Client.Inv.getName()))
		{	
			if(invce.getClick() == ClickType.RIGHT || invce.getClick() == ClickType.LEFT) 
			{
				invce.setCancelled(true);
				
				if(invce.getCurrentItem().equals(Client.playerData))
				{
					p.sendMessage("open");
				}
			}
		}
	}
}
