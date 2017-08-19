package nx.property.file;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import nx.property.core.Core;

public class ClientMessages{

	public static YamlConfiguration yaml = null;
	public static File file = null;

	// reload
	public static void reload(){
		if(file == null){
			file = new File(Core.plugin.getDataFolder() + File.separator + "Messages" + File.separator + "zh-tw.yml");
		}
		yaml = LanguageFileManager.loadFile(file, "Messages/zh-tw.yml");
	}

	// getConfig
	public static FileConfiguration getConfig(){
		if(yaml == null){
			reload();
		}
		return yaml;
	}

	// save
	public static void saveConfig(){
		if((yaml == null) || (file == null)){
			return;
		}
		LanguageFileManager.saveFile(file, yaml);
	}
}
