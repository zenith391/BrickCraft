package org.xio.brickcraft.block;

import org.xio.brickcraft.TileManager;

public class Stone extends Block {

	public Stone() {
		setTexture(TileManager.textures.get("stone"));
	}
	
	public float getHardness() {
		return 2.4f;
	}

}
