package org.xio.brickcraft.multiplayer;

import java.util.ArrayList;

import org.lggl.multiplayer.PackageServer;
import org.lggl.multiplayer.ServerHandler;
import org.xio.brickcraft.BrickCraft;
import org.xio.brickcraft.entity.Entity;

public class Server extends ServerHandler {
	
	public Server(PackageServer owner) {
		super(owner);
	}

	public static final short PACKET_STARTMOVE = 5;
	public static final short PACKET_STOPMOVE = 7;
	public static final short PACKET_DISCONNECT = 6;
	public static final short PACKET_CONNECT = 4;
	
	private ArrayList<Entity> moving = new ArrayList<>();

	@Override
	public String getValue(String name) {
		if (name.equals("server.name")) {
			return "Untitled";
		}
		if (name.equals("server.motd")) {
			return "BrickCraft server";
		}
		if (name.equals("server.brand")) {
			return BrickCraft.BRAND.toLowerCase();
		}
		if (name.equals("server.version")) {
			return BrickCraft.VERSION.trim().toLowerCase();
		}
		return "undefined";
	}

	@Override
	public void onPacket(short type, byte[] data) {
		switch (type) {
		case PACKET_STARTMOVE:
			break;
		case PACKET_DISCONNECT:
			break;
		case PACKET_CONNECT:
			break;
		case PACKET_STOPMOVE:
			break;
		default:
			System.err.println("Client sending invalid packet!");
			break;
		}
	}

	@Override
	public void putValue(String name, String value) {
		
	}

}
