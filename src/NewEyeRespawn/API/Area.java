package NewEyeRespawn.API;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

public class Area {
	
	String NAME;
	Location PINPOINT;
	int RADIUS;
	
	public Area(String name, Location loc, int radius){
		NAME = name;
		PINPOINT = loc;
		RADIUS = radius;
	}
	
	public String getLocationName(){
		return NAME;
	}
	
	public Location getLocation(){
		return PINPOINT;
	}
	
	public int getRadius(){
		return RADIUS;
	}
	
	public void save(OfflinePlayer player){
		File file = new File("plugins/NewEye/PlayerData/" + player.getUniqueId() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		List<String> areas = config.getStringList("Respawn.Areas");
		areas.add(NAME + "#" + PINPOINT.getBlockX() + "," + PINPOINT.getBlockY() + "," + PINPOINT.getBlockZ() + "," + PINPOINT.getWorld().getName() + "#" + RADIUS);
		config.set("Respawn.Areas", areas);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
