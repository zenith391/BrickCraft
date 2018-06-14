package org.xio.brickcraft.entity;

import java.awt.Graphics;

import org.jhggl.assets.AssetsManager;
import org.lggl.graphics.Texture;
import org.xio.brickcraft.World;

public class Sheep extends Entity {

	private int orientation;
	private Texture left;
	private Texture right;
	
	private int width = 32;
	private int height = 32;
	
	public Sheep(World world) {
		super(world);
		left = AssetsManager.getTexture("textures/sheep-left", "png");
		right = AssetsManager.getTexture("textures/sheep-right", "png");
		orientation = 0;
	}
	
	public void render(int x, int y, Graphics g) {
		if (orientation == 0) {
			g.drawImage(left.getAWTImage(), x, y, width, height, null);
		} else {
			g.drawImage(right.getAWTImage(), x, y, width, height, null);
		}
	}

}
