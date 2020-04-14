package org.infury;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.infury.commands.PlayerCommand;
import org.infury.config.CreateConfig;
import org.infury.events.BlockListener;

import java.util.HashMap;

public class CustomOreGenerator extends JavaPlugin implements Listener {
	private static CustomOreGenerator plugin;
	private static HashMap<Player, String> playerLevel = new HashMap<Player, String>();
	private static HashMap<Location, String> blockLevel = new HashMap<Location, String>();

	@Override
	public void onEnable() {
		plugin = this;
		CreateConfig.initial();
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
		getCommand("oregen").setExecutor(new PlayerCommand());
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public static CustomOreGenerator getInstance() {
		return plugin;
	}


	public static HashMap<Player, String> getPlayerLevel() {
		return playerLevel;
	}

	public static HashMap<Location, String> getBlockLevel() {
		return blockLevel;
	}
}
