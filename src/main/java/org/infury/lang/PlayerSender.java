package org.infury.lang;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerSender {
	public static void send(Player player, String message) {
		player.sendMessage(("[CustomOreGenerator] " + message).replaceAll("&", "\u00A7"));
	}

	public static void warn(Player player, String message) {
		player.sendMessage(("&c[CustomOreGenerator] " + message).replaceAll("&", "\u00A7"));
	}

	public static void send(CommandSender player, String message) {
		player.sendMessage(("[CustomOreGenerator] " + message).replaceAll("&", "\u00A7"));
	}

	public static void warn(CommandSender player, String message) {
		player.sendMessage(("&c[CustomOreGenerator] " + message).replaceAll("&", "\u00A7"));
	}
}
