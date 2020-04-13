package org.infury.api;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class FindNearby {
	/**
	 * Find a block's nearby whether has lava.
	 * @param block the block should be checked.
	 * @return true if found lava, otherwise false.
	 */
	public static boolean findNearbyLava(Block block) {
		int[][] nearby = new int[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, -1}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		int x = block.getX(), y = block.getY(), z = block.getZ();
		World world = block.getWorld();
		for (int[] i : nearby)
			if (world.getBlockAt(x + i[0], y + i[1], z + i[2]).getType() == Material.LAVA)
				return true;
		return false;
	}
}
