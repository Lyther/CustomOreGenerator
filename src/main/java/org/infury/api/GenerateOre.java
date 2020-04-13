package org.infury.api;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.infury.CustomOreGenerator;

import java.util.Set;

public class GenerateOre {
	private static CustomOreGenerator plugin = CustomOreGenerator.getInstance();

	public static void generateOre(Block block) {
		Set<String> blocks = plugin.getConfig().getConfigurationSection("level-settings.level1").getKeys(false);
		double total = 0;
		for (String b : blocks)
			total += plugin.getConfig().getInt("level-settings.level1." + b);
		while (true) {
			for (String b : blocks) {
				double current = plugin.getConfig().getInt("level-settings.level1." + b);
				if (Math.random() < current / total) {
					Material type = Material.getMaterial(b.toUpperCase());
					if (type == null) Console.log("&cERROR: wrong configuration: " + b + " doesn't exists.");
					else block.setType(type);
					return;
				}
			}
		}
	}
}
