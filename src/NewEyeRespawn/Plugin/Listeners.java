package NewEyeRespawn.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import NewEyeRespawn.API.Area;
import NewEyeRespawn.API.Respawning;

public class Listeners implements Listener{
	
	@EventHandler
	public static void respawnEvent(PlayerRespawnEvent event){
		Location loc = event.getRespawnLocation();
		Area area = new Area("Home", loc.add(0, 3, 0), 6);
		Respawning respawner = new Respawning(event.getPlayer(), area);
		event.setRespawnLocation(loc.add(0, 5, 0));
		File file = new File("plugins/NewEye/PlayerData/" + event.getPlayer().getUniqueId() + ".yml");
		if (file.exists()){
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
			List<String> areasString = config.getStringList("Respawn.Areas");
			List<Area> areas = new ArrayList<Area>();
			for(String areaString : areasString){
				String[] args = areaString.split("#");
				String name = args[0];
				String[] locString = args[1].split(",");
				int radius = Integer.parseInt(args[2]);
				int X = Integer.parseInt(locString[0]);
				int Y = Integer.parseInt(locString[1]);
				int Z = Integer.parseInt(locString[2]);
				Location loc2 = new Location(Bukkit.getWorld(locString[3]), X, Y, Z);
				Area area2 = new Area(name, loc2, radius);
				areas.add(area2);
			}
			respawner.setGotoAreas(areas);
		}
	}
	
	@EventHandler
	public static void moveEvent(PlayerMoveEvent event){
		Respawning respawner = Respawning.getRespawner(event.getPlayer());
		if (respawner != null){
			Location loc = event.getTo();
			Block ground = loc.getBlock().getRelative(0, -1, 0);
			if (!ground.getType().equals(Material.AIR)){
				respawner.spawn();
			}
			Location loc2 = respawner.getRespawnArea().getLocation();
			if (loc2.distance(loc) > (respawner.getRespawnArea().getRadius()*2)){
				event.getPlayer().sendMessage("Too far out");
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public static void clickEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Respawning respawner = Respawning.getRespawner(player);
		if (respawner != null){
			for(int A = 0; A < respawner.getGotoAreas().size(); A++){
				Area area = respawner.getGotoAreas().get(A);
				if (area.equals(respawner.getRespawnArea())){
					if ((A + 1) == respawner.getGotoAreas().size()){
						Area area2 = respawner.getGotoAreas().get(0);
						respawner.setRespawnArea(area2);
						return;
					}else{
						Area area2 = respawner.getGotoAreas().get(A + 1);
						respawner.setRespawnArea(area2);
						return;
					}
				}else{
					player.sendMessage("Something went wrong: can not find current location");
				}
			}
		}
	}
	
	@EventHandler
	public static void healthDamage(EntityDamageEvent event){
		if (event.getEntity() instanceof Player){
			Respawning respawner = Respawning.getRespawner((Player)event.getEntity());
			if (respawner != null){
				event.setCancelled(true);
			}else if (Respawning.getInvPlayers().contains((Player)event.getEntity())){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public static void hungerEvent(FoodLevelChangeEvent event){
		if (event.getEntity() instanceof Player){
			Respawning respawner = Respawning.getRespawner((Player)event.getEntity());
			if (respawner != null){
				event.setCancelled(true);
			}
		}
	}

}
