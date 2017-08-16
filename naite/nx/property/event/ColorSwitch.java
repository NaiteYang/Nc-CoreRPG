package nx.property.event;

import java.util.ArrayList;
import java.util.List;

import nx.property.core.Core;
import org.bukkit.configuration.file.YamlConfiguration;

public class ColorSwitch 
{
	static Core main;
	public ColorSwitch(Core core)
	{
		main = core;
	}
	
	public static void replaceColor(YamlConfiguration yml){
		for (String key : yml.getKeys(true)) 
	    {
	      if (!yml.isConfigurationSection(key)) 
	      {
	    	  if (yml.isList(key)){
	    		  List <Object> object = new ArrayList<Object>();
	    		  for (Object x : yml.getList(key)){
	    			  object.add(((String) x).replace("&", "§"));
	    		  }
	    		  yml.set(key, object);
	    	  }else
	    		  yml.set(key, yml.getString(key).replace("&", "§"));
	      }
	      
	    }
	}
}
