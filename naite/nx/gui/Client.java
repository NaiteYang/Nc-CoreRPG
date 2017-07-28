package nx.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nx.file.ClientGUI;

public class Client 
{		
	// Item
	public static ItemStack playerData;
	public static ItemStack property;
	public static ItemStack none;
	
	static
	{
		// Data
		playerData = new ItemStack(Material.SKULL_ITEM ,1 ,(short)3);
		ItemMeta pd = Client.playerData.getItemMeta(); 
		pd.setDisplayName("§6◈ " + ClientGUI.getClientGUI().getString("Setting.Items.Data.DisplayName") + " §6◈");
		pd.setLore(ClientGUI.getClientGUI().getStringList("Setting.Items.Data.Lores"));
		Client.playerData.setItemMeta(pd);
		
		// property
		property = new ItemStack(Material.ENDER_PEARL ,1 );
		ItemMeta pp = Client.property.getItemMeta();
		pp.setDisplayName("§6◈ " + ClientGUI.getClientGUI().getString("Setting.Items.Property.DisplayName") + " §6◈");
		pp.setLore(ClientGUI.getClientGUI().getStringList("Setting.Items.Property.Lores"));
		Client.property.setItemMeta(pp);
		
		// none
		none = new ItemStack(Material.STAINED_GLASS_PANE ,1 ,(short)15);
		ItemMeta nn = Client.none.getItemMeta(); 
		nn.setDisplayName(ClientGUI.getClientGUI().getString("Setting.Items.None.DisplayName"));
		nn.setLore(ClientGUI.getClientGUI().getStringList("Setting.Items.None.Lores"));
		Client.none.setItemMeta(nn);
	}
	
	// GUI
	public static Inventory Inv = Bukkit.createInventory(null , 27 , ClientGUI.getClientGUI().getString("Setting.Inventory.Client.GUIName"));
	
	static 
	{
		Inv.setItem(0, none);
		Inv.setItem(1, none);
		Inv.setItem(2, none);
		Inv.setItem(3, none);
		Inv.setItem(4, none);
		Inv.setItem(5, none);
		Inv.setItem(6, none);
		Inv.setItem(7, none);
		Inv.setItem(8, none);
		Inv.setItem(9, none);
		Inv.setItem(10, none);
		Inv.setItem(11, none);
		Inv.setItem(12, playerData);
		Inv.setItem(13, none);
		Inv.setItem(14, property);
		Inv.setItem(15, none);
		Inv.setItem(16, none);
		Inv.setItem(17, none);
		Inv.setItem(18, none);
		Inv.setItem(19, none);
		Inv.setItem(20, none);
		Inv.setItem(21, none);
		Inv.setItem(22, none);
		Inv.setItem(23, none);
		Inv.setItem(24, none);
		Inv.setItem(25, none);
		Inv.setItem(26, none);
	}
}

