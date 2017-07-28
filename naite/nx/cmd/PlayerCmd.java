package nx.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import nx.data.DefaultData;
import nx.gui.Client;

public class PlayerCmd implements CommandExecutor {
	
	public static String title = "§6[NcProperty] ";
	public static DefaultData PD;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
		
		if (!(sender instanceof Player))
		{
			sender.sendMessage("§cYou must be a player to use this commmand.");
			return false;
		}
		
		Player p = (Player) sender;
		
			if(args.length > 0){
				
				if(args[0].equalsIgnoreCase("inv"))
				{
					p.closeInventory();
					p.openInventory(Client.Inv);
				}
				
				//if(args[0].equalsIgnoreCase("get")){
				//	if(args.length == 1){
				//		p.sendMessage(title + "§c請輸入物品代號");
				//	}else if(args.length >= 2){
				//		String a = args[1];
				//		switch(a){
				//		case "pd":
				//			p.getInventory().addItem(InvGUI.playerData);
				//			p.updateInventory();
				//			break;
				//		case "pp":
				//			p.getInventory().addItem(InvGUI.property);
				//			p.updateInventory();
				//			break;
				//		default:
			    //            p.sendMessage("§c無此物品編號");
				//		}
				//	}
				//}
			}else{
				p.sendMessage(title + "§c請輸入有效的參數§f");	
			}
		return false;
	}
}

