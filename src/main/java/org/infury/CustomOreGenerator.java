package org.infury;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.infury.config.CreateConfig;
import org.infury.events.BlockGenEvent;

public class CustomOreGenerator extends JavaPlugin implements Listener {
	private static CustomOreGenerator plugin;

	@Override
	public void onEnable() {
		plugin = this;
		CreateConfig.initial();
		getServer().getPluginManager().registerEvents(new BlockGenEvent(), this);
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	public static CustomOreGenerator getInstance() {
		return plugin;
	}
}
