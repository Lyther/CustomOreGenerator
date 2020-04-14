package org.infury.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.infury.CustomOreGenerator;
import org.infury.api.Console;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PlayerCommand implements CommandExecutor {
	private CustomOreGenerator plugin = CustomOreGenerator.getInstance();
	private HashMap<Player, String> playerLevel = CustomOreGenerator.getPlayerLevel();
	private HashMap<Location, String> blockLevel = CustomOreGenerator.getBlockLevel();
	private List<String> validLevels = getValidLevels();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Console.log("This command can only be executed by players!");
			return false;
		}
		if (args.length < 1) {
			sender.sendMessage("Usage:");
			sender.sendMessage("oregen set <level>: set your ore generator's level.");
			sender.sendMessage("oregen remove: clear your ore generator settings.");
			return true;
		}
		if (args[0].equals("set")) {
			if (!validLevels.contains(args[1])) {
				sender.sendMessage("The level you choose doesn't exist!");
				return false;
			} else if (!sender.hasPermission("oregen.set." + args[1])) {
				sender.sendMessage("You don't have permission to run this command!");
				return false;
			}
			playerLevel.put(((Player) sender).getPlayer(), args[1]);
			blockLevel.clear();
			sender.sendMessage("Ore generator level set success!");
			return true;
		} else if (args[0].equals("remove")) {
			playerLevel.remove(((Player) sender).getPlayer());
			blockLevel.clear();
			sender.sendMessage("You have cancelled your ore generator level.");
			return true;
		}
		return false;
	}

	private List<String> getValidLevels() {
		List<String> results = new ArrayList<String>();
		FileConfiguration levelsConfig = YamlConfiguration.loadConfiguration(new File("./plugins/CustomOreGenerator/levels.yml"));
		Set<String> groups = levelsConfig.getConfigurationSection("levels").getKeys(false);
		for (String group : groups) {
			Set<String> levels = levelsConfig.getConfigurationSection("levels." + group).getKeys(false);
			for (String level : levels) {
				String result = group + "." + level;
				results.add(result);
			}
		}
		if (plugin.getConfig().getBoolean("debug")) Console.log("Debug: current levels: " + results.toString());
		return results;
	}
}
