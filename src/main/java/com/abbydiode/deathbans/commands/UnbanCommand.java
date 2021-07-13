package com.abbydiode.deathbans.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.BanManager;
import com.abbydiode.deathbans.utils.Utils;

public class UnbanCommand implements CommandExecutor {
private App plugin;
	
	public UnbanCommand(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("unban").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("galaxy_deathbans.unban")) {			
			if (args.length > 0) {
				Player player = Bukkit.getPlayer(args[0]);
				if (player != null) {
					new BanManager(plugin).removeBan(player);
					Utils.respawnPlayer(player);
					sender.sendMessage("§aYou successfully unbanned §b" + player.getName());
				} else {
					sender.sendMessage("§cPlayer could not be found, they must be online when trying to unban");
				}
			} else {
				sender.sendMessage("§cYou must specify which player you want to unban");
			}
		} else {
			sender.sendMessage("§cYou don't have permission to unban players");
		}
		return false;
	}
}
