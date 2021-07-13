package com.abbydiode.deathbans.commands;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.BanManager;
import com.abbydiode.deathbans.utils.NameFetcher;

public class BansCommand implements CommandExecutor {
private App plugin;
	
	public BansCommand(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("bans").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		HashMap<String, Long> bans = new BanManager(plugin).getAllBans();
		boolean singular = bans.size() == 1;
		StringBuilder sb = new StringBuilder("§aThere " + (singular ? "is" : "are") + " currently §b" + bans.size() + " §abanned player" + (singular ? "" : "s") + "\n");
		bans.forEach((uniqueId, banTime) -> {
			sb.append("§b" + NameFetcher.getName(uniqueId) + "§r: §6" + new Date(banTime) + "\n");
		});
		sender.sendMessage(sb.toString());
		return false;
	}
}
