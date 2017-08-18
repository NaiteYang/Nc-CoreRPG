package nx.property.core;

import nx.property.file.ClientDataGUI;
import nx.property.file.ClientMessages;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import nx.property.data.PropertySettings;
import org.bukkit.plugin.java.JavaPlugin;

import nx.property.cmd.AdminCmd;
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
		
		ClientCoreGUI.saveCoreGUI();
		ClientDataGUI.saveDataGUI();
		ClientPropertyGUI.savePropertyGUI();
		
		commands();
		reload();
		events();
		files();
		save();
	}
	
	public void onDisable()
	{
		save();
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
		getCommand("np").setExecutor(new PlayerCmd());
		getCommand("npa").setExecutor(new AdminCmd());
	}
	
	public void events()
	{
		getServer().getPluginManager().registerEvents(new InvClick(), this);
	}
}
