package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.Utils;

public class PlayerRespawnListener implements Listener {
	private App plugin;
	
	public PlayerRespawnListener(App plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player player = e.getPlayer();
		if (player.getGameMode() == GameMode.SPECTATOR) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					Utils.spectateRandomTarget(player);
				}
			});
		}
	}
}
