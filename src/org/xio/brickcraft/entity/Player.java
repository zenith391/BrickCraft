package org.xio.brickcraft.entity;

import java.awt.Graphics;

import org.jhggl.assets.AssetsManager;
import org.lggl.graphics.Texture;
import org.lggl.input.Keyboard;
import org.xio.brickcraft.BrickCraft;
import org.xio.brickcraft.IInventoryContainer;
import org.xio.brickcraft.Inventory;
import org.xio.brickcraft.World;

public class Player extends Entity implements IInventoryContainer {

	private Texture left;
	private Texture right;
	private Inventory inv;
	private int orientation;
	private int width = 19;
	private int height = 64; // original should be 57
	
	public Player(World w) {
		super(w);
		left = AssetsManager.getTexture("textures/steve-left");
		right = AssetsManager.getTexture("textures/steve-right");
		orientation = 0;
		inv = new Inventory(8);
	}

	public void render(int x, int y, Graphics g) {
		if (orientation == 0) {
			g.drawImage(left.getAWTImage(), x, y, width, height, null);
		} else {
			g.drawImage(right.getAWTImage(), x, y, width, height, null);
		}
	}
	
	public void update(float x, float y) {
		super.update(x, y);
		if (BrickCraft.getInstance().win.getKeyboard().isKeyDown(Keyboard.KEY_D)) {
			orientation = 1;
			this.velX = 0.5f;
		}
		if (BrickCraft.getInstance().win.getKeyboard().isKeyDown(Keyboard.KEY_A)) {
			orientation = 0;
			this.velX = -1;
		}
		if (BrickCraft.getInstance().win.getKeyboard().isKeyDown(Keyboard.KEY_W)) {
			this.velY = -0.5f;
		}
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}
	
}
