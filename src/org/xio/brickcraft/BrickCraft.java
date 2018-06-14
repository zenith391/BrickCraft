package org.xio.brickcraft;

import java.awt.Color;

import org.jhggl.assets.AssetsManager;
import org.lggl.SizedViewport;
import org.lggl.game.SimpleGame;
import org.lggl.graphics.Window;
import org.lggl.graphics.objects.Text;
import org.lggl.graphics.renderers.SimpleRenderer;
import org.lggl.graphics.renderers.lightning.Lightning;
import org.lggl.input.Keyboard;
import org.lggl.input.Mouse;
import org.xio.brickcraft.block.Blocks;
import org.xio.brickcraft.block.Dirt;
import org.xio.brickcraft.block.Grass;
import org.xio.brickcraft.entity.Player;
import org.xio.brickcraft.entity.Sheep;
import org.xio.brickcraft.guis.GUI;
import org.xio.brickcraft.guis.Loading;
import org.xio.brickcraft.guis.MainMenu;
import org.xio.brickcraft.item.Item;
import org.xio.brickcraft.item.ItemStack;
import org.xio.brickcraft.util.ColorUtils;
import org.xio.brickcraft.world.Chunk;

public class BrickCraft extends SimpleGame {

	private static BrickCraft instance;
	private World world;
	private WorldRenderer renderworld;
	private Camera cam;
	private Player player;
	private Text debug;
	public Window win;
	public boolean debugMode = false;
	public boolean playing = false;
	private GUI gui;

	public Camera getCamera() {
		return cam;
	}

	public World getWorld() {
		return world;
	}

	public void loadWorld(World world) {
		this.world = world;
		renderworld.setWorld(world);
	}
	
	public GUI getGUI() {
		return gui;
	}
	
	public void setGUI(GUI g) {
		if (gui != null) {
			win.remove(gui);
		}
		if (g != null)
			win.add(g);
		gui = g;
	}

	public BrickCraft() {

	}

	public void onExit() {
		System.exit(0);
	}
	
	public void load() {
		win.setBackground(Color.WHITE);
		AssetsManager.setAssetsFolder("assets");
		setGUI(new Loading());
		AssetsManager.loadAssets();

		Blocks.register("brickcraft:grass", new Grass());
		Blocks.register("brickcraft:dirt", new Dirt());
		
		setGUI(new MainMenu());
	}

	public void gameInit() {

		
		
		World world = new World();
		renderworld = new WorldRenderer(world);
		this.world = world;
		cam = new Camera();
		player = new Player(world);

		world.addEntity(player);

		player.getInventory().setStack(0, new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:grass")), 32));

		Sheep shepo = new Sheep(world);
		shepo.setX(2.5f);
		shepo.setY(3.0f);

		world.addEntity(shepo);
		
		
		win.add(renderworld);
		hud = new HUD();
		win.add(hud);
		
		win.setBackground(Color.CYAN);
		
		playing = true;
	}

	private Tile placing, oldPlacing;
	private HUD hud;

	public void changePlacing(Tile t) {
		placing = t;
		if (t == null) {
			debug.setText("Removing");
		} else {
			debug.setText("Placing: " + t.getClass().getSimpleName());
		}
	}

	@Override
	public void update(Window win) {
		if (win.isClosed()) {
			onExit();
		}
		
			debug.setText("FPS: " + win.getFPS());
		
		Keyboard k = win.getKeyboard();
		if (playing) {
			if (Mouse.isPressed()) {
				if (hud.slotID > 8) {
					hud.slotID = 8;
				}
				if (hud.slotID < 0) {
					hud.slotID = 0;
				}
				ItemStack sStack = player.getInventory().getStack(hud.slotID);
				Item sel = null;
				if (sStack != null)
					sel = sStack.getItem();
				if (sel != null) {
					if (sel.getItemBlock() != null) {
						placing = new Tile(sel.getItemBlock(), world);
					}
				}
				if (!Mouse.isButtonPressed(1)) {

					Chunk chunk = this.world.chunks[(cam.getX() + Mouse.getX()) / (16 * TileManager.TILE_WIDTH)];
					if (chunk != null) {
						if (placing != null && Mouse.isButtonPressed(0))
							chunk.tiles[((cam.getX() + Mouse.getX()) / TileManager.TILE_WIDTH)
									% 16][(cam.getY() + Mouse.getY()) / TileManager.TILE_HEIGHT] = placing.clone();
						else {
							if (Mouse.isButtonPressed(2)) {
								Tile t = chunk.tiles[((cam.getX() + Mouse.getX()) / TileManager.TILE_WIDTH)
										% 16][(cam.getY() + Mouse.getY()) / TileManager.TILE_HEIGHT];
								if (t != null) {
									t.startDestroy();
								}
							}
						}
					} else {
						this.world.chunks[(cam.getX() + Mouse.getX()) / 16] = new Chunk();
						;
					}
				} else {

				}
			} else {
				try {
					Chunk chunk = this.world.chunks[(cam.getX() + Mouse.getX()) / (16 * TileManager.TILE_WIDTH)];
					if (chunk != null) {
						Tile t = chunk.tiles[((cam.getX() + Mouse.getX()) / TileManager.TILE_WIDTH)
								% 16][(cam.getY() + Mouse.getY()) / TileManager.TILE_HEIGHT];
						if (t != null) {
							if (t.destroying()) {
								t.endDestroy();
							}
						}
					}
				} catch (Exception e) {

				}
			}
			cam.centerOn(player);
		}
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void init(Window win) {
		((Lightning) Window.getRenderer()).setUsePostProcessing(false);
		this.win = win;
		win.setTitle("BrickCraft: Block Edition v0_10");
		// BROWN: A52A2A 
		win.setViewportManager(new SizedViewport(1280, 720));
		win.setSize(1380, 820);
		win.setResizable(true);
		debug = new Text("Placing: Grass");
		debug.setY(50);
		win.add(debug);
		win.show();
		load();
		win.setBackground(ColorUtils.hexColor("A52A2A"));
	}

	public static BrickCraft getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		instance = new BrickCraft();
		for (String str : args) {
			if (str.equals("--debug")) {

			}
		}
		instance.start();
	}

}
