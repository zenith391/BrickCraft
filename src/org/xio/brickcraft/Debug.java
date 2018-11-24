package org.xio.brickcraft;

import org.powerhigh.utils.debug.DebugLogger;

public class Debug {

	public Debug() {
		
	}
	
	public static void debug(Object o) {
		if (BrickCraft.getInstance().debugMode == true)
			DebugLogger.logInfo(o);
	}

}
