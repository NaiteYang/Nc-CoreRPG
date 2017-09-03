package nx.property.core;

import nx.property.data.PlayerPropertyData;
import nx.property.event.PlayerJoinAndQuit;
import nx.property.event.PlayerMove;
import nx.property.event.PlayerRegainHealth;
import nx.property.file.ClientDataGUI;
import nx.property.file.ClientMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
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
		
		commands();
		reload();
		events();
		files();
		save();
	}
	
	public void onDisable()
	{
		save();
		PlayerPropertyData.removeAllPlayerData();
		Bukkit.getScheduler().cancelTasks(plugin);
	}
	
	public void reload()
	{
		CoreGUI.reload();
		DataGUI.reload();
		PropertyGUI.reload();
		ClientCoreGUI.reloadCoreGUI();
		ClientDataGUI.reloadDataGUI();
		ClientPropertyGUI.reloadPropertyGUI();
		PropertySettings.reload();
		InvClick.reload();
		PlayerPropertyData.loadOnlinePlayersData();
	}
	
	public void files()
	{
		getConfig().options().copyDefaults(true);
		ClientCoreGUI.getCoreGUI().options().copyDefaults(true);
		ClientDataGUI.getDataGUI().options().copyDefaults(true);
		ClientPropertyGUI.getPropertyGUI().options().copyDefaults(true);
		ClientMessages.getConfig().options().copyDefaults(true);
	}
	
	public void save()
	{
		saveDefaultConfig();
		ClientCoreGUI.saveCoreGUI();
		ClientDataGUI.saveDataGUI();
		ClientPropertyGUI.savePropertyGUI();
	}
	
	public void commands()
	{
		CommandExecutor executor = new PlayerCmd();
		getCommand("np").setExecutor(executor);
		getCommand("property").setExecutor(executor);
		getCommand("pro").setExecutor(executor);
	}
	
	public void events()
	{
		getServer().getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);
		getServer().getPluginManager().registerEvents(new InvClick(), this);
		getServer().getPluginManager().registerEvents(new PlayerRegainHealth(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
	}
}
