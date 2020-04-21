package org.infury.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.infury.CustomOreGenerator;
import org.infury.lang.ConsoleSender;
import org.infury.lang.Message;
import org.infury.lang.PlayerSender;

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
			ConsoleSender.log(Message.ONLY_PLAYER);
			return false;
		}
		if (args.length < 1) {
			PlayerSender.send(sender, Message.HELP);
			return true;
		}
		if (args[0].equals("set")) {
			if (!sender.hasPermission("oregen.set." + args[1])) {
				PlayerSender.warn(sender, Message.NO_PERMISSION);
				return false;
			} else if (!validLevels.contains(args[1])) {
				PlayerSender.warn(sender, Message.NOT_EXIST);
				return false;
			}
			playerLevel.put(((Player) sender).getPlayer(), args[1]);
			blockLevel.clear();
			PlayerSender.send(sender, Message.SET);
			return true;
		} else if (args[0].equals("remove")) {
			if (!sender.hasPermission("oregen.remove")) {
				PlayerSender.warn(sender, Message.NO_PERMISSION);
			}
			playerLevel.remove(((Player) sender).getPlayer());
			blockLevel.clear();
			PlayerSender.send(sender, Message.REMOVE);
			return true;
		} else if (args[0].equals("buy")) {
			if (!sender.hasPermission("oregen.buy." + args[1])) {
				PlayerSender.warn(sender, Message.NO_PERMISSION);
				return false;
			}
			// TODO: buy logic
			return true;
		} else if (args[0].equals("upgrade")) {
			if (!sender.hasPermission("oregen.upgrade." + args[1])) {
				PlayerSender.warn(sender, Message.NO_PERMISSION);
				return false;
			}
			// TODO: upgrade logic
			return true;
		} else if (args[0].equals("list")) {
			if (!sender.hasPermission("oregen.list")) {
				PlayerSender.warn(sender, Message.NO_PERMISSION);
				return false;
			}
			// TODO: list levels
		} else if (args[0].equals("reload")) {
			if (!sender.hasPermission("oregen.reload")) {
				PlayerSender.warn(sender, Message.NO_PERMISSION);
				return false;
			}
			plugin.reloadConfig();
			PlayerSender.send(sender, Message.RELOAD);
			return true;
		} else {
			PlayerSender.warn(sender, Message.NO_SUCH_COMMAND);
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
		if (plugin.getConfig().getBoolean("debug")) ConsoleSender.log("Debug: current levels: " + results.toString());
		return results;
	}
}
