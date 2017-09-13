package nx.property.event;

import nx.property.data.PlayerPropertyData;
import nx.property.config.PropertySettings;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

/**
 * Created by User on 2017/9/3.
 */
public class PlayerMove implements Listener{

	private HashMap<Player, Double> playerLoc = new HashMap<>(); //玩家已移動的距離

	@EventHandler(ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		double distance = playerLoc.containsKey(player) ? playerLoc.get(player) : 0;
		double decreaseDistance = PropertySettings.getVitalityDecreaseWalkDistance();
		Location from = event.getFrom();
		Location to = event.getTo().clone();
		to.setY(from.getY());
		distance += from.distance(to);
		if(distance >= decreaseDistance){ //已經移動了要減少耐力的格數
			distance -= decreaseDistance;
			PlayerPropertyData.getPlayerData(player).removeVitality(PropertySettings.getVitalityDecrease());
		}
		playerLoc.put(player, distance);
	}

}
