package org.xio.brickcraft.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.jhggl.assets.AssetsManager;
import org.powerhigh.graphics.Drawer;
import org.powerhigh.graphics.Texture;
import org.powerhigh.input.AbstractKeyboard;
import org.powerhigh.input.KeyCodes;
import org.xio.brickcraft.BrickCraft;
import org.xio.brickcraft.IInventoryContainer;
import org.xio.brickcraft.Inventory;
import org.xio.brickcraft.TileManager;
import org.xio.brickcraft.World;
import org.xio.brickcraft.item.ItemStack;

public class Player extends Entity implements IInventoryContainer {

	private Skin skin;
	private Inventory inv;
	private int orientation;
	
	class Skin {
		public Texture head_left;
		public Texture head_right;
		public Texture arm_left;
		public Texture arm_right;
		public Texture leg_left;
		public Texture leg_right;
		
		public Skin(Texture t) {
			head_right = t.getSubTexture(0, 8, 8, 8);
			head_left = t.getSubTexture(16, 8, 8, 8);
			leg_left = t.getSubTexture(16, 52, 8, 12);
			leg_right = t.getSubTexture(24, 52, 8, 12);
			arm_left = t.getSubTexture(32, 52, 8, 12);
			arm_right = t.getSubTexture(40, 52, 8, 12);
		}
	}
	
	public Player(World w) {
		super(w);
		skin = new Skin(AssetsManager.getTexture("_Zen1th_"));
		orientation = 0;
		inv = new Inventory(8);
		width = 1f;
		height = 1.78f; // original should be 1.78f
	}
	
	int armRot = 0;
	boolean armRotDir = false;

	public void render(int x, int y, Drawer g) {
		super.render(x, y, g);
		int rx = (int) (width * TileManager.TILE_WIDTH);
		int ry = (int) (height * TileManager.TILE_HEIGHT);
		if (orientation == 0) {
			g.drawTexture(x+((rx/2)/2), y, rx/2, rx/2, skin.head_left);
			g.saveState();
			if (velX != 0) {
				g.drawTexture(x+((rx/2)/2) + 2 , y + (rx/2), (rx / 2) - 4, ry / 2, skin.arm_left);
				g.localRotate(Math.toRadians(armRot), x+((rx/2)/2) + 2 , y + (rx/2));
				if (armRotDir) {
					armRot += 3;
					if (armRot >= 50) {
						armRotDir = false;
					}
				} else {
					armRot -= 3;
					if (armRot <= -50) {
						armRotDir = true;
					}
				}
			} else {
				armRot = 0;
			}
			g.drawTexture(x+((rx/2)/2) + 2 , y + (rx/2), (rx / 2) - 4, ry / 2, skin.arm_left);
			g.restoreState();
			g.drawTexture(x+((rx/2)/2) + 2 , y + (ry / 2) + (rx/2), (rx / 2) - 4, ry / 3, skin.leg_left);
		} else {
			g.drawTexture(x+((rx/2)/2), y, rx/2, rx/2, skin.head_right);
			g.saveState();
			if (velX != 0) {
				g.drawTexture(x+((rx/2)/2) + 2 , y + (rx/2), (rx / 2) - 4, ry / 2, skin.arm_right);
				g.localRotate(Math.toRadians(-armRot), x+((rx/2)/2) + 2 , y + (rx/2));
				if (armRotDir) {
					armRot += 3;
					if (armRot >= 50) {
						armRotDir = false;
					}
				} else {
					armRot -= 3;
					if (armRot <= -50) {
						armRotDir = true;
					}
				}
			} else {
				armRot = 0;
			}
			g.drawTexture(x+((rx/2)/2) + 2 , y + (rx/2), (rx / 2) - 4, ry / 2, skin.arm_right);
			g.restoreState();
			g.drawTexture(x+((rx/2)/2) + 2 , y + (ry / 2) + (rx/2), (rx / 2) - 4, ry / 3, skin.arm_right);
		}
		g.drawText(x - 10, y - 4, "HP: " + health);
	}
	
	public void update(float x, float y) {
		super.update(x, y);
		if (BrickCraft.getInstance().win.getInput().getKeyboard().isKeyDown(KeyCodes.KEY_D)) {
			orientation = 1;
			this.velX = 0.65f;
		}
		if (BrickCraft.getInstance().win.getInput().getKeyboard().isKeyDown(KeyCodes.KEY_A)) {
			orientation = 0;
			this.velX = -0.65f;
		}
		if (BrickCraft.getInstance().win.getInput().getKeyboard().isKeyDown(KeyCodes.KEY_W)) {
			this.velY = -0.5f;
		}
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}

	@Override
	public boolean addItem(ItemStack stack) {
		return inv.add(stack);
	}
	
}
