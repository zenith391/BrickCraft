package org.xio.brickcraft;

import java.util.HashMap;

import org.jhggl.assets.AssetsManager;
import org.lggl.graphics.Texture;

public class TileManager {

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	public static HashMap<String, Texture> textures = new HashMap<>();
	
	static {
		textures.put("grass", AssetsManager.getTexture("textures/grass"));
		textures.put("dirt", AssetsManager.getTexture("textures/dirt"));
	}

}
