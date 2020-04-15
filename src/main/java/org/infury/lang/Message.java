package org.infury.lang;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Message {
	public static String SET, REMOVE, NO_PERMISSION, NOT_EXIST, HELP, ONLY_PLAYER, RELOAD;

	public static void setMessage(FileConfiguration lang) {
		SET = lang.getString("set");
		REMOVE = lang.getString("remove");
		NO_PERMISSION = lang.getString("no_permission");
		NOT_EXIST = lang.getString("not_exist");
		HELP = getSection(lang, "help");
		ONLY_PLAYER = lang.getString("only_player");
		RELOAD = lang.getString("reload");
	}

	private static String getSection(FileConfiguration lang, String path) {
		StringBuilder sb = new StringBuilder();
		ConfigurationSection section = lang.getConfigurationSection(path);
		for (String i : section.getKeys(false))
			sb.append(i).append("\n");
		return sb.toString();
	}
}
