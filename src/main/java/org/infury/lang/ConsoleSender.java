package org.infury.lang;

import org.bukkit.Bukkit;

public class ConsoleSender {
	private ConsoleSender() {}

	public static void log(String message) {
		Bukkit.getConsoleSender().sendMessage(("[CustomOreGenerator] " + message).replaceAll("&", "\u00A7"));
	}

	public static void debug(String message) {
		Bukkit.getConsoleSender().sendMessage(("&e[CustomOreGenerator] " + message).replaceAll("&", "\u00A7"));
	}
}
