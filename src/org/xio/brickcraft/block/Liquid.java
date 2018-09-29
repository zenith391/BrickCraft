package org.xio.brickcraft.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.xio.brickcraft.Tile;
import org.xio.brickcraft.World;

public class Liquid extends Block {

	protected float visquosity = 1.0f;

	private int lt = 0;
	private int ltm = 10;

	private HashMap<Tile, Integer> lts = new HashMap<>();
	private HashMap<Tile, Integer> ltms = new HashMap<>();

	public Liquid() {

	}

	public boolean canPassThrough() {
		return true;
	}
	
	public String getDroppingBlock() {
		return null;
	}

	protected void liquidUpdate(World w, int x, int y) {
		try {
			Tile t = w.get(x, y);
			
			if (!lts.containsKey(t)) {
				lts.put(t, 0);
				ltms.put(t, (int) (visquosity * 10));
			}
			lt = lts.get(t);
			ltm = ltms.get(t);
			if (lt > ltm) {
				if (w.get(x, y + 1) == null) {
					w.set(x, y + 1, new Tile(this, w, 1));
				}
				if (t.getBlockState() == 1) {
					Tile tUp = w.get(x, y - 1);
					if (tUp == null) {
						w.set(x, y, null);
					}
				}
				lt = -(new Random().nextInt(3));
			}
			ltm = (int) (visquosity * 10);
			lt++;
			lts.put(t, lt);
			ltms.put(t, ltm);
			
		
		} catch (Exception e) {

		}
	}

}
