package com.abbydiode.deathbans;

import org.bukkit.plugin.java.JavaPlugin;

import com.abbydiode.deathbans.commands.BansCommand;
import com.abbydiode.deathbans.commands.CheckBanCommand;
import com.abbydiode.deathbans.commands.DeathbansCommand;
import com.abbydiode.deathbans.commands.UnbanCommand;
import com.abbydiode.deathbans.listeners.AsyncPlayerChatListener;
import com.abbydiode.deathbans.listeners.DeathListener;
import com.abbydiode.deathbans.listeners.PlayerGameModeChangeListener;
import com.abbydiode.deathbans.listeners.PlayerInteractListener;
import com.abbydiode.deathbans.listeners.PlayerJoinListener;
import com.abbydiode.deathbans.listeners.PlayerRespawnListener;
import com.abbydiode.deathbans.listeners.PlayerTeleportListener;
import com.abbydiode.deathbans.listeners.PlayerToggleSneakListener;
import com.abbydiode.deathbans.timers.BanCheckTimer;

public class App extends JavaPlugin {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		new DeathListener(this); // Handle all death related logic
		new AsyncPlayerChatListener(this); // Add *DEAD* prefix to dead players
		new PlayerTeleportListener(this); // Get entity at teleport location to spectate when teleporting
		new PlayerToggleSneakListener(this); // Cancel dismount commands and send to random target instead
		new PlayerGameModeChangeListener(this); // Set player flyspeed to 0 when switching to spectator
		new PlayerRespawnListener(this); // Send player to random target when respawning
		new PlayerInteractListener(this); // Send player to random target when left clicking
		new PlayerJoinListener(this); // Checks if player can be unbanned and sends them to random spectate target
		
		new DeathbansCommand(this); // Command to check if deathbans is properly installed
		new UnbanCommand(this); // Command to unban players with
		new CheckBanCommand(this); // Command to check how long a player is still banned
		new BansCommand(this); // Return a list of all bans in chat
		
		new BanCheckTimer(this); // Checks every minute if player can be unbanned
	}
}
