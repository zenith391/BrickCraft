package org.xio.brickcraft.block;

import org.xio.brickcraft.TileManager;

public class OakWood extends Block {

	public OakWood() {
		setTexture(TileManager.textures.get("oak"));
	}
	
	public boolean canPassThrough() {
		return true;
	}

}
