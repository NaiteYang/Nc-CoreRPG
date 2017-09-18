package nx.multisystem.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

/**
 * Created by User on 2017/8/29.
 */
public class PlayerRegainHealth implements Listener{

	@EventHandler
	public void onPlayerRegainHealth(EntityRegainHealthEvent event){
		if(event.getEntity() instanceof Player){
			event.setCancelled(true);
		}
	}

}
