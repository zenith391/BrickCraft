package org.xio.brickcraft.multiplayer;

import java.net.InetAddress;

import org.lggl.multiplayer.PackageSession;
import org.lggl.multiplayer.PackageSystem;
import org.lggl.utils.LGGLException;
import org.xio.brickcraft.BrickCraft;

public class Client {

	private PackageSession sess;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public void connect(InetAddress addr) {
		try {
			sess = PackageSystem.connect(addr, 7675);
			if (sess.get("server.version") != BrickCraft.VERSION.trim().toLowerCase()) {
				throw new Error("Server is not same version!");
			}
		} catch (LGGLException e) {
			e.printStackTrace();
		}
	}

}
