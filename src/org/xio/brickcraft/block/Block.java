package org.xio.brickcraft.block;

import java.awt.Graphics;

import org.lggl.graphics.Texture;
import org.xio.brickcraft.Tile;
import org.xio.brickcraft.World;

public class Block {
	
	protected Texture texture;
	
	protected void setTexture(Texture tex) {
		texture = tex;
	}

	public boolean haveSpecialRender() {
		return false;
	}
	
	public void render(int x, int y, Graphics g) {
		
	}
	
	public void update(World w, int x, int y) {
		
	}
	
	public boolean hasRandomTicking() {
		return false;
	}
	
	public void randomTick(World w, Tile t) {
		
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public Block() {
		
	}

}
