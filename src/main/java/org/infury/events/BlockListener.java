package org.infury.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.infury.CustomOreGenerator;
import org.infury.api.FindNearby;
import org.infury.api.GenerateOre;
import org.infury.lang.ConsoleSender;

import java.util.List;

public class BlockListener implements Listener {
	private final FileConfiguration config = CustomOreGenerator.getInstance().getConfig();
	private List<String> worlds = config.getStringList("enable-worlds");

	@EventHandler
	public void onBlockFormEvent(BlockFormEvent e) {
		Block block = e.getBlock();
		if (!worlds.contains(block.getWorld().getName())) return;
		if (block.getType() == Material.LAVA && FindNearby.findNearbyLava(block)) {
			Boolean replaced = (new GenerateOre(block)).generateOre();
			if (replaced) e.setCancelled(true);
		} else if (config.getBoolean("debug")) {
			ConsoleSender.debug("Debug: target block type is: " + block.getType());
			ConsoleSender.debug("Debug: nearby lava found: " + FindNearby.findNearbyLava(block));
		}
	}
}
