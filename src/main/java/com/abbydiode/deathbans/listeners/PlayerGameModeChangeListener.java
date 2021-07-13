package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.Utils;

public class PlayerGameModeChangeListener implements Listener {
	private App plugin;
	
	public PlayerGameModeChangeListener(App plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent e) {
		Player player = e.getPlayer();
		if (e.getNewGameMode() == GameMode.SPECTATOR) {
			if (!player.hasPermission("galaxy_deathbans.override")) {					
				player.setFlySpeed(0);
			} else {
				player.sendMessage("§cYou can still freeroam because you're an admin.");
			}
			player.sendMessage("§aPress any number to start spectating.");
		} else {
			player.setFlySpeed(0.1f);
		}
	}
}
