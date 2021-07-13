package com.abbydiode.deathbans.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.abbydiode.deathbans.App;

public class AsyncPlayerChatListener implements Listener {
	private App plugin;
	
	public AsyncPlayerChatListener(App plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onAsyncChat(AsyncPlayerChatEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			e.setFormat("*DEAD* " + e.getFormat());
		}
	}
}
