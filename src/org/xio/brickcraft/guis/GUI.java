package org.xio.brickcraft.guis;

import java.awt.Color;
import java.awt.Graphics;

import org.lggl.graphics.Window;
import org.lggl.graphics.objects.Container;

public class GUI extends Container {

	public GUI() {
		this.checkContent = false;
	}
	
	public void update(Window win) {
		setX(0);
		setY(0);
		setSize(win.getWidth(), win.getHeight());
	}
	
	public void paint(Graphics g, Window src) {
		g.setColor(Color.LIGHT_GRAY);
		//g.fillRect(x, y, width, height);
		super.paint(g, src);
		update(src);
	}

}
