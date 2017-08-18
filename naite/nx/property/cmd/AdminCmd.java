package nx.property.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nx.property.data.PlayerPropertyData;

public class AdminCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("§cYou must be a player to use this commmand.");
			return false;
		}
		Player p = (Player) sender;
		
		if(lable.equalsIgnoreCase("npa"))
		{
			if(args.length == 0)
			{
				p.sendMessage("§cPlease enter the correct of vaule. Enter the command:§6 /npa <value>");
			}
			
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("addpoint"))
				{
					PlayerPropertyData.getPlayerData(p).addPoint(2);
				}
			}
		}
	return false;
	}

}
