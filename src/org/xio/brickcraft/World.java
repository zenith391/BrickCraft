package org.xio.brickcraft;

import java.util.ArrayList;
import java.util.HashMap;

import org.lggl.utils.debug.DebugLogger;
import org.xio.brickcraft.block.Blocks;
import org.xio.brickcraft.entity.Entity;
import org.xio.brickcraft.world.Chunk;

public class World {

	public Chunk[] chunks = new Chunk[256];
	public HashMap<String, Entity> entities = new HashMap<>();
	
	private boolean raining;

	public World() {
		for (int x = 0; x < 16; x++) {
			generateChunk(x);
		}
		DebugLogger.logInfo("Sucefully generated world !");
		raining = true;
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
		entities.put(Integer.toHexString(ent.hashCode()), ent);
		return Integer.toHexString(ent.hashCode());
	}
	
	public boolean colliding(int x, int y) {
		return get(x, y) != null;
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

	public void generateChunk(int x) {
		Chunk chunk = new Chunk();
		for (int x0 = 0; x0 < chunk.tiles.length; x0++) {
			for (int y = 20; y < chunk.tiles[0].length; y++) {
				chunk.tiles[x0][y] = new Tile(Blocks.getBlockByID("brickcraft:grass"), this);
			}
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
