package nx.multisystem.language;

public class MessageLanguage{

	public static final String PREFIX = "§6[NcMultiSystem]";

	public static final String ADD_STR = "§f[§a+§f] §7您的力量增加 §61 §7點";

	public static final String ADD_INT = "§f[§a+§f] §7您的智力增加 §61 §7點";

	public static final String ADD_AGI = "§f[§a+§f] §7您的敏捷增加 §61 §7點";

	public static final String ADD_CON = "§f[§a+§f] §7您的體質增加 §61 §7點";

	public static final String ADD_LUK = "§f[§a+§f] §7您的幸運增加 §61 §7點";

	public static final String ADD_WIS = "§f[§a+§f] §7您的感知增加 §61 §7點";

	public static final String POINT_LACK = "§c您的屬性點數不足";

	public static final String LEVEL_UP = "§a恭喜您升級了! §e目前等級是§f %level% §e級";

	public static final String NOT_ONLINE = PREFIX + "§c目標不在線上";

	public static final String NOT_PERMISSION = PREFIX  + "§c你沒有權限使用這個指令";

	public static final String INVALID_ARGUMENT = PREFIX + "§c無效的參數";

	public static final String COMMAND_HELP_ADMIN_HP = "§7/propertyadmin health set|add|remove <target> <value>§f- §a設定血量";

	public static final String COMMAND_HELP_ADMIN_MEN = "§7/propertyadmin mentality set|add|remove <target> <value>§f- §a設定精力";

	public static final String COMMAND_HELP_ADMIN_VIT = "§7/propertyadmin vitality set|add|remove <target> <value>§f- §a設定耐力";

	public static final String COMMAND_HELP_ADMIN_EXP = "§7/propertyadmin exp add <target> <value>§f- §a增加經驗";

	public static final String COMMAND_ADMIN_HP_SET = PREFIX + "§a已將 §f%player% §a的血量設定為 §f%value%";

	public static final String COMMAND_ADMIN_MEN_SET = PREFIX + "§a已將 §f%player% §a的精力設定為 §f%value%";

	public static final String COMMAND_ADMIN_VIT_SET = PREFIX + "§a已將 §f%player% §a的耐力設定為 §f%value%";

	public static final String COMMAND_ADMIN_EXP_ADD = PREFIX + "§a已為 §f%player% §a增加 §f%value% §a經驗";

	public static final String COMMAND_HELP_TITLE = PREFIX + "§a指令列表:";

	public static final String ONLY_PLAYER = "§cYou must be a player to use this commmand.";

	public static final String[] COMMAND_HELP_PLAYER = new String[]{
			"§6[NcProperty]§a指令列表:",
			"§7/property §f- §a開啟屬性GUI",
			"§7/property info §f- §a於聊天欄查看自己的屬性資訊",
			"§7/property info <ID> §f- §a查看他人的屬性資訊"
	};

}
