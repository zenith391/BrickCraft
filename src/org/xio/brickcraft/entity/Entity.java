package org.xio.brickcraft.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.xio.brickcraft.Filter;
import org.xio.brickcraft.Tile;
import org.xio.brickcraft.TileManager;
import org.xio.brickcraft.World;

public class Entity {

	protected float x, y, velX, velY, width, height;
	
	protected float health;
	protected World world;
	
	protected String id;
	
	public static final boolean DRAW_HITBOXES = true;
	
	public Entity(World world) {
		this.world = world;
		this.health = 20f;
	}
	
	public float getWidth() {
		return width;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public float getHeight() {
		return height;
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
	
	public static boolean isInBounds(double x0, double y0, double x, double y, double w, double h) {
		boolean cond1 = false;
		boolean cond2 = false;
		for (int i = (int) x; i < w + x; i += 1d) {
			if (x0 == i) {
				cond1 = true;
			}
			for (int i2 = (int) y; i2 < h + y; i2 += 1d) {
				if (y0 == i2) {
					cond2 = true;
				}
			}
		}
		return cond1 && cond2;
	}

	public void render(int x, int y, Graphics g) { // Render using Screen-coordinates
		if (DRAW_HITBOXES) {
			g.setColor(Color.BLUE);
			g.drawRect(x, y, (int) (width * TileManager.TILE_WIDTH), (int) (height * TileManager.TILE_HEIGHT));
			g.setColor(Color.RED);
			g.fillRect(x, y, 2, 2);
		}
	}
	
	private int roundBlockFloat(float f) {
		if (f < 1) {
			f = 1;
		}
		return Math.round(f);
	}
	
	public Entity[] getCollidedEntities(Filter filter) {
		ArrayList<Entity> e = new ArrayList<Entity>();
		//setY(30);
		for (Entity ent : world.entities.values()) {
			boolean match = false;
			System.out.println(ent.getX() + "&&" + getX() + ", " + ent.getY() + "&&" + getY());
			if (isInBounds(ent.getX() * 32, ent.getY() * 32, (getX() - 1) * 32, (getY() - 1) * 32, (getWidth() + 2) * 32, (getHeight() + 2) * 32)) {
				for (Class<?> klass : filter.getAcceptedClasses()) {
					System.out.println("accepted " + klass);
					if (klass.isAssignableFrom(ent.getClass())) {
						match = true;
						break;
					}
				}
				for (Class<?> i : filter.getAcceptedInterfaces()) {
					System.out.println("accepted " + i + " & ent = " + ent);
					for (Class<?> in : ent.getClass().getInterfaces()) {
						if (i.isAssignableFrom(in)) {
							match = true;
							break;
						}
					}
				}
				//System.err.println("COLLIDING + MATCH = " + match);
				if (match) {
					e.add(ent);
				}
			}
		}
		
		return e.toArray(new Entity[e.size()]);
	}
	
	public strictfp void update(float x, float y) { // Update using World-coordinates
		// Gravity
		this.x = x;
		this.y = y;
		if (velY < 1 && !world.colliding((int) x, (int) y+roundBlockFloat(height))) {
			if (!world.colliding((int) x, (int) y+roundBlockFloat(height))) {
				velY += 0.1;
			}
		}
		if (world.colliding((int) x, (int) y+roundBlockFloat(height)) && velY > 0) {
			velY = 0;
			this.y = y;
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
		
		Tile t = world.get(roundBlockFloat(x), roundBlockFloat(y));
		if (t != null && t.isFlammable()) {
			health -= 1f;
		}
		//System.out.println(velX);
		if (!world.colliding((int) (x+velX), (int) y)) {
			this.x += velX;
		} else {
			velX = 0;
		}

		velX = 0;
		

		
	}

}
