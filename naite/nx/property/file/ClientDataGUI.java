package nx.property.file;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nx.property.core.Core;

public class ClientDataGUI{

	public static YamlConfiguration yaml = null;
	public static File file = null;

	// reload
	public static void reloadDataGUI(){
		if(file == null){
			file = new File(Core.plugin.getDataFolder() + File.separator + "Interface" + File.separator + "datagui.yml");
		}
		yaml = LanguageFileManager.loadFile(file, "Interface/datagui.yml");
	}

	// getConfig
	public static FileConfiguration getDataGUI(){
		if(yaml == null){
			reloadDataGUI();
		}
		return yaml;
	}

	// save
	public static void saveDataGUI(){
		if((yaml == null) || (file == null)){
			return;
		}
		LanguageFileManager.saveFile(file, yaml);
	}
}
