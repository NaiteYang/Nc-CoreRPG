package nx.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nx.core.Core;

public class ClientPropertyGUI
{
	static Core main;
	public ClientPropertyGUI(Core core){main = core;}
	
	public static YamlConfiguration pC = null;
	public static File pF = null;
	
	// reload
	@SuppressWarnings("deprecation")
	public static void reloadPropertyGUI()
	{
		if (pF == null) 
			pF = new File(Core.plugin.getDataFolder() + File.separator + "Interface" + File.separator + "propertygui.yml");
		if (!pF.exists())
		{
			new File(Core.plugin.getDataFolder() + File.separator + "Interface").mkdir();
			Core.plugin.saveResource("Interface/propertygui.yml", true);
		}
		pC = YamlConfiguration.loadConfiguration(pF);
		
		// =================== 確認檔案是否損失，避免誤刪
	    InputStream is = Core.plugin.getResource("Interface/propertygui.yml");
		YamlConfiguration defaultYaml = YamlConfiguration.loadConfiguration(is);
	    
	    boolean newMsg = false;
	    for (String key : defaultYaml.getKeys(true)) 
	    {
	      if (!defaultYaml.isConfigurationSection(key)) 
	      {
	        if (pC.getString(key, null) == null)
	        {
	          pC.set(key, defaultYaml.getString(key));
	          newMsg = true;
	        }
	      }
	    }
	    if (newMsg) 
	    {
	      try{
	        pC.save(pF);
	      }catch (IOException ex){
	        ex.printStackTrace();
	      }
	    }
	}
	
	// getConfig
	public static FileConfiguration getPropertyGUI()
	{
		if(pC == null){reloadPropertyGUI();}
		return pC;
	}
	
	// save
	public static void savePropertyGUI()
	{
		if((pC == null) || (pF == null)){return;}
		try{
			getPropertyGUI().save(pF);
		}catch(IOException e){
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + pF, e );
		}
	}
}
