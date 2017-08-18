package nx.property.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import nx.property.core.Core;

public class ClientMessages 
{
	static Core main;
	public ClientMessages(Core core){main = core;}
	
	public static YamlConfiguration mC = null;
	public static File mF = null;
	
	// reload
	@SuppressWarnings("deprecation")
	public static void reload()
	{
		if (mF == null) 
			mF = new File(Core.plugin.getDataFolder() + File.separator + "Messages" + File.separator + "zh-tw.yml");
		if (!mF.exists())
		{
			new File(Core.plugin.getDataFolder() + File.separator + "Messages").mkdir();
			Core.plugin.saveResource("Messages/zh-tw.yml", true);
		}
		mC = YamlConfiguration.loadConfiguration(mF);
		
		// =================== 確認檔案是否損失，避免誤刪
	    InputStream is = Core.plugin.getResource("Messages/zh-tw.yml");
		YamlConfiguration defaultYaml = YamlConfiguration.loadConfiguration(is);
	    
	    boolean newMsg = false;
	    for (String key : defaultYaml.getKeys(true)) 
	    {
	    	if (!defaultYaml.isConfigurationSection(key)) 
	    	{
	    		if (mC.getString(key, null) == null)
	    		{
	    			mC.set(key, defaultYaml.getString(key));
	    			newMsg = true;
	    		}
	    	}
	    }
	    if (newMsg) 
	    {
	    	try{
	    		mC.save(mF);
	    	}catch (IOException ex){
	    		ex.printStackTrace();
	    	}
	    }
	}
	
	// getConfig
	public static FileConfiguration getConfig()
	{
		if(mC == null){reload();}
		return mC;
	}
	
	// save
	public static void saveConfig()
	{
		if((mC == null) || (mF == null)){return;}
		try{
			getConfig().save(mF);
		}catch(IOException e){
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + mF, e );
		}
	}
}
