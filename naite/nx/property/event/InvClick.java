package nx.property.event;

import nx.property.data.PlayerPropertyData;
import nx.property.file.ClientMessages;
import nx.property.gui.CoreGUI;
import org.bukkit.configuration.file.YamlConfiguration;
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
	private static YamlConfiguration yaml = (YamlConfiguration) ClientMessages.getConfig();
	
	public static void reload()
	{
		ColorSwitch.replaceColor(yaml);
	}
	
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
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(DataGUI.back.getItemMeta().getDisplayName())||
				   invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.back.getItemMeta().getDisplayName()))
				{
					p.closeInventory();
					CoreGUI.openInterface(p);
				}
				if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.str.getItemMeta().getDisplayName())||
				   invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.ints.getItemMeta().getDisplayName())||
				   invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.agi.getItemMeta().getDisplayName())||
				   invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.luk.getItemMeta().getDisplayName())||
				   invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.con.getItemMeta().getDisplayName())||
				   invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.wis.getItemMeta().getDisplayName()))
				{
					if(PlayerPropertyData.getPlayerData(p).getPoint() == 0)
					{
						p.sendMessage(yaml.getString("Property.Lack"));
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.str.getItemMeta().getDisplayName()))
					{
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1)
						{
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addStr(1);
							p.sendMessage(yaml.getString("Property.Add.Str"));
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.ints.getItemMeta().getDisplayName()))
					{
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1)
						{
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addInt(1);
							p.sendMessage(yaml.getString("Property.Add.Int"));
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.agi.getItemMeta().getDisplayName()))
					{
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1)
						{
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addAgi(1);
							p.sendMessage(yaml.getString("Property.Add.Agi"));
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.luk.getItemMeta().getDisplayName()))
					{
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1)
						{
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addLuk(1);
							p.sendMessage(yaml.getString("Property.Add.Luk"));
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.con.getItemMeta().getDisplayName()))
					{
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1)
						{
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addCon(1);
							p.sendMessage(yaml.getString("Property.Add.Con"));
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
					if(invce.getCurrentItem().getItemMeta().getDisplayName().equals(PropertyGUI.wis.getItemMeta().getDisplayName()))
					{
						if(PlayerPropertyData.getPlayerData(p).getPoint() >= 1)
						{
							PlayerPropertyData.getPlayerData(p).removePoint(1);
							PlayerPropertyData.getPlayerData(p).addWis(1);
							p.sendMessage(yaml.getString("Property.Add.Wis"));
							p.closeInventory();
							PropertyGUI.openInterface(p);
						}
					}
				}
			}else invce.setCancelled(true);
		}
	}
}
