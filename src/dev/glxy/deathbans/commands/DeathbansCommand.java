package dev.glxy.deathbans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.glxy.deathbans.Main;

public class DeathbansCommand implements CommandExecutor {
	private Main plugin;
	
	public DeathbansCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("deathbans").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("Deathbans is correctly configured and working!");
		return true;
	}
}
