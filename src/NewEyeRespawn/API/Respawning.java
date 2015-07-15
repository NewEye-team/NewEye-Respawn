package NewEyeRespawn.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import NewEyeRespawn.Respawn;

public class Respawning {
	
	Player PLAYER;
	Area AREA;
	List<Area> GOTOAREA = new ArrayList<Area>();
	
	public static List<Respawning> RESPAWNING = new ArrayList<Respawning>();
	static List<Player> PLAYERS = new ArrayList<Player>();
	
	
	@SuppressWarnings({ "unused" })
	public Respawning(Player player, Area area){
		PLAYER = player;
		AREA = area;
		GOTOAREA.add(area);
		player.setAllowFlight(true);
		player.setFlying(true);
		RESPAWNING.add(this);
		 PlayerInventory inventory = player.getInventory();
         ItemStack itemstack = new ItemStack(arrow());
         inventory.addItem(new ItemStack[] { arrow() });
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
		PLAYER.sendMessage(ChatColor.GREEN + "Area: " + area);
	}
	
	public void spawn(){
		if ((PLAYER.getGameMode().equals(GameMode.SURVIVAL)) || (PLAYER.getGameMode().equals(GameMode.ADVENTURE))){
			PLAYER.setFlying(false);
			PLAYER.setAllowFlight(false);
			PlayerInventory inventory = PLAYER.getInventory();
			inventory.removeItem(arrow());
		}
		RESPAWNING.remove(this);
		PLAYERS.add(PLAYER);
		Bukkit.getScheduler().runTaskLater(Respawn.getPlugin(), new Runnable(){

			@Override
			public void run() {
				PLAYER.sendMessage(ChatColor.GREEN + "Respawned");
				Location loc = PLAYER.getLocation();
				PLAYER.playSound(loc, Sound.LEVEL_UP, 1.0F, 0.0F);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ItemStack arrow()
	  {
	    ItemStack d = new ItemStack(Material.ARROW, 1);
	    ItemMeta m = d.getItemMeta();
	    m.setDisplayName(ChatColor.AQUA + "Fall on the ground and you will be respawned");
	    List<String> s = new ArrayList();
	    s.add(ChatColor.DARK_PURPLE + "Fall on the ground and you will be respawned");
	    m.setLore(s);
	    d.setItemMeta(m);
	    return d;
}
	

	}
