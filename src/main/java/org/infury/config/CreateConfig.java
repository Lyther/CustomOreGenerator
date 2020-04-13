package org.infury.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.infury.CustomOreGenerator;

public class CreateConfig {
	private static CustomOreGenerator plugin = CustomOreGenerator.getInstance();

	public static void initial() {
		FileConfiguration config = plugin.getConfig();
		config.addDefault("level-settings.level1.dirt", 50);
		config.addDefault("level-settings.level1.diamond_ore", 50);
		config.addDefault("level-settings.level1.stone", 50);
		config.options().copyDefaults(true);
		plugin.saveDefaultConfig();
		plugin.saveConfig();
	}
}
