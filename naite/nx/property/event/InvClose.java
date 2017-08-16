package nx.property.event;

import org.bukkit.event.Listener;

public class InvClose implements Listener
{
	/*/public void onInventoryClose (InventoryCloseEvent e)
	{
		Player p = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		if(inv.getName().equals(CoreGUI.Inv.getName()))
		{
			p.removeScoreboardTag("NcProperty_OpenGui");
		}
	}
	//*/
}
