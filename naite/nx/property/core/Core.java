package nx.property.core;

import nx.property.data.PlayerPropertyData;
import nx.property.event.PlayerJoinAndQuit;
import nx.property.event.PlayerMove;
import nx.property.event.PlayerRegainHealth;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import nx.property.config.PropertySettings;
import org.bukkit.plugin.java.JavaPlugin;

import nx.property.cmd.PlayerCmd;
import nx.property.event.InvClick;

public class Core extends JavaPlugin{

	public static Core plugin;

	public void onEnable(){
		plugin = this;

		// console
		Server server = getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.YELLOW + "NcProperty v1.0.1 has been enabled");

		commands();
		reload();
		events();
		save();
	}

	public void onDisable(){
		save();
		PlayerPropertyData.removeAllPlayerData();
		Bukkit.getScheduler().cancelTasks(plugin);
	}

	public void reload(){
		getConfig().options().copyDefaults(true);
		PropertySettings.reload();
		PlayerPropertyData.loadOnlinePlayersData();
	}

	public void save(){
		saveDefaultConfig();
	}

	public void commands(){
		CommandExecutor executor = new PlayerCmd();
		getCommand("np").setExecutor(executor);
		getCommand("property").setExecutor(executor);
		getCommand("pro").setExecutor(executor);
	}

	public void events(){
		getServer().getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);
		getServer().getPluginManager().registerEvents(new InvClick(), this);
		getServer().getPluginManager().registerEvents(new PlayerRegainHealth(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
	}
}
