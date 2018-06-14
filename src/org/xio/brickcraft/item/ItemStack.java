package org.xio.brickcraft.item;

import org.xio.brickcraft.World;
import org.xio.brickcraft.entity.Entity;

public class ItemStack extends Entity {

	private Item model;
	private int count = 1, maxCount = 64;
	
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
