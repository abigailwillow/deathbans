package com.abbydiode.deathbans.timers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.BanManager;
import com.abbydiode.deathbans.utils.Utils;

public class BanCheckTimer {
	private App plugin;
	BukkitScheduler scheduler;
	
	public BanCheckTimer(App plugin) {
		this.plugin = plugin;
		scheduler = Bukkit.getServer().getScheduler();
		
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				BanManager banManager = new BanManager(plugin);
				Bukkit.getOnlinePlayers().forEach(p -> {
					Player player = (Player) p;
					long banTime = banManager.getBan(player.getUniqueId().toString());
					if (banTime > 0) {
						if (System.currentTimeMillis() > banTime) {
							Utils.respawnPlayer(player);
							new BanManager(plugin).removeBan(player);
						}
					}
				});
			}
		}, 1200, 1200);
	}
}
