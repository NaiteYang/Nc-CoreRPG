package nx.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nx.core.Core;

public class ClientCoreGUI 
{
	static Core main;
	public ClientCoreGUI(Core core){main = core;}
	
	public static YamlConfiguration cC = null;
	public static File cF = null;
	
	// reload
	@SuppressWarnings("deprecation")
	public static void reloadCoreGUI()
	{
		if (cF == null) 
			cF = new File(Core.plugin.getDataFolder() + File.separator + "Interface" + File.separator + "coregui.yml");
		if (!cF.exists())
		{
			new File(Core.plugin.getDataFolder() + File.separator + "Interface").mkdir();
			Core.plugin.saveResource("Interface/coregui.yml", true);
		}
		cC = YamlConfiguration.loadConfiguration(cF);
		
		// =================== 確認檔案是否損失，避免誤刪
	    InputStream is = Core.plugin.getResource("Interface/coregui.yml");
		YamlConfiguration defaultYaml = YamlConfiguration.loadConfiguration(is);
	    
	    boolean newMsg = false;
	    for (String key : defaultYaml.getKeys(true)) 
	    {
	    	if (!defaultYaml.isConfigurationSection(key)) 
	    	{
	    		if (cC.getString(key, null) == null)
	    		{
	    			cC.set(key, defaultYaml.getString(key));
	    			newMsg = true;
	    		}
	    	}
	    }
	    if (newMsg) 
	    {
	    	try{
	    		cC.save(cF);
	    	}catch (IOException ex){
	    		ex.printStackTrace();
	    	}
	    }
	}
	
	// getConfig
	public static FileConfiguration getCoreGUI()
	{
		if(cC == null){reloadCoreGUI();}
		return cC;
	}
	
	// save
	public static void saveCoreGUI()
	{
		if((cC == null) || (cF == null)){return;}
		try{
			getCoreGUI().save(cF);
		}catch(IOException e){
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + cF, e );
		}
	}
}
