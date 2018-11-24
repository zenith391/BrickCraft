package org.xio.brickcraft;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.jhggl.assets.AssetsManager;
import org.powerhigh.graphics.Drawer;
import org.powerhigh.graphics.Texture;
import org.xio.brickcraft.block.Block;
import org.xio.brickcraft.block.Blocks;
import org.xio.brickcraft.item.Item;
import org.xio.brickcraft.item.ItemStack;

public class Tile implements Cloneable {

	protected int x, y;
	protected int blockState;
	protected World world;
	protected Block block;
	protected int destroyStage = -1, dacc = 0;
	private boolean destroying;
	private Random random;

	public Tile(Block b, World w) {
		this(b, w, 0);
	}
	
	public Tile(Block b, World w, int blockState) {
		world = w;
		block = b;
		if (b != null) {
			if (b.hasRandomTicking()) {
				random = new Random();
			}
		}
		this.blockState = blockState;
	}
	
	public boolean isFlammable() {
		if (block.getSpecialProperty("flammable") != null && block.getSpecialProperty("flammable").equals(Boolean.TRUE)) {
			return true;
		}
		return false;
	}
	
	public Tile() {
		this(null, null);
	}

	public void render(int x, int y, Drawer g) {
		if (block.haveSpecialRender()) {
			block.render(x, y, g);
		} else {
			g.drawTexture(x, y, TileManager.TILE_WIDTH, TileManager.TILE_HEIGHT, block.getTexture());
		}
		postRender(x, y, g);
	}

	protected void postRender(int x, int y, Drawer g) {
		if (destroyStage != -1 && destroyStage < 10) {
			Texture tex = AssetsManager.getTexture("textures/destroy_stage_" + destroyStage);
			g.drawTexture(x, y, TileManager.TILE_WIDTH, TileManager.TILE_HEIGHT, tex);
		}
	}

	public void update(int x, int y) {
		this.x = x;
		this.y = y;
		if (random != null) {
			int r = random.nextInt(300);
			if (r % 150 > 145) {
				block.randomTick(world, this);
			}
		}
		if (destroying) {
			dacc++;
			if (dacc >= (int) ((float) 5 * block.getHardness())) {
				dacc = 0;
				destroyStage++;
			}
			if (destroyStage == 10) {
				world.set(x, y, null); // Kill this tile :(
				Block nb = null;
				if (block.getDroppingBlock() != null) {
					nb = Blocks.getBlockByID(block.getDroppingBlock());
				}
				if (nb != null) {
					ItemStack stack = new ItemStack(world, new Item(nb), 1);
					stack.setX(x + .5f);
					stack.setY(y + .5f);
					world.addEntity(stack);
				}
			}
		}
		try {
			block.update(world, x, y);
		} catch (Exception e) {
			System.err.println(e + " thrown in " + block.getClass().getName() + ":update");
			e.printStackTrace();
		}
	}
	
	public int getBlockState() {
		return blockState;
	}
	
	public void setBlockState(int bs) {
		blockState = bs;
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

	public Block getBlock() {
		return block;
	}
	
	public void setWorld(World w) {
		world = w;
	}
	
	public void setBlock(Block b) {
		block = b;
		if (b.hasRandomTicking()) {
			random = new Random();
		}
	}

}
