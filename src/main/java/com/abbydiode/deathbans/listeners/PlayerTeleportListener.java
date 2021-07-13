package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.Utils;

public class PlayerTeleportListener implements Listener {
	private App plugin;
	
	public PlayerTeleportListener(App plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		if (e.getCause() == TeleportCause.SPECTATE) {
			Player player = e.getPlayer();
			try {
				Player target = (Player) player.getWorld().getNearbyEntities(e.getTo(), 1, 1, 1,
						entity -> entity.getType() == EntityType.PLAYER && entity != player).toArray()[0];
				Utils.setSpectatorTarget(player, target);
			} catch (Exception exception) {
				plugin.getLogger().warning(exception.getMessage());
			}
		}
	}
}
