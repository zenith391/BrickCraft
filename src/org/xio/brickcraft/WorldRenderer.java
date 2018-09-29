package org.xio.brickcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import org.jhggl.assets.AssetsManager;
import org.lggl.graphics.Texture;
import org.lggl.graphics.Window;
import org.lggl.objects.GameObject;
import org.lggl.input.Mouse;
import org.xio.brickcraft.entity.Entity;
import org.xio.brickcraft.world.Chunk;

public class WorldRenderer extends GameObject {

	private World world;
	private Texture rain;
	private int offsetRain;
	private int mtfr;
	private int tfr;

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public WorldRenderer(World world) {
		this.world = world;
		rain = AssetsManager.getTexture("environment/rain");
	}
	
	public WorldRenderer() {
		this(null);
	}

	@Override
	public void paint(Graphics g, Window source) {
		int c = 0;
		if (world.isRaining()) {
			for (int x = 0; x < 1280/rain.getWidth(); x++) {
				for (int y = -600; y < 1000/rain.getHeight(); y++) {
					g.drawImage(rain.getAWTImage(), x*rain.getWidth(), y*rain.getHeight()+(offsetRain%200), null);
				}
			}
			offsetRain += 20;
		}
		if (mtfr == 0) {
			mtfr = new Random().nextInt(64000);
		} else {
			tfr++;
		}
		if (tfr >= mtfr) {
			mtfr = 0;
			tfr = 0;
			world.setRaining(!world.isRaining());
		}
		int cx = BrickCraft.getInstance().getCamera().getX() - TileManager.TILE_WIDTH;
		int cy = BrickCraft.getInstance().getCamera().getY() - TileManager.TILE_HEIGHT;
		g.drawImage(AssetsManager.getTexture("environment/sun").getAWTImage(), 128, 128, 128, 128, null);
		g.translate(-BrickCraft.getInstance().getCamera().getX(), -BrickCraft.getInstance().getCamera().getY());
		for (Chunk ch : world.chunks) {
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 128; y++) {
					if (ch != null) {
						Tile t = ch.tiles[x][y];
						if (t != null) {
							int x0 = (c * (TileManager.TILE_WIDTH * 16))
									+ (x * TileManager.TILE_WIDTH);
							int y0 = (y * TileManager.TILE_HEIGHT);
							if (x0 > cx && x0 < cx + 1300 && y0 > cy && y0 < cy + 800) {
								t.render(x0, y0, g);
							}
							t.update(x + (c * 16), y);
						}
					}
				}
			}
			c++;
		}
		
		for (String entID : world.entities.keySet()) {
			Entity ent = world.getEntity(entID);
			ent.render(
					(int) ((ent.getX() * TileManager.TILE_WIDTH)),
					(int) ((ent.getY() * TileManager.TILE_HEIGHT)), g);
			ent.update(ent.getX(), ent.getY());
		}
		int sx = BrickCraft.getInstance().getCamera().getX() + ((Mouse.getX() / TileManager.TILE_WIDTH) * TileManager.TILE_WIDTH);
		int sy = BrickCraft.getInstance().getCamera().getY() + ((Mouse.getY() / TileManager.TILE_HEIGHT) * TileManager.TILE_HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawRect(sx, sy, TileManager.TILE_WIDTH, TileManager.TILE_HEIGHT);
		
		g.translate(BrickCraft.getInstance().getCamera().getX(), BrickCraft.getInstance().getCamera().getY());
		
	}

}
