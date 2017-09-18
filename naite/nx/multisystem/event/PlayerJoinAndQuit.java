package nx.multisystem.event;

import nx.multisystem.data.PlayerPropertyData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by User on 2017/8/18.
 */
public class PlayerJoinAndQuit implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		PlayerPropertyData.getPlayerData(event.getPlayer());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		PlayerPropertyData.removePlayerData(event.getPlayer());
	}

}
