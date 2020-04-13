package org.infury.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.infury.api.FindNearby;
import org.infury.api.Console;
import org.infury.api.GenerateOre;

public class BlockGenEvent implements Listener {
	@EventHandler
	public void onBlockFormEvent(BlockFormEvent e) {
		Block block = e.getBlock();
		if (block.getType() == Material.LAVA && FindNearby.findNearbyLava(block)) {
			GenerateOre.generateOre(block);
			e.setCancelled(true);
		} else {
			Console.log("Debug: target block type is: " + block.getType());
			Console.log("Debug: nearby lava found: " + FindNearby.findNearbyLava(block));
		}
	}
}
