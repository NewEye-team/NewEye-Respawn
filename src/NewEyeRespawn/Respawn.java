package NewEyeRespawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import NewEyeRespawn.Plugin.Commands;
import NewEyeRespawn.Plugin.Listeners;

public class Respawn extends JavaPlugin{
	
	static Respawn plugin;
	
	public void onEnable(){
		plugin = this;
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
		this.getCommand("respawn").setExecutor(new Commands());
	}
	
	public static Respawn getPlugin(){
		return plugin;
	}

}
