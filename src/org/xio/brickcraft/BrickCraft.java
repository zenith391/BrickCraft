package org.xio.brickcraft;

import org.powerhigh.utils.Color;
import java.io.File;
import java.io.IOException;

import org.jhggl.assets.AssetsManager;
import org.powerhigh.SizedViewport;
import org.powerhigh.swing.audio.WavSound;
import org.powerhigh.audio.Music;
import org.powerhigh.audio.Sound;
import org.powerhigh.swing.audio.WavMusic;
import org.powerhigh.game.SimpleGame;
import org.powerhigh.graphics.Interface;
import org.powerhigh.objects.Text;
import org.powerhigh.graphics.renderers.lightning.Lightning;
import org.powerhigh.input.AbstractKeyboard;
import org.powerhigh.input.KeyCodes;
import org.powerhigh.input.Mouse;
import org.powerhigh.utils.debug.DebugLogger;
import org.xio.brickcraft.block.Blocks;
import org.xio.brickcraft.block.Dirt;
import org.xio.brickcraft.block.Grass;
import org.xio.brickcraft.block.LavaSource;
import org.xio.brickcraft.block.OakLeaves;
import org.xio.brickcraft.block.OakWood;
import org.xio.brickcraft.block.Stone;
import org.xio.brickcraft.block.WaterSource;
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
	public Interface win;
	public boolean debugMode = false;
	public boolean playing = false;
	private GUI gui;

	public static final String VERSION = "INDEV 0.25";
	public static final String BRAND = "chocolate";

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

	public void exit(Interface win) {
		try {
//			PackOutputStream pos = new PackOutputStream(new DeflaterOutputStream(new FileOutputStream("default.pak")));
//			pos.write(renderworld);
//			pos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void load() {
		win.setBackground(Color.WHITE);
		AssetsManager.setAssetsFolder("assets");
		setGUI(new Loading());
		AssetsManager.loadAssets();

		Blocks.register("brickcraft:grass", new Grass());
		Blocks.register("brickcraft:dirt", new Dirt());
		Blocks.register("brickcraft:stone", new Stone());
		Blocks.register("brickcraft:oak_log", new OakWood());
		Blocks.register("brickcraft:oak_leaves", new OakLeaves());
		Blocks.register("brickcraft:water_source", new WaterSource());
		Blocks.register("brickcraft:lava_source", new LavaSource());

		//win.setIcon(AssetsManager.getTexture("Icon"));
		
		win.setBackground(ColorUtils.hexColor("A52A2A"));
		setGUI(new MainMenu());

		music();
	}

	public void music() {
		try {
			//music = new WavMusic(new File("assets/sound/calm1.wav"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//audio.playMusic(music);
	}

	public void gameInit() {

		World world = new World();
		renderworld = new WorldRenderer(world);
		this.world = world;
		cam = new Camera();
		player = new Player(world);

		world.addEntity(player);

		player.getInventory().setStack(0, new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:grass")), 32));
		player.getInventory().setStack(1, new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:stone")), 31));
		player.getInventory().setStack(2,
				new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:oak_log")), 31));
		player.getInventory().setStack(3,
				new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:oak_leaves")), 31));
		player.getInventory().setStack(4, new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:dirt")), 31));
		player.getInventory().setStack(5, new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:water_source")), 29));
		player.getInventory().setStack(6, new ItemStack(world, new Item(Blocks.getBlockByID("brickcraft:lava_source")), 29));
		

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
	private HUD hud;

	private Music music;

	@Override
	public void update(Interface win, double delta) {
		debug.setText("FPS: " + win.getFPS());

		//System.out.println(Mouse.isPressed());
		AbstractKeyboard k = win.getInput().getKeyboard();
		
		if (k.isKeyDown(KeyCodes.KEY_F11)) {
//			win.setFullscreenWidth(1280);
//			win.setFullscreenHeight(720);
//			win.setFullscreen(!win.isFullscreen());
//			k.setKeyDown(Keyboard.KEY_F11, false);
		}
		
		if (playing) {

			if (k.isKeyDown(KeyCodes.KEY_K)) {
				if (hud.slotID > 0) {
					hud.slotID--;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (k.isKeyDown(KeyCodes.KEY_L)) {
				if (hud.slotID < 8) {
					hud.slotID++;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
				if (!Mouse.isButtonPressed(1)) {
					int tx = ((cam.getX() + Mouse.getX()) / TileManager.TILE_WIDTH);
					int ty = (cam.getY() + Mouse.getY()) / TileManager.TILE_HEIGHT;
					if (Mouse.isButtonPressed(0) && sel != null && sel.getItemBlock() != null) {
						if (sStack.getCount() > 0 && world.get(tx, ty) == null) {
							world.set(tx, ty, new Tile(sel.getItemBlock(), world));
							sStack.setCount(sStack.getCount() - 1);
						}
					}
					if (Mouse.isButtonPressed(2)) {
						Tile wt = world.get(tx, ty);
						if (wt != null) {
							wt.startDestroy();
							//wt.destroyStage = 10;
						}
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
	public void init(Interface win) {
		//((Lightning) Window.getRenderer()).setUsePostProcessing(false);
		this.win = win;
		win.setTitle("BrickCraft " + BrickCraft.VERSION);
		// BROWN: A52A2A
		debug = new Text("FPS: null");
		debug.setY(24);
		win.add(debug);
		win.setViewportManager(new SizedViewport(1280, 720));
		win.setSize(1380, 820);
		win.setResizable(true);
		win.show();
		DebugLogger.logInfo("BrickCraft " + BrickCraft.VERSION + " is loading..");
		load();

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

	@Override
	public ImplementationSettings getImplementationSettings() {
		return new ImplementationSettings(ImplementationSettings.Interface.SWING, ImplementationSettings.Audio.AWT);
	}

}
