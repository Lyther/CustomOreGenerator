package org.infury.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.infury.CustomOreGenerator;
import org.infury.config.ConfigFactory;
import org.infury.lang.ConsoleSender;

import java.util.HashMap;
import java.util.Set;

public class GenerateOre {
	private static final CustomOreGenerator plugin = CustomOreGenerator.getInstance();
	private Block block;
	private HashMap<String, String> playerLevel = CustomOreGenerator.getPlayerLevel();
	private HashMap<Location, String> blockLevel = CustomOreGenerator.getBlockLevel();

	public GenerateOre(Block block) {
		this.block = block;
	}

	public Boolean generateOre() {
		FileConfiguration levels = YamlConfiguration.loadConfiguration(ConfigFactory.getFile("levels.yml"));
		String currentLevel = getCurrentLevel();
		if (currentLevel == null) return false;
		String currentBlocks = "levels." + currentLevel + ".blocks";
		Set<String> blocks = levels.getConfigurationSection(currentBlocks).getKeys(false);
		double total = 0;
		for (String b : blocks)
			total += levels.getInt(currentBlocks + "." + b);
		while (true) {
			for (String b : blocks) {
				double current = levels.getInt(currentBlocks + "." + b);
				if (Math.random() < current / total) {
					Material type = Material.getMaterial(b.toUpperCase());
					if (type == null) ConsoleSender.log("&cERROR: wrong configuration: " + b + " doesn't exists.");
					else block.setType(type);
					return true;
				}
			}
		}
	}

	private String getCurrentLevel() {
		String level;
		if (blockLevel.containsKey(block.getLocation()) && blockLevel.get(block.getLocation()) != null)
			return blockLevel.get(block.getLocation());
		else {
			Player player = findClosetPlayer();
			if (plugin.getConfig().getBoolean("debug"))
				ConsoleSender.debug("this location isn't stored, found nearby player " + player.getName());
			if (player == null) return null;
			level = playerLevel.get(player.getUniqueId().toString());
			blockLevel.put(block.getLocation(), level);
			return level;
		}
	}

	private Player findClosetPlayer() {
		Player player = null;
		double distance = plugin.getConfig().getInt("search-radius");
		for (Player p : Bukkit.getOnlinePlayers()) {
			double currentDistance = block.getLocation().distance(p.getLocation());
			if (currentDistance < distance) {
				distance = currentDistance;
				player = p;
			}
		}
		return player;
	}
}
