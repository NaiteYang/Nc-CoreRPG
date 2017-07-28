package nx.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nx.core.Core;

public class ClientGUI 
{
	static Core main;
	public ClientGUI(Core core)
	{
		main = core;
	}
	
	public static YamlConfiguration mC = null;
	public static File mF = null;
	
	// reload
	@SuppressWarnings("deprecation")
	public static void reloadClientGUI()
	{
		InputStream defmsg = Bukkit.getPluginManager().getPlugin("NcProperty").getResource("Client.yml");
		
		if(mF == null)
		{
			mF = new File(Bukkit.getPluginManager().getPlugin("NcProperty").getDataFolder() + File.separator + "Setting","Client.yml");
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
	public static FileConfiguration getClientGUI()
	{
		if(mC == null)
		{
			reloadClientGUI();
		}
		return mC;
	}
	
	// save
	public static void saveClientGUI()
	{
		if((mC == null) || (mF == null))
		{
			return;
		}
		try
		{
			getClientGUI().save(mF);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Can not be saved." + mF, e );
		}
	}
}
