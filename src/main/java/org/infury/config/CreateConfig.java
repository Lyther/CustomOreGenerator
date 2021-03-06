package org.infury.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.CustomOreGenerator;
import org.infury.api.Player;
import org.infury.lang.ConsoleSender;
import org.infury.lang.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateConfig {
	private static CustomOreGenerator plugin = CustomOreGenerator.getInstance();

	public static void initial() {
		initialLevels();
		initialConfig();
		initialLang();
		initialPlayers();
	}

	private static void initialLevels() {
		try {
			FileConfiguration levels = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("levels.yml"));
			levels.addDefault("levels.ore.level-1.price", 100);
			levels.addDefault("levels.ore.level-1.blocks.dirt", 50);
			levels.addDefault("levels.ore.level-1.blocks.diamond_ore", 50);
			levels.addDefault("levels.ore.level-1.blocks.stone", 50);
			levels.addDefault("levels.ore.level-2.price", 200);
			levels.addDefault("levels.ore.level-2.blocks.diamond_ore", 100);
			levels.addDefault("levels.other.some-name.price", 100);
			levels.addDefault("levels.other.some-name.blocks.stone", 20);
			levels.addDefault("levels.other.some-name.blocks.glass", 25);
			levels.options().copyDefaults(true);
			levels.save(ConfigFactory.getFile("levels.yml"));
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
			FileConfiguration lang = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("lang.yml"));
			lang.addDefault("set", "Generator level set success!");
			lang.addDefault("remove", "Removed your generator's level.");
			lang.addDefault("no_permission", "You don't have the permission to run this command!");
			lang.addDefault("not_exist", "This group or level doesn't exist!");
			List<String> help = new ArrayList<String>();
			help.add("Usage:");
			help.add("/oregen set <group>.<level> : set your generator's level.");
			help.add("/oregen remove : remove your level and set to default cobblestone generator.");
			help.add("/oregen reload : reload the config file for plugin.");
			lang.addDefault("help", help);
			lang.addDefault("only_player", "This command can only run by a player.");
			lang.addDefault("reload", "Plugin configuration reload success!");
			lang.addDefault("no_such_command", "This command doesn't exist!");
			lang.options().copyDefaults(true);
			lang.save(ConfigFactory.getFile("lang.yml"));
			Message.setMessage();
		} catch (IOException ex) {
			ConsoleSender.log("&cERROR: cannot save to lang.yml");
		}
	}

	private static void initialPlayers() {
		try {
			FileConfiguration players = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("players.yml"));
			players.addDefault("players.a55fff79-11f1-4e7d-abd9-8d822cbe5d1c.level", "ore.level-1");
			List<String> buy = new ArrayList<String>();
			buy.add("ore.level-1");
			buy.add("ore.level-2");
			players.addDefault("players.a55fff79-11f1-4e7d-abd9-8d822cbe5d1c.buy", buy);
			players.options().copyDefaults(true);
			players.save(ConfigFactory.getFile("players.yml"));
			Player.setPlayerLevel();
		} catch (IOException ex) {
			ConsoleSender.log("&cERROR: cannot save to players.yml");
		}
	}
}
