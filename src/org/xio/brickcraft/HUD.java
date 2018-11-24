
package org.xio.brickcraft;

import org.powerhigh.utils.*;

import org.powerhigh.graphics.*;
import org.powerhigh.objects.GameObject;
import org.xio.brickcraft.entity.Player;
import org.xio.brickcraft.item.Item;
import org.xio.brickcraft.item.ItemStack;

public class HUD extends GameObject {

	public HUD() {
		
	}
	
	private int iox = 1280/2-200, ioy = 680;
	public int slotID = 0;

	@Override
	public void paint(Drawer g, Interface src) {
		g.translate(BrickCraft.getInstance().getCamera().getX(), BrickCraft.getInstance().getCamera().getY());
		Player p = BrickCraft.getInstance().getPlayer();
		Inventory i = p.getInventory();
		g.setColor(Color.CYAN);
		//g.fillRect(0, 0, 1280, 720);
		g.setColor(Color.LIGHT_GRAY);
		g.drawText(0, 12, "BrickCraft " + BrickCraft.VERSION);
		for (int a = 0; a < i.getSize(); a++) {
			g.fillRect(iox + (a * 32), ioy, 32, 32);
			if (slotID == a) {
				g.setColor(Color.WHITE);
				g.fillRect(iox + (a * 32), ioy, 32, 32); // normal = draw
				g.setColor(Color.LIGHT_GRAY);
			}
			int x = iox + (a * 32) + 2;
			int y = ioy + 2;
			ItemStack s = i.getStack(a);
			if (s != null) {
				if (s.getCount() == 0) {
					i.setStack(a, null); // We still check
				}
				Item item = s.getItem();
				if (item != null) {
					if (item.getTexture() != null) {
						g.drawTexture(x, y, 28, 28, item.getTexture());
					}
					g.setColor(Color.WHITE);
					g.drawText(x + 18, y + 30, String.valueOf(s.getCount()));
					g.setColor(Color.LIGHT_GRAY);
				}
			}
		}
	}

}
