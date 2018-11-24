package org.xio.brickcraft.util;

import org.powerhigh.utils.Color;

public class ColorUtils {
	
	public static Color hexColor(String hex) {
		int rgb = Integer.decode("0x" + hex);
		int blue = rgb & 255;
		int green = (rgb >> 8) & 255;
		int red = (rgb >> 16) & 255;
		return new Color(red, green, blue);
	}

}
