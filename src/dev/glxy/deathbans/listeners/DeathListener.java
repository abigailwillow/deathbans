package dev.glxy.deathbans.listeners;

import java.util.Date;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import dev.glxy.deathbans.Main;

public class DeathListener implements Listener {
	private Main plugin;
	
	public DeathListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		String reason = e.getDeathMessage();
		Date expirationDate = new Date(System.currentTimeMillis() + plugin.getConfig().getInt("banLength") * 1000);
		
		if (plugin.getConfig().getBoolean("lightningOnDeath")) {
			player.getWorld().strikeLightningEffect(player.getLocation());
		}
		Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), reason, expirationDate, null);
		player.kickPlayer(reason + "\nYour ban will be lifted on " + expirationDate);
	}
}
