package nx.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nx.file.ClientDataGUI;

public class ClientData 
{
	// Item
	public static ItemStack playerData;
	
	static
	{
		// Data
		playerData = new ItemStack(Material.SKULL_ITEM ,1 ,(short)3);
		ItemMeta pd = ClientData.playerData.getItemMeta();
		pd.setDisplayName("§6◈ " + ClientDataGUI.getClientDataGUI().getString("Setting.Items.Player.DisplayName") + " §6◈");
	}
	
	// GUI
	public static Inventory Inv = Bukkit.createInventory(null, 54, "§1數據");
	
	static
	{
		
	}
}
