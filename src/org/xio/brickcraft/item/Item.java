package org.xio.brickcraft.item;

import org.powerhigh.graphics.Texture;
import org.xio.brickcraft.block.Block;

public class Item {

	private Block itemBlock;
	
	public Item(Block itemBlock) {
		this.itemBlock = itemBlock;
	}
	
	public Item() {
		this(null);
	}
	
	public Block getItemBlock() {
		return itemBlock;
	}
	
	public Texture getTexture() {
		if (getItemBlock() != null) {
			return getItemBlock().getTexture();
		}
		return null;
	}
	

}
