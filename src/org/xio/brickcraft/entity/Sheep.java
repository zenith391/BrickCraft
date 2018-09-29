package org.xio.brickcraft.entity;

import java.awt.Graphics;

import org.jhggl.assets.AssetsManager;
import org.lggl.graphics.Texture;
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
	
	public void render(int x, int y, Graphics g) {
		super.render(x, y, g);
		if (orientation == 0) {
			g.drawImage(left.getAWTImage(), x, y, (int) (width * 32), (int) (height * 32), null);
		} else {
			g.drawImage(right.getAWTImage(), x, y, (int) (width * 32), (int) (height * 32), null);
		}
	}

}
