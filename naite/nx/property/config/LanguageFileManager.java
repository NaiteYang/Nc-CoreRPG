package nx.property.config;

import nx.property.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * Created by User on 2017/8/20.
 */
public class LanguageFileManager{

	static YamlConfiguration loadFile(File file, String resource){
		if(!file.exists()){
			file.getParentFile().mkdir();
			Core.plugin.saveResource(resource, true);
		}

		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

		InputStream is = Core.plugin.getResource(resource);
		YamlConfiguration defaultYaml = YamlConfiguration.loadConfiguration(is);

		boolean newMsg = false;
		for(String key : defaultYaml.getKeys(true)){
			if(!defaultYaml.isConfigurationSection(key)){
				if(yaml.getString(key, null) == null){
					yaml.set(key, defaultYaml.getString(key));
					newMsg = true;
				}
			}
		}
		if(newMsg){
			try{
				yaml.save(file);
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}

		return yaml;
	}

	static void saveFile(File file, YamlConfiguration yaml){
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + file, e );
		}
	}

}
