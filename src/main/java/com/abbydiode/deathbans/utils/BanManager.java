package com.abbydiode.deathbans.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.abbydiode.deathbans.App;

public class BanManager {
	private App plugin;
	private YamlConfiguration banList;
	File banListFile;
	
	
	public BanManager(App plugin) {
		this.plugin = plugin;
		banListFile = new File(plugin.getDataFolder(), "bans.yml");
		banList = YamlConfiguration.loadConfiguration(banListFile);
	}
	
	public void addBan(Player player) {
		String uniqueId = player.getUniqueId().toString();
		long banDuration = System.currentTimeMillis() + plugin.getConfig().getInt("banLength") * 1000;
		banList.set(uniqueId, banDuration);
		try {
			banList.save(banListFile);
		} catch (IOException exception) {
			plugin.getLogger().warning(exception.getMessage());
		}
	}
	
	public long getBan(String uniqueId) {
		long banDuration = banList.getLong(uniqueId);
		return banDuration;
	}
	
	public void removeBan(Player player) {
		String uniqueId = player.getUniqueId().toString();
		banList.set(uniqueId, null);
		try {
			banList.save(banListFile);
		} catch (IOException exception) {
			plugin.getLogger().warning(exception.getMessage());
		}
	}
	
	public HashMap<String, Long> getAllBans() {
		HashMap<String, Long> bans = new HashMap<String, Long>();
		banList.getKeys(false).forEach(uniqueId -> {
			bans.put(uniqueId, banList.getLong(uniqueId));
		});
		return bans;
	}
}
