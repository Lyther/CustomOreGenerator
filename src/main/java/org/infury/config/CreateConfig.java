package org.infury.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.CustomOreGenerator;
import org.infury.lang.ConsoleSender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateConfig {
	private static CustomOreGenerator plugin = CustomOreGenerator.getInstance();

	public static void initial() {
		initialLevels();
		initialConfig();
		initialLang();
	}

	private static void initialLevels() {
		try {
			FileConfiguration levels = YamlConfiguration.loadConfiguration(getFilePath("levels.yml"));
			levels.addDefault("levels.ore.level-1.dirt", 50);
			levels.addDefault("levels.ore.level-1.diamond_ore", 50);
			levels.addDefault("levels.ore.level-1.stone", 50);
			levels.addDefault("levels.ore.level-2.diamond_ore", 100);
			levels.addDefault("levels.other.some-name.stone", 20);
			levels.addDefault("levels.other.some-name.glass", 25);
			levels.options().copyDefaults(true);
			levels.save(getFilePath("levels.yml"));
		} catch (IOException ex) {
			ConsoleSender.log("&cERROR: cannot save config to levels.yml");
		}
	}

	private static void initialConfig() {
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

	private static void initialLang() {
		try {
			FileConfiguration lang = YamlConfiguration.loadConfiguration(getFilePath("lang.yml"));
			lang.addDefault("set", "Generator level set success!");
			lang.addDefault("remove", "Removed your generator's level.");
			lang.addDefault("no_permission", "You don't have the permission to run this command!");
			lang.addDefault("not_exist", "This level doesn't exist!");
			List<String> help = new ArrayList<String>();
			help.add("Usage:");
			help.add("/oregen set <group>.<level> : set your generator's level.");
			help.add("/oregen remove : remove your level and set to default cobblestone generator.");
			help.add("/oregen reload : reload the config file for plugin.");
			lang.addDefault("help", help);
			lang.addDefault("only_player", "This command can only run by a player.");
			lang.addDefault("reload", "Plugin configuration reload success!");
			lang.options().copyDefaults(true);
			lang.save(getFilePath("lang.yml"));
		} catch (IOException ex) {
			ConsoleSender.log("&cERROR: cannot save to lang.yml");
		}
	}

	private static File getFilePath(String name) {
		return new File("./plugins/CustomOreGenerator/" + name);
	}
}
