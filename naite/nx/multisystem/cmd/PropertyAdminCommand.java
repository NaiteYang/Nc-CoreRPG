package nx.multisystem.cmd;

import nx.multisystem.data.PlayerPropertyData;
import nx.multisystem.language.MessageLanguage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PropertyAdminCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 4){ // propertyadmin health|hp|mana|mp|mentality|men|vitality|vit|exp set|add|remove <player> <value>
			if(!sender.hasPermission("NcProperty.set")){
				sender.sendMessage(MessageLanguage.NOT_PERMISSION);
				return true;
			}
			String option = args[0];
			String action = args[1];
			Player target = Bukkit.getPlayer(args[2]);
			if(!target.isOnline()){ //目標不在線
				sender.sendMessage(MessageLanguage.NOT_ONLINE);
				return true;
			}
			int value;
			try{
				value = Integer.valueOf(args[3]);
			}
			catch(Exception ex){ //輸入的不是數字
				sender.sendMessage(MessageLanguage.INVALID_ARGUMENT);
				return true;
			}
			if(value < 0){ //輸入的數值小於0
				sender.sendMessage(MessageLanguage.INVALID_ARGUMENT);
				return true;
			}
			PlayerPropertyData data = PlayerPropertyData.getPlayerData(target);

			if(option.equalsIgnoreCase("health") || option.equalsIgnoreCase("hp")){
				if(action.equalsIgnoreCase("set")){
					data.setHealth(value);
				}
				else if(action.equalsIgnoreCase("add")){
					data.addHealth(value);
				}
				else if(action.equalsIgnoreCase("remove")){
					data.removeHealth(value);
				}
				else{
					sender.sendMessage(MessageLanguage.COMMAND_HELP_ADMIN_HP);
					return true;
				}
				sender.sendMessage(MessageLanguage.COMMAND_ADMIN_HP_SET.replaceAll("%player%", target.getName()).replaceAll("%value%", String.valueOf(data.getHealth())));
				return true;
			}
			else if(option.equalsIgnoreCase("mana") || option.equalsIgnoreCase("mp")){
				if(action.equalsIgnoreCase("set")){
					data.setMana(value);
				}
				else if(action.equalsIgnoreCase("add")){
					data.addMana(value);
				}
				else if(action.equalsIgnoreCase("remove")){
					data.removeMana(value);
				}
				else{
					sender.sendMessage(MessageLanguage.COMMAND_HELP_ADMIN_MP);
					return true;
				}
				sender.sendMessage(MessageLanguage.COMMAND_ADMIN_MP_SET.replaceAll("%player%", target.getName()).replaceAll("%value%", String.valueOf(data.getMana())));
				return true;
			}
			else if(option.equalsIgnoreCase("mentality") || option.equalsIgnoreCase("men")){
				if(action.equalsIgnoreCase("set")){
					data.setMentality(value);
				}
				else if(action.equalsIgnoreCase("add")){
					data.addMentality(value);
				}
				else if(action.equalsIgnoreCase("remove")){
					data.removeMentality(value);
				}
				else{
					sender.sendMessage(MessageLanguage.COMMAND_HELP_ADMIN_MEN);
					return true;
				}
				sender.sendMessage(MessageLanguage.COMMAND_ADMIN_MEN_SET.replaceAll("%player%", target.getName()).replaceAll("%value%", String.valueOf(data.getMentality())));
				return true;
			}
			else if(option.equalsIgnoreCase("vitality") || option.equalsIgnoreCase("vit")){
				if(action.equalsIgnoreCase("set")){
					data.setVitality(value);
				}
				else if(action.equalsIgnoreCase("add")){
					data.addVitality(value);
				}
				else if(action.equalsIgnoreCase("remove")){
					data.removeVitality(value);
				}
				else{
					sender.sendMessage(MessageLanguage.COMMAND_HELP_ADMIN_VIT);
					return true;
				}
				sender.sendMessage(MessageLanguage.COMMAND_ADMIN_VIT_SET.replaceAll("%player%", target.getName()).replaceAll("%value%", String.valueOf(data.getVitality())));
				return true;
			}
			else if(option.equalsIgnoreCase("exp") && action.equalsIgnoreCase("add")){
				data.addExp(value);
				sender.sendMessage(MessageLanguage.COMMAND_ADMIN_EXP_ADD.replaceAll("%player%", target.getName()).replaceAll("%value%", String.valueOf(value)));
				return true;
			}
		}
		sender.sendMessage(new String[]{
				MessageLanguage.COMMAND_HELP_TITLE,
				MessageLanguage.COMMAND_HELP_ADMIN_HP,
				MessageLanguage.COMMAND_HELP_ADMIN_MP,
				MessageLanguage.COMMAND_HELP_ADMIN_MEN,
				MessageLanguage.COMMAND_HELP_ADMIN_VIT,
				MessageLanguage.COMMAND_HELP_ADMIN_EXP
		});

		return true;
	}
}
