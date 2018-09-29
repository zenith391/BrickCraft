package org.xio.brickcraft.block;

import org.jhggl.assets.AssetsManager;
import org.xio.brickcraft.World;

public class WaterSource extends Liquid {

	public WaterSource() {
		this.setTexture(AssetsManager.getTexture("textures/water_overlay"));
	}
	
	public void update(World w, int x, int y) {
		liquidUpdate(w, x, y);
		
	}

}
