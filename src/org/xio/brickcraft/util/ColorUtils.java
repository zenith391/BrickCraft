package org.xio.brickcraft.util;

import java.awt.Color;

public class ColorUtils {

	public ColorUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static Color hexColor(String hex) {
		return new Color(Integer.decode("0x" + hex));
	}

}