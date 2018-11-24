package org.xio.brickcraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.powerhigh.utils.debug.DebugLogger;
import org.xio.brickcraft.block.Block;
import org.xio.brickcraft.block.Blocks;
import org.xio.brickcraft.entity.Entity;
import org.xio.brickcraft.world.Chunk;

public class World {

	public Chunk[] chunks = new Chunk[256];
	public HashMap<String, Entity> entities = new HashMap<>();
	
	private boolean raining;

	public World() {
		for (int x = 0; x < 32; x++) {
			generateChunk(x);
		}
		DebugLogger.logInfo("Sucefully generated world !");
	}
	
	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	public Entity getEntity(String uuid) {
		return entities.get(uuid);
	}
	
	public String addEntity(Entity ent) {
		String id = Integer.toHexString(ent.hashCode());
		entities.put(id, ent);
		ent.setID(id);
		return id;
	}
	
	public boolean removeEntity(String id) {
		return entities.remove(id) != null;
	}
	
	public boolean colliding(int x, int y) {
		Tile t = get(x, y);
		if (t == null) {
			return false;
		} else {
			if (t.getBlock().canPassThrough()) {
				return false;
			} else {
				return true;
			}
		}
	}

	public Tile get(int x, int y) {
		try {
			if (x < 0) return null;
			if (y > 255) return null;
			Chunk ch = chunks[x / 16];
			return ch.tiles[x % 16][y];
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean set(int x, int y, Tile t) {
		try {
			if (x < 0) return false;
			if (y > 255) return false;
			Chunk ch = chunks[x / 16];
			ch.tiles[x % 16][y] = t;
			Debug.debug("Sucefully set to " + ch.tiles[x % 16][y]);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void generateTree(Chunk ch, int x, int y, Random r) {
		int height = -r.nextInt(20);
		if (height > -5) {
			height = -5;
		}
		Block oak = Blocks.getBlockByID("brickcraft:oak_log");
		Block leaf = Blocks.getBlockByID("brickcraft:oak_leaves");
		for (int iy = y; iy > y + height; iy--) {
			if (iy > 0) {
				ch.tiles[x][iy] = new Tile(oak, this);
			}
		}
	}

	Random r = new Random();
	int sy = 30;
	
	public void generateChunk(int x) {
		Chunk chunk = new Chunk();
		for (int x0 = 0; x0 < chunk.tiles.length; x0++) {
			sy += r.nextInt(3) - 1;
			if (sy < 0) {
				sy = 0;
			}
			if (sy > 250) {
				sy = 250;
			}
			for (int y = sy; y < 60; y++) {
				chunk.tiles[x0][y] = new Tile(Blocks.getBlockByID("brickcraft:dirt"), this);
			}
			int tree = r.nextInt(500);
			if (tree > 450) {
				generateTree(chunk, x0, sy, r);
			}
		}
		for (int x0 = 0; x0 < chunk.tiles.length; x0++) {
			for (int y = 60; y < chunk.tiles[0].length; y++) {
				if (y < 250) {
					chunk.tiles[x0][y] = new Tile(Blocks.getBlockByID("brickcraft:stone"), this);
				}
			}
		}
		for (int x0 = 0; x0 < chunk.tiles.length; x0++) {
			
		}
		chunks[x] = chunk;
	}

	public boolean isGenerated(int x) {
		try {
			return chunks[x] != null;
		} catch (Exception e) {
			return false;
		}
	}

}
