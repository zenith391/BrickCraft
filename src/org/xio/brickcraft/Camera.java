package org.xio.brickcraft;

import org.xio.brickcraft.entity.Entity;

public class Camera {

	private int x, y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void centerOn(Entity ent) {
		setX((int) (ent.getX() * TileManager.TILE_WIDTH - 640));
		setY((int)(ent.getY() * TileManager.TILE_HEIGHT - 360));
	}

	public Camera() {
		
	}

}
