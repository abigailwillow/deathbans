package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.Utils;

public class PlayerInteractListener implements Listener {
	private App plugin;

	public PlayerInteractListener(App plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		if (player.getGameMode() == GameMode.SPECTATOR && action == Action.LEFT_CLICK_AIR) {
			Utils.spectateRandomTarget(player);
		}
	}
}
