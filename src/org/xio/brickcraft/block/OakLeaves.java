package org.xio.brickcraft.block;

import org.xio.brickcraft.TileManager;

public class OakLeaves extends Block {

	public OakLeaves() {
		setTexture(TileManager.textures.get("leaf"));
	}
	
	public float getHardness() {
		return 0.0f;
	}
	
	public boolean canPassThrough() {
		return true;
	}

}
