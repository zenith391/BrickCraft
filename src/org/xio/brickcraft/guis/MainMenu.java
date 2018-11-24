package org.xio.brickcraft.guis;

import org.powerhigh.utils.Color;
import java.awt.Font;
import java.util.Random;

import org.jhggl.assets.AssetsManager;
import org.jhggl.assets.Resource;
import org.powerhigh.graphics.Interface;
import org.powerhigh.objects.Button;
import org.powerhigh.objects.Sprite;
import org.powerhigh.objects.Text;
import org.xio.brickcraft.BrickCraft;

public class MainMenu extends GUI {

	public MainMenu() {
		initComponents();
	}

	private int panoramaNum;
	private Sprite panorama;
	private Text splash;
	private int splashSize;
	private boolean splashSizeB;
	private int splashSizeACC;
	private boolean allSplash;

	private void initComponents() {
		panoramaNum = new Random().nextInt(4);
		panorama = new Sprite(AssetsManager.getTexture("main_menu/background/panorama_" + panoramaNum));
		Resource splashes = (Resource) AssetsManager.get("texts/splashes.txt");
		String[] splashs = splashes.getPlainContent().split("\n");
		int sp = new Random().nextInt(splashs.length);
		splash = new Text(splashs[sp]);
		if (splash.getText().equals("/say Display all splashes! (this will work)")) {
			allSplash = true;
			// easter egg :D
		}

		panorama.setSize(1024, 1024);
		Text title = new Text("BrickCraft");
		Text title2 = new Text("Block Edition");
		Button b1 = new Button();
		b1.setText("Singleplayer");
		Button b2 = new Button();
		b2.setText("Multiplayer");
		title.setX((1280 / 2) - 300);
		splash.setX((1280 / 2) + 150);
		splash.setFont(new Font(Font.DIALOG, Font.ITALIC, 16));
		splashSize = 16;
		title2.setX((1280 / 2) - 120);
		title.setY(100);
		splash.setY(150);
		splash.setColor(Color.YELLOW);
		splash.setRotation(-120);
		title2.setY(170);
		b1.setY(400);
		b1.setX((1280 / 2) - 125);
		b2.setX((1280 / 2) - 125);
		b2.setY(500);
		this.setColor(Color.BLACK);
		b2.setWidth(250);
		b1.addAction(new Runnable() {

			@Override
			public void run() {
				BrickCraft.getInstance().setGUI(null);
				BrickCraft.getInstance().gameInit();
			}

		});
		b2.addAction(new Runnable() {
			
			@Override
			public void run() {
				
			}
			
		});
		b1.setWidth(250);
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 114));
		title2.setFont(new Font(Font.DIALOG, Font.ITALIC, 32));
		// add(panorama);
		add(title);
		add(title2);
		add(b1);
		add(b2);
		add(splash);
		splash.setRotation(60);
	}

	public void update(Interface win) {
		splash.setRotation(splash.getRotation() + 1);
		splashSizeACC++;
		if (allSplash) {
			Resource splashes = (Resource) AssetsManager.get("texts/splashes.txt");
			String[] splashs = splashes.getPlainContent().split("\n");
			int sp = new Random().nextInt(splashs.length);
			splash.setText("/say Display all splashes (this will work)\n" + splashs[sp]);
		}
		if (splashSizeACC > 2) {
			splashSizeACC = 0;
			if (splashSizeB) {
				splashSize++;
				if (splashSize > 20) {
					splashSizeB = false;
				}
				splash.setFont(new Font(Font.DIALOG, Font.ITALIC, splashSize));
			} else {
				splashSize--;
				if (splashSize < 14) {
					splashSizeB = true;
				}
				splash.setFont(new Font(Font.DIALOG, Font.ITALIC, splashSize));
			}
		}
		super.update(win);

	}

}
