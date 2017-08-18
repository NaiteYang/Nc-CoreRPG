package nx.property.cmd;

import nx.property.data.PlayerPropertyData;
import nx.property.gui.CoreGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCmd implements CommandExecutor {
	
	public static PlayerPropertyData PD;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
		
		if (!(sender instanceof Player))
		{
			sender.sendMessage("§cYou must be a player to use this commmand.");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(lable.equalsIgnoreCase("np"))
		{
			if(args.length > 0)
			{
				p.sendMessage("§cPlease enter the correct of vaule. Enter the command:§6 /np");
			}else{
				p.closeInventory();
				CoreGUI.openInterface(p);
			}
		}
	return false;
	}
}

