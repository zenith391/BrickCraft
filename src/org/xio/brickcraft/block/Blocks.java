package org.xio.brickcraft.block;

import java.util.HashMap;
import java.util.Map;

public class Blocks {

	private static Map<String, Block> blockRegister = new HashMap<>();
	
	public static void register(String id, Block b) {
		blockRegister.put(id, b);
	}
	
	public static Block getBlockByID(String id) {
		return blockRegister.get(id);
	}
	
	public static String getIDByBlock(Block block) {
		for (String key : blockRegister.keySet()) {
			if (blockRegister.get(key).equals(block)) {
				return key;
			}
		}
		return null;
	}

}
