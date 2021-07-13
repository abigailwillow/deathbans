package com.abbydiode.deathbans.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.BanManager;
import com.abbydiode.deathbans.utils.UUIDFetcher;

public class CheckBanCommand implements CommandExecutor {
private App plugin;
	
	public CheckBanCommand(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("checkban").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		if (args.length > 0) {
			String uniqueId;
			uniqueId = Bukkit.getPlayer(args[0]) != null ? Bukkit.getPlayer(args[0]).getUniqueId().toString() : UUIDFetcher.getUUID(args[0]);
			if (uniqueId != null) {
				long timeLeft = new BanManager(plugin).getBan(uniqueId) - System.currentTimeMillis();
				long hoursLeft = timeLeft / 3600000;
				long minutesLeft = (timeLeft - hoursLeft * 3600000) / 60000;
				sender.sendMessage("§b" + args[0] + "§a " + (timeLeft > 0 ? "is banned for another " + hoursLeft + " hours and " + minutesLeft + " minutes" : 
					"is currently not banned"));
			} else {
				sender.sendMessage("§cPlayer could not be found");
			}
		} else {
			sender.sendMessage("§cYou must specify which player you want to check");
		}
		return false;
	}
}
