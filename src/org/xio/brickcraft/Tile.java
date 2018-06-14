package org.xio.brickcraft;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.jhggl.assets.AssetsManager;
import org.lggl.graphics.Texture;
import org.xio.brickcraft.block.Block;

public class Tile implements Cloneable {

	protected int x, y;
	protected World world;
	protected Block block;
	protected int destroyStage = -1, dacc = 0;
	private boolean destroying;
	private Random random;
	
	public Tile(Block b, World w) {
		world = w;
		block = b;
		if (b.hasRandomTicking()) {
			random = new Random();
		}
	}
	
	public void render(int x, int y, Graphics g) {
		if (block.haveSpecialRender()) {
			block.render(x, y, g);
		} else {
			g.drawImage(block.getTexture().getAWTImage(), x, y, TileManager.TILE_WIDTH, TileManager.TILE_HEIGHT, null);
		}
		postRender(x, y, g);
	}
	
	protected void postRender(int x, int y, Graphics g) {
		if (destroyStage != -1 && destroyStage < 10) {
			Texture tex = AssetsManager.getTexture("textures/destroy_stage_" + destroyStage);
			g.drawImage(tex.getAWTImage(), x, y, TileManager.TILE_WIDTH, TileManager.TILE_HEIGHT, null);
		}
	}
	
	public void update(int x, int y) {
		this.x = x;
		this.y = y;
		if (random != null) {
			int r = random.nextInt(300);
			if (r % 150 > 140) {
				block.randomTick(world, this);
			}
		}
		if (destroying) {
			dacc++;
			if (dacc > 5) {
				dacc = 0;
				destroyStage++;
			}
			if (destroyStage == 10) {
				world.set(x, y, null); // Kill this tile :(
			}
		}
		try {
			block.update(world, x, y);
		} catch (Exception e) {
			System.err.println(e + " thrown in " + block.getClass().getName() + ":update");
			e.printStackTrace();
		}
	}
	
	public boolean destroying() {
		return destroying;
	}
	
	public void startDestroy() {
		destroying = true;
	}
	
	public void endDestroy() {
		destroying = false;
		destroyStage = -1;
	}
	
	public Tile clone() {
		try {
			return getClass().getConstructor(Block.class, World.class).newInstance(block, world);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public World getWorld() {
		return world;
	}

}
