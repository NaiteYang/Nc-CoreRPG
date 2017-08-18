package nx.property.core;

import nx.property.data.PlayerPropertyData;
import nx.property.event.PlayerJoinAndQuit;
import nx.property.file.ClientDataGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import nx.property.data.PropertySettings;
import org.bukkit.plugin.java.JavaPlugin;
import nx.property.cmd.PlayerCmd;
import nx.property.event.InvClick;
import nx.property.file.ClientCoreGUI;
import nx.property.file.ClientPropertyGUI;
import nx.property.gui.CoreGUI;
import nx.property.gui.DataGUI;
import nx.property.gui.PropertyGUI;

public class Core extends JavaPlugin{
	
	public static Core plugin;
	
	public void onEnable()
	{
		plugin = this;

		// console
		Server server = getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.YELLOW + "NcProperty v1.0.1 has been enabled");
		
		// files
		getConfig().options().copyDefaults(true);
		ClientCoreGUI.getCoreGUI().options().copyDefaults(true);
		ClientDataGUI.getDataGUI().options().copyDefaults(true);
		ClientPropertyGUI.getPropertyGUI().options().copyDefaults(true);
		
		// commands
		getCommand("np").setExecutor(new PlayerCmd());
		
		// events
		getServer().getPluginManager().registerEvents(new InvClick(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);
		
		// save
		saveDefaultConfig();

		ClientCoreGUI.saveCoreGUI();
		ClientDataGUI.saveDataGUI();
		ClientPropertyGUI.savePropertyGUI();

		reload();
	}
	
	public void onDisable()
	{
		// save
		saveDefaultConfig();
		ClientCoreGUI.saveCoreGUI();
		ClientDataGUI.saveDataGUI();
		ClientPropertyGUI.savePropertyGUI();

		PlayerPropertyData.removePlayerDatas();
	}
	
	public static void reload()
	{
		CoreGUI.reload();
		DataGUI.reload();
		PropertyGUI.reload();
		PropertySettings.reload();
		PlayerPropertyData.loadPlayerDatas();
	}
}
