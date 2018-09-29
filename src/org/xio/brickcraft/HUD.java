
package org.xio.brickcraft;

import java.awt.Color;
import java.awt.Graphics;

import org.lggl.graphics.Window;
import org.lggl.objects.GameObject;
import org.xio.brickcraft.entity.Player;
import org.xio.brickcraft.item.Item;
import org.xio.brickcraft.item.ItemStack;

public class HUD extends GameObject {

	public HUD() {
		
	}
	
	private int iox = 1280/2-200, ioy = 680;
	public int slotID = 0;

	@Override
	public void paint(Graphics g, Window src) {
		Player p = BrickCraft.getInstance().getPlayer();
		Inventory i = p.getInventory();
		g.setColor(Color.CYAN);
		//g.fillRect(0, 0, 1280, 720);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("BrickCraft " + BrickCraft.VERSION, 0, 12);
		for (int a = 0; a < i.getSize(); a++) {
			g.fill3DRect(iox + (a * 32), ioy, 32, 32, true);
			if (slotID == a) {
				g.setColor(Color.WHITE);
				g.drawRect(iox + (a * 32), ioy, 32, 32);
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
						g.drawImage(item.getTexture().getAWTImage(), x, y, 28, 28, null);
					}
					g.setColor(Color.WHITE);
					g.drawString(String.valueOf(s.getCount()), x + 18, y + 30);
					g.setColor(Color.LIGHT_GRAY);
				}
			}
		}
	}

}
