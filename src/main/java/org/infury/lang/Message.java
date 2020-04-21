package org.infury.lang;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.config.ConfigFactory;

public class Message {
	public static String SET, REMOVE, NO_PERMISSION, NOT_EXIST, HELP, ONLY_PLAYER, RELOAD, NO_SUCH_COMMAND;
	private static final FileConfiguration lang
			= YamlConfiguration.loadConfiguration(ConfigFactory.getFile("lang.yml"));

	public static void setMessage() {
		SET = lang.getString("set");
		REMOVE = lang.getString("remove");
		NO_PERMISSION = lang.getString("no_permission");
		NOT_EXIST = lang.getString("not_exist");
		HELP = ConfigFactory.getListedString("lang.yml", "help");
		ONLY_PLAYER = lang.getString("only_player");
		RELOAD = lang.getString("reload");
		NO_SUCH_COMMAND = lang.getString("no_such_command");
	}
}
