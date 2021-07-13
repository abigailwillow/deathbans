package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.Utils;

public class PlayerToggleSneakListener implements Listener {
	private App plugin;
	
	public PlayerToggleSneakListener(App plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (player.getGameMode() == GameMode.SPECTATOR && e.isSneaking() && !player.hasPermission("galaxy_deathbans.override")) {
			e.setCancelled(true);
			Utils.spectateRandomTarget(player);
		}
	}
}
