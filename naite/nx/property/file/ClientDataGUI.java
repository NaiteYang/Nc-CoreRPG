package nx.property.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nx.property.core.Core;

public class ClientDataGUI 
{
	static Core main;
	public ClientDataGUI(Core core){main = core;}
	
	public static YamlConfiguration dC = null;
	public static File dF = null;
	
	// reload
	@SuppressWarnings("deprecation")
	public static void reloadDataGUI()
	{
		if (dF == null) 
			dF = new File(Core.plugin.getDataFolder() + File.separator + "Interface" + File.separator + "datagui.yml");
		if (!dF.exists())
		{
			new File(Core.plugin.getDataFolder() + File.separator + "Interface").mkdir();
			Core.plugin.saveResource("Interface/datagui.yml", true);
		}
		dC = YamlConfiguration.loadConfiguration(dF);
		
		// =================== 確認檔案是否損失，避免誤刪
	    InputStream is = Core.plugin.getResource("Interface/datagui.yml");
		YamlConfiguration defaultYaml = YamlConfiguration.loadConfiguration(is);
	    
	    boolean newMsg = false;
	    for (String key : defaultYaml.getKeys(true)) 
	    {
	    	if (!defaultYaml.isConfigurationSection(key)) 
	    	{
	    		if (dC.getString(key, null) == null)
	    		{
	    			dC.set(key, defaultYaml.getString(key));
	    			newMsg = true;
	    		}
	    	}
	    }
	    if (newMsg) 
	    {
	    	try{
	    		dC.save(dF);
	    	}catch (IOException ex){
	    		ex.printStackTrace();
	    	}
	    }
	}
	
	// getConfig
	public static FileConfiguration getDataGUI()
	{
		if(dC == null){reloadDataGUI();}
		return dC;
	}
	
	// save
	public static void saveDataGUI()
	{
		if((dC == null) || (dF == null)){return;}
		try{
			getDataGUI().save(dF);
		}catch(IOException e){
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + dF, e );
		}
	}
}
