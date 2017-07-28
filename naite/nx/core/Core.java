package nx.core;

import org.bukkit.plugin.java.JavaPlugin;

import nx.cmd.PlayerCmd;
import nx.event.InvClick;
import nx.file.ClientGUI;
public class Core extends JavaPlugin{
	
	public static Core plugin;
	
	public void onEnable(){
		plugin = this;
		
		getLogger().info("Starting plugin");
		getLogger().info("Plugin version 0.1");
		
		getConfig().options().copyDefaults(true);
		ClientGUI.getClientGUI().options().copyDefaults(true);
		
		getCommand("np").setExecutor(new PlayerCmd());
		
		getServer().getPluginManager().registerEvents(new InvClick(), this);
		
		saveDefaultConfig();
		ClientGUI.saveClientGUI();
	}
	public void onDisable(){
		
		saveDefaultConfig();
		
	}
}
