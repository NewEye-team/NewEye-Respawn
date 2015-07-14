package NewEyeRespawn.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import NewEyeRespawn.Respawn;

public class Respawning {
	
	Player PLAYER;
	Area AREA;
	List<Area> GOTOAREA = new ArrayList<Area>();
	
	static List<Respawning> RESPAWNING = new ArrayList<Respawning>();
	static List<Player> PLAYERS = new ArrayList<Player>();
	
	public Respawning(Player player, Area area){
		PLAYER = player;
		AREA = area;
		GOTOAREA.add(area);
		player.setAllowFlight(true);
		player.setFlying(true);
		RESPAWNING.add(this);
	}
	
	public Player getPlayer(){
		return PLAYER;
	}
	
	public Area getRespawnArea(){
		return AREA;
	}
	
	public List<Area> getGotoAreas(){
		return GOTOAREA;
	}
	
	public void setGotoAreas(List<Area> areas){
		GOTOAREA.addAll(areas);
	}
	
	public void setRespawnArea(Area area){
		AREA = area;
		PLAYER.teleport(area.getLocation());
		PLAYER.sendMessage("Area: " + area);
	}
	
	public void spawn(){
		if ((PLAYER.getGameMode().equals(GameMode.SURVIVAL)) || (PLAYER.getGameMode().equals(GameMode.ADVENTURE))){
			PLAYER.setFlying(false);
			PLAYER.setAllowFlight(false);
		}
		RESPAWNING.remove(this);
		PLAYERS.add(PLAYER);
		Bukkit.getScheduler().runTaskLater(Respawn.getPlugin(), new Runnable(){

			@Override
			public void run() {
				PLAYER.sendMessage("Respawned");
				PLAYERS.remove(PLAYER);
			}
			
		}, 5);
	}

	public static List<Respawning> getRespawners(){
		return RESPAWNING;
	}
	
	public static Respawning getRespawner(Player player){
		for(Respawning respawner : getRespawners()){
			if (respawner.getPlayer().equals(player)){
				return respawner;
			}
		}
		return null;
	}
	
	public static List<Player> getInvPlayers(){
		return PLAYERS;
	}
}
