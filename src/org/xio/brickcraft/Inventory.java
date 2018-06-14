package org.xio.brickcraft;

import org.xio.brickcraft.item.ItemStack;

public class Inventory {

	private ItemStack[] items;
	
	public Inventory(int size) {
		items = new ItemStack[size];
	}
	
	public ItemStack getStack(int pos) {
		if (pos > items.length || pos < 0)
			return null;
		return items[pos];
	}
	
	public int getSize() {
		return items.length;
	}
	
	public boolean hasStack(int pos) {
		return pos > -1 && pos < items.length;
	}
	
	public boolean setStack(int pos, ItemStack stack) {
		if (hasStack(pos)) {
			items[pos] = stack;
			return true;
		}
		return false;
	}

}
