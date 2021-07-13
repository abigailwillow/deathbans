package com.abbydiode.deathbans.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.abbydiode.deathbans.App;
import com.abbydiode.deathbans.utils.BanManager;

public class DeathListener implements Listener {
	private App plugin;
	
	public DeathListener(App plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		String reason = e.getDeathMessage();
		Date expirationDate = new Date(System.currentTimeMillis() + plugin.getConfig().getInt("banLength") * 1000);
		String readableDate = new SimpleDateFormat("YYYY-MM-dd 'at' kk:mm '('z')'").format(expirationDate);
		
		if (plugin.getConfig().getBoolean("lightningOnDeath")) {
			player.getWorld().strikeLightningEffect(player.getLocation());
		}
		
		if (plugin.getConfig().getBoolean("stormOnDeath")) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather thunder");
		}
		
		player.setGameMode(GameMode.SPECTATOR);
		player.sendTitle("", "§c" + reason, 10, 200, 10);
		player.sendMessage("§6You will be respawned on " + readableDate);
		new BanManager(plugin).addBan(player);
	}
}
