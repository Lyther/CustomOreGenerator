package org.infury.api;

import org.bukkit.Bukkit;

public class Console {
	private Console() {}

	public static void log(String message) {
		Bukkit.getConsoleSender().sendMessage(("[org.infury.CustomOreGenerator] " + message).replace('&', '\u00A7'));
	}
}
