package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.BanManager;
import com.abbydiode.deathbans.utils.Utils;

public class PlayerJoinListener implements Listener {
	private App plugin;
	
	public PlayerJoinListener(App plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {	
		Player player = e.getPlayer();
		long banTime = new BanManager(plugin).getBan(player.getUniqueId().toString());
		if (banTime > 0) {
			if (System.currentTimeMillis() > banTime) {
				Utils.respawnPlayer(player);
				new BanManager(plugin).removeBan(player);
			} else {
				long timeLeft = banTime - System.currentTimeMillis();
				long hoursLeft = timeLeft / 3600000;
				long minutesLeft = (timeLeft - hoursLeft * 3600000) / 60000;
				player.sendMessage("§6You will be respawned in " + hoursLeft + " hours and " + minutesLeft + " minutes");
			}
		}
		
		if (player.getGameMode() == GameMode.SPECTATOR && !player.hasPermission("galaxy_deathbans.override")) {
			Utils.spectateRandomTarget(player);
		}
	}
}
