package org.xio.brickcraft.guis;

import org.powerhigh.graphics.Drawer;
import org.powerhigh.graphics.Interface;
import org.powerhigh.objects.Container;
import org.powerhigh.utils.Color;

public class GUI extends Container {

	public GUI() {
		this.checkContent = false;
	}
	
	public void update(Interface win) {
		setX(0);
		setY(0);
		setSize(win.getWidth(), win.getHeight());
	}
	
	public void paint(Drawer g, Interface src) {
		g.setColor(Color.LIGHT_GRAY);
		//g.fillRect(x, y, width, height);
		super.paint(g, src);
		update(src);
	}

}
