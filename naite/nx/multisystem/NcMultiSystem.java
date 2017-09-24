package nx.multisystem;

import nx.multisystem.cmd.PropertyAdminCommand;
import nx.multisystem.data.PlayerPropertyData;
import nx.multisystem.event.PlayerJoinAndQuit;
import nx.multisystem.event.PlayerMove;
import nx.multisystem.event.PlayerRegainHealth;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import nx.multisystem.config.PropertySettings;
import org.bukkit.plugin.java.JavaPlugin;

import nx.multisystem.cmd.PropertyPlayerCommand;
import nx.multisystem.event.InvClick;

public class NcMultiSystem extends JavaPlugin{

	public static NcMultiSystem plugin;

	public void onEnable(){
		plugin = this;

		// console
		Server server = getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.YELLOW + "NcMultiSystem v1.0.1 has been enabled");

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
		CommandExecutor playerCommandExecutor = new PropertyPlayerCommand();
		getCommand("np").setExecutor(playerCommandExecutor);
		getCommand("property").setExecutor(playerCommandExecutor);
		getCommand("pro").setExecutor(playerCommandExecutor);

		CommandExecutor adminCommandExecutor = new PropertyAdminCommand();
		getCommand("npadmin").setExecutor(adminCommandExecutor);
		getCommand("propertyadmin").setExecutor(adminCommandExecutor);
		getCommand("proadmin").setExecutor(adminCommandExecutor);
	}

	public void events(){
		getServer().getPluginManager().registerEvents(new PlayerJoinAndQuit(), this);
		getServer().getPluginManager().registerEvents(new InvClick(), this);
		getServer().getPluginManager().registerEvents(new PlayerRegainHealth(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
	}
}
