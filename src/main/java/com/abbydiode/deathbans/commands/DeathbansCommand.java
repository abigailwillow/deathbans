package com.abbydiode.deathbans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.abbydiode.deathbans.App;

public class DeathbansCommand implements CommandExecutor {
	private App plugin;
	
	public DeathbansCommand(App plugin) {
		this.plugin = plugin;
		plugin.getCommand("deathbans").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("Deathbans is correctly configured and working!");
		return true;
	}
}
