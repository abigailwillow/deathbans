package com.abbydiode.deathbans.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Utils {
	private static Random random = new Random();
	
	public static void setSpectatorTarget(Player player, Player target) {
		if (target != null) {
			if (player.getSpectatorTarget() != target) {
				player.setSpectatorTarget(target);
				player.sendMessage("§aYou are now spectating §b" + target.getName());
			}
		} else {
			player.sendMessage("§cCould not find specified player.");
		}
	}
	
	public static void spectateRandomTarget(Player player) {
		player.setSpectatorTarget(null);
		List<Object> players = new LinkedList<>(Arrays.asList(Bukkit.getOnlinePlayers().toArray()));
		players.removeIf(p -> ((Player) p).getGameMode() != GameMode.SURVIVAL);
		if (players.size() > 0) {
			setSpectatorTarget(player, (Player) players.get(random.nextInt(players.size())));
		} else {
			player.sendMessage("§cThere are currently no players online that can be spectated");
		}
	}
	
	public static void respawnPlayer(Player player) {
		if (player.getGameMode() == GameMode.SPECTATOR) {
			Location spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
			player.getServer().broadcastMessage("§b" + player.getName() + " §awas just respawned");
			player.playSound(spawn, Sound.ENTITY_ARROW_HIT_PLAYER, 2, 1);
			player.setGameMode(GameMode.SURVIVAL);
			player.teleport(spawn);
		}
	}
}
