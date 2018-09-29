package org.xio.brickcraft.block;

import java.awt.Graphics;
import java.util.Properties;

import org.lggl.graphics.Texture;
import org.xio.brickcraft.Tile;
import org.xio.brickcraft.World;

public class Block {
	
	protected Texture texture;
	protected Properties specialProperties = new Properties();
	
	protected void setTexture(Texture tex) {
		texture = tex;
	}
	
	public Object getSpecialProperty(String value) {
		return specialProperties.get(value);
	}

	public boolean haveSpecialRender() {
		return false;
	}
	
	public void render(int x, int y, Graphics g) {
		
	}
	
	public void update(World w, int x, int y) {
		
	}
	
	public float getHardness() {
		return 1.0f;
	}
	
	public String getDroppingBlock() {
		return Blocks.getIDByBlock(this);
	}
	
	public boolean hasRandomTicking() {
		return false;
	}
	
	public boolean canPassThrough() {
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
