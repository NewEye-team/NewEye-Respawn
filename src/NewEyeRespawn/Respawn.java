package NewEyeRespawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import NewEyeRespawn.Plugin.Commands;
import NewEyeRespawn.Plugin.Listeners;

public class Respawn extends JavaPlugin{
	
	static Respawn plugin;
	
	public void onEnable(){
		plugin = this;
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
		this.getCommand("respawn").setExecutor(new Commands());
		Bukkit.getConsoleSender.sendMessage(ChatColor.AQUA + "Plugin has been enabled");
	}//onEnable
	
	public void onDisable(){
		Bukkit.getConsoleSender.sendMessage(ChatColor.AQUA + "Plugin has been disabled");
	}
	
	public static Respawn getPlugin(){
		return plugin;
	}//respawn

}//javaplugin
