package org.xio.brickcraft.item;

import java.awt.Graphics;

import org.xio.brickcraft.Filter;
import org.xio.brickcraft.IInventoryContainer;
import org.xio.brickcraft.TileManager;
import org.xio.brickcraft.World;
import org.xio.brickcraft.entity.Entity;
import org.xio.brickcraft.entity.Player;

public class ItemStack extends Entity {

	private Item model;
	private int count = 1, maxCount = 64;
	
	private int floatingY = 0;
	private boolean floatingState;
	
	private Filter playerFilter;
	
	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public ItemStack(World world, Item model, int count) {
		super(world);
		if (count > maxCount) {
			count = 64;
		}
		this.model = model;
		this.count = count;
		this.width = 0.5f;
		this.height = 0.5f;
		playerFilter = new Filter()
				.acceptInterface(IInventoryContainer.class);
	}
	
	public strictfp void update(float x, float y) {
		super.update(x, y);
		Entity[] entities = this.getCollidedEntities(playerFilter);
		for (Entity ent : entities) {
			System.out.println(ent);
			if (ent instanceof IInventoryContainer) {
				IInventoryContainer p = (IInventoryContainer) ent;
				if (p.addItem(this)) {
					world.removeEntity(getID());
				}
			}
		}
	}
	
	public void render(int x, int y, Graphics g) {
		super.render(x, y, g);
		g.drawImage(getItem().getTexture().getAWTImage(), x, y + floatingY, TileManager.TILE_WIDTH / 2, TileManager.TILE_HEIGHT / 2, null);
		if (floatingY < -1) {
			floatingState = true;
		}
		
		if (floatingY > 10) {
			floatingState = false;
		}
		
		if (floatingState) {
			floatingY ++;
		} else {
			floatingY --;
		}
	}
	
	public Item getItem() {
		return model;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean setCount(int count) {
		if (count < maxCount) {
			this.count = count;
			return true;
		}
		return false;
	}

}
