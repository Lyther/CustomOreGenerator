package org.infury.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.CustomOreGenerator;
import org.infury.lang.ConsoleSender;

import java.io.File;
import java.util.List;

public class ConfigFactory {
	private static final CustomOreGenerator plugin = CustomOreGenerator.getInstance();
	private static final FileConfiguration config = CustomOreGenerator.getInstance().getConfig();

	public static File getFile(String name) {
		String path = "plugins/CustomOreGenerator/" + name;
		File result = new File(path);
		if (config.getBoolean("debug") && !result.exists())
			ConsoleSender.debug("Target config file not exists: " + result.getAbsolutePath());
		return result;
	}

	public static String getListedString(String path, String name) {
		StringBuilder sb = new StringBuilder();
		FileConfiguration lang = YamlConfiguration.loadConfiguration(getFile(path));
		List<String> section = lang.getStringList(name);
		for (String i : section)
			sb.append(i).append("\n");
		return sb.toString();
	}
}
