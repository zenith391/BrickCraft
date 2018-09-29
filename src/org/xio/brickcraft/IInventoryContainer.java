package org.xio.brickcraft;

import org.xio.brickcraft.item.ItemStack;

public interface IInventoryContainer {

	public Inventory getInventory();
	public boolean addItem(ItemStack stack);
	
}
