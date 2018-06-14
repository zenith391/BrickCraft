package org.xio.brickcraft.block;


import org.xio.brickcraft.Tile;
import org.xio.brickcraft.TileManager;
import org.xio.brickcraft.World;

public class Grass extends Block {
	
	int tt = 0, ttm = 20;

	public Grass() {
		setTexture(TileManager.textures.get("grass"));
	}
	
	public void randomTick(World world, Tile t) {
		int x = t.getX();
		int y = t.getY();
		Tile up = world.get(x, y-1);
		if (up != null) {
			tt++;
			if (tt > ttm) {
				world.set(x, y, new Tile(Blocks.getBlockByID("brickcraft:dirt"), world));
			}
		}
	}
	
	public void update(World world, int x, int y) {
		
	}
	
	public boolean hasRandomTicking() {
		return true;
	}

}
