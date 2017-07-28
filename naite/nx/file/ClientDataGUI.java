package nx.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nx.core.Core;

public class ClientDataGUI 
{
	static Core main;
	public ClientDataGUI(Core core)
	{
		main = core;
	}
	
	public static YamlConfiguration mC = null;
	public static File mF = null;
	
	// reload
	@SuppressWarnings("deprecation")
	public static void reloadClientDataGUI()
	{
		InputStream defmsg = Bukkit.getPluginManager().getPlugin("NcProperty").getResource("ClientData.yml");
		
		if(mF == null)
		{
			mF = new File(Bukkit.getPluginManager().getPlugin("NcProperty").getDataFolder() + File.separator + "Setting","ClientData.yml");
		}
		mC = YamlConfiguration.loadConfiguration(mF);
		
		if(defmsg != null)
		{
			YamlConfiguration defm = YamlConfiguration.loadConfiguration(defmsg);
			
			if((!mF.exists()) || (mF.length() == 0L))
			{
				mC.setDefaults(defm);
			}
		}
	}
	
	// getConfig
	public static FileConfiguration getClientDataGUI()
	{
		if(mC == null)
		{
			reloadClientDataGUI();
		}
		return mC;
	}
	
	// save
	public static void saveClientDataGUI()
	{
		if((mC == null) || (mF == null))
		{
			return;
		}
		try
		{
			getClientDataGUI().save(mF);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + mF, e );
		}
	}
}
