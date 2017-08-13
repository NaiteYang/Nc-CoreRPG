package nx.core;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import nx.data.PropertySettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import nx.cmd.PlayerCmd;
import nx.event.InvClick;
import nx.file.ClientCoreGUI;
import nx.file.ClientDataGUI;
import nx.file.ClientPropertyGUI;
import nx.gui.CoreGUI;
import nx.gui.DataGUI;
import nx.gui.PropertyGUI;

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
	}
	
	public static void reload()
	{
		CoreGUI.reload();
		DataGUI.reload();
		PropertyGUI.reload();
		PropertySettings.reload();
	}
}
