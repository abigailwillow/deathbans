package dev.glxy.deathbans;

import org.bukkit.plugin.java.JavaPlugin;

import dev.glxy.deathbans.commands.DeathbansCommand;
import dev.glxy.deathbans.listeners.DeathListener;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		new DeathListener(this);
		new DeathbansCommand(this);
	}
}
