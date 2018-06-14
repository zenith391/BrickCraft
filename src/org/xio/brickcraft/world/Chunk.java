package org.xio.brickcraft.world;

import org.xio.brickcraft.Tile;

public class Chunk {

	public Tile[][] tiles;
	
	public Chunk() {
		tiles = new Tile[16][256];
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

}
