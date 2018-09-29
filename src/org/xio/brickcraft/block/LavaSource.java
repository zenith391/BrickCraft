package org.xio.brickcraft.block;

import org.jhggl.assets.AssetsManager;
import org.xio.brickcraft.World;

public class LavaSource extends Liquid {

	public LavaSource() {
		setTexture(AssetsManager.getTexture("textures/lava_still").getSubTexture(0, 0, 16, 16));
		visquosity = 3.4f;
		specialProperties.put("flammable", Boolean.TRUE);
	}
	
	public void update(World w, int x, int y) {
		liquidUpdate(w, x, y);
		
	}

}
