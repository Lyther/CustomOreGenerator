package org.infury.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.CustomOreGenerator;
import org.infury.config.ConfigFactory;
import org.infury.lang.ConsoleSender;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Player {
	private static HashMap<String, String> playerLevel = CustomOreGenerator.getPlayerLevel();

	public static void setPlayerLevel() {
		FileConfiguration config = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("players.yml"));
		Set<String> players = config.getConfigurationSection("players").getKeys(false);
		for (String player : players) {
			String level = config.getString("players." + player + ".level");
			playerLevel.put(player, level);
		}
	}

	public static void savePlayerLevel(String player, String level) {
		try {
			FileConfiguration config = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("players.yml"));
			config.set("players." + player + ".level", level);
			config.save(ConfigFactory.getFile("players.yml"));
		} catch (IOException ex) {
			ConsoleSender.log("&cERROR: cannot save to file players.yml");
		}
	}

	public static void savePlayerBuy(String player, String level) {
		try {
			FileConfiguration config = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("players.yml"));
			List<String> levels = config.getStringList("players." + player + ".buy");
			levels.add(level);
			config.set("players." + player + ".buy", levels);
			config.save(ConfigFactory.getFile("players.yml"));
		} catch (IOException ex) {
			ConsoleSender.log("&cERROR: cannot save to file players.yml");
		}
	}
}
