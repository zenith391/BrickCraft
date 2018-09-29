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
	
	public int getFreeSlot() {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public int getSize() {
		return items.length;
	}
	
	public boolean hasStack(int pos) {
		return pos > -1 && pos < items.length;
	}
	
	public boolean add(ItemStack stack) {
		for (ItemStack item : items) {
			if (item.getItem().equals(stack.getItem())) {
				if (item.getCount() + stack.getCount() < item.getMaxCount()) {
					item.setCount(item.getCount() + stack.getCount());
					return true;
				}
			} else {
				int freeSlot = getFreeSlot();
				if (freeSlot != -1) {
					setStack(freeSlot, stack);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean setStack(int pos, ItemStack stack) {
		if (hasStack(pos)) {
			items[pos] = stack;
			return true;
		}
		return false;
	}

}
