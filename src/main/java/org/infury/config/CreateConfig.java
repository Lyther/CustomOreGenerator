package org.infury.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.CustomOreGenerator;
import org.infury.api.Console;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateConfig {
	private static CustomOreGenerator plugin = CustomOreGenerator.getInstance();

	public static void initial() {
		initial_levels();
		initial_config();
	}

	private static void initial_levels() {
		try {
			FileConfiguration levels = YamlConfiguration.loadConfiguration(new File("./plugins/CustomOreGenerator/levels.yml"));
			levels.addDefault("levels.ore.level-1.dirt", 50);
			levels.addDefault("levels.ore.level-1.diamond_ore", 50);
			levels.addDefault("levels.ore.level-1.stone", 50);
			levels.addDefault("levels.ore.level-2.diamond_ore", 100);
			levels.addDefault("levels.other.some-name.stone", 20);
			levels.addDefault("levels.other.some-name.glass", 25);
			levels.options().copyDefaults(true);
			levels.save(new File("./plugins/CustomOreGenerator/levels.yml"));
		} catch (IOException ex) {
			Console.log("&cERROR: cannot save config to levels.yml");
		}
	}

	private static void initial_config() {
		FileConfiguration config = plugin.getConfig();
		config.addDefault("debug", true);
		List<String> worlds = new ArrayList<String>();
		worlds.add("world");
		worlds.add("skyblock");
		worlds.add("other-world");
		config.addDefault("enable-worlds", worlds);
		config.addDefault("search-radius", 12);
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}
}
