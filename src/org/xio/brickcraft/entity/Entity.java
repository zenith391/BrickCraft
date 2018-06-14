package org.xio.brickcraft.entity;

import java.awt.Graphics;

import org.xio.brickcraft.World;

public class Entity {

	protected float x, y, velX, velY;
	private World world;
	
	public Entity(World world) {
		this.world = world;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void render(int x, int y, Graphics g) { // Render using Screen-coordinates
		
	}
	
	public strictfp void update(float x, float y) { // Update using World-coordinates
		// Gravity
		if (velY < 1 && !world.colliding((int) x, (int) y+2)) {
			if (!world.colliding((int) x, (int) y+2)) {
				velY += 0.1;
			}
		}
		if (world.colliding((int) x, (int) y+2) && velY > 0) {
			velY = -(velY / 2);
		}
		if (velX > 0) {
			velX -= 0.50;
		}
		if (velX < 0) {
			velX += 0.50;
		}
		if (!world.colliding((int) x, (int) (y+velY))) {
			this.y += velY;
		} else {
			velY = 0;
		}
		//System.out.println(velX);
		if (!world.colliding((int) (x+velX), (int) y)) {
			this.x += velX;
		} else {
			velX = 0;
		}
	}

}
