package org.xio.brickcraft.entity;

import org.jhggl.assets.AssetsManager;
import org.powerhigh.graphics.Drawer;
import org.powerhigh.graphics.Texture;
import org.xio.brickcraft.World;

public class Sheep extends Entity {

	private int orientation;
	private Texture left;
	private Texture right;
	
	public Sheep(World world) {
		super(world);
		left = AssetsManager.getTexture("textures/sheep-left", "png");
		right = AssetsManager.getTexture("textures/sheep-right", "png");
		orientation = 0;
		width = 1.0f;
		height = 1.0f;
	}
	
	public void render(int x, int y, Drawer g) {
		super.render(x, y, g);
		if (orientation == 0) {
			g.drawTexture(x, y, (int) (width * 32), (int) (height * 32), left);
		} else {
			g.drawTexture(x, y, (int) (width * 32), (int) (height * 32), right);
		}
	}

}
