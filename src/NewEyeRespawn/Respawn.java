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
		getLogger().info("Plugin has been enabled");
	}//onenable
	
	public void onDisable(){
		getLogger().info("Plugin has been disabled");
	}
	
	public static Respawn getPlugin(){
		return plugin;
	}//respawn
	
	public boolean onCommand(CommandSender sender, Command cmd, String args, String[] label){
		if (!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "This command can be used only in-game");
		}
		Player player = (Player) sender; 
			if (cmd.getName().equalsIgnoreCase("nerespawn")){
				if (player.hasPermission("nerespawn.nerespawn")){
					sender.sendMessage(ChatColor.GOLD + "-----" + ChatColor.BLUE + "NewEye-Respawn" + ChatColor.GOLD + "-----");
					sender.sendMessage(ChatColor.BLUE + "Authors: MoseMister and Petrosaurus");
					sender.sendMessage(ChatColor.BLUE + "Version: 0.2");
					sender.sendMessage(ChatColor.GOLD + "------------------------");
				}
			} return false;
		
	}

}//javaplugin
