package org.xio.brickcraft.guis;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import org.jhggl.assets.AssetsManager;
import org.jhggl.assets.Resource;
import org.lggl.graphics.Window;
import org.lggl.graphics.objects.Button;
import org.lggl.graphics.objects.Sprite;
import org.lggl.graphics.objects.Text;
import org.xio.brickcraft.BrickCraft;

public class MainMenu extends GUI {

	public MainMenu() {
		initComponents();
	}
	
	private int panoramaNum;
	private Sprite panorama;
	private Text splash;
	private void initComponents() {
		panoramaNum = new Random().nextInt(5);
		panorama = new Sprite(AssetsManager.getTexture("main_menu/background/panorama_" + panoramaNum));
		Resource splashes = (Resource) AssetsManager.get("texts/splashes.txt");
		String[] splashs = splashes.getPlainContent().split("\n");
		int sp = new Random().nextInt(splashs.length);
		splash = new Text(splashs[sp]);
		
		panorama.setSize(1024, 1024);
		Text title = new Text("BrickCraft");
		Text title2 = new Text("Block Edition");
		Button b1 = new Button();
		b1.setText("Singleplayer");
		title.setX((1280 / 2) - 300);
		splash.setX((1280 / 2) + 150);
		splash.setFont(new Font(Font.DIALOG, Font.ITALIC, 16));
		title2.setX((1280 / 2) - 120);
		title.setY(100);
		splash.setY(150);
		splash.setColor(Color.yellow);
		splash.setRotation(-120);
		title2.setY(170);
		b1.setY(400);
		b1.setX((1280 / 2) - 125);
		b1.addAction(new Runnable() {

			@Override
			public void run() {
				BrickCraft.getInstance().setGUI(null);
				BrickCraft.getInstance().gameInit();
			}
			
		});
		b1.setWidth(250);
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 114));
		title2.setFont(new Font(Font.DIALOG, Font.ITALIC, 32));
		//add(panorama);
		add(title);
		add(title2);
		add(b1);
		add(splash);
	}
	
	public void update(Window win) {
		splash.setRotation(splash.getRotation() + 1);
		super.update(win);
		
	}

}
