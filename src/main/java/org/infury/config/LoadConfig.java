package org.infury.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.infury.lang.Message;

import java.io.File;

public class LoadConfig {
	public static void initial() {
		FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(new File("./plugins/CustomOreGenerator/lang.yml"));
		Message.setMessage(fileConfiguration);
	}
}
