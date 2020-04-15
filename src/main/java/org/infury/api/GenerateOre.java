package org.infury.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.infury.CustomOreGenerator;
import org.infury.lang.ConsoleSender;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class GenerateOre {
	private static final CustomOreGenerator plugin = CustomOreGenerator.getInstance();
	private Block block;
	private HashMap<Player, String> playerLevel = CustomOreGenerator.getPlayerLevel();
	private HashMap<Location, String> blockLevel = CustomOreGenerator.getBlockLevel();

	public GenerateOre(Block block) {
		this.block = block;
	}

	public Boolean generateOre() {
		FileConfiguration levels = YamlConfiguration.loadConfiguration(new File("./plugins/CustomOreGenerator/levels.yml"));
		String currentLevel = getCurrentLevel();
		if (currentLevel == null) return false;
		currentLevel = "levels." + currentLevel;
		Set<String> blocks = levels.getConfigurationSection(currentLevel).getKeys(false);
		double total = 0;
		for (String b : blocks)
			total += levels.getInt(currentLevel + "." + b);
		while (true) {
			for (String b : blocks) {
				double current = levels.getInt(currentLevel + "." + b);
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
			if (plugin.getConfig().getBoolean("debug")) ConsoleSender.log("Debug: find player " + player.getName());
			if (player == null) return null;
			level = playerLevel.get(player);
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
