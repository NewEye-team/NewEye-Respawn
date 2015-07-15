package NewEyeRespawn.Plugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import NewEyeRespawn.API.Area;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("respawn") && (sender.hasPermission("nerespawn.respawn"))){
			if (sender instanceof Player){
				Player player = (Player)sender;
				if (args.length == 0){
					//help
					player.sendMessage(ChatColor.AQUA + "/respawn setArea <name>");
				}else{
					if (args[0].equalsIgnoreCase("setArea") && (sender.hasPermission("nerespawn.setarea"))){
						if (args.length >= 2){
							String name = args[1];
							Location loc = player.getLocation().add(0, 2, 0);
							Area area = new Area(name, loc, 4);
							area.save(player);
						}
					}
				}
					
				}
			}
		return false;
	}

}
