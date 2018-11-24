package org.xio.brickcraft.guis;

import java.io.File;
import java.io.IOException;

import org.jhggl.assets.AssetsManager;
import org.jhggl.objects.ProgressBar;
import org.powerhigh.graphics.Texture;
import org.powerhigh.graphics.TextureLoader;
import org.powerhigh.graphics.Interface;
import org.powerhigh.objects.Sprite;

public class Loading extends GUI {
	
	private Sprite image;
	private ProgressBar bar;
	
	public Loading() {
		initComponents();
	}
	
	private void initComponents() {
		Texture mojang = null;
		try {
			mojang = TextureLoader.getTexture("assets/main_menu/mojang.png"); // Assets are'nt loaded, so manually loading.
		} catch (Exception e) {
			e.printStackTrace();
		}
		image = new Sprite(mojang);
		if (mojang != null)
			image.setSize(mojang.getWidth() * 2, mojang.getHeight() * 2);
		else
			image.setSize(512, 512);
		add(image);
		bar = new ProgressBar();
		bar.setSize(310, 50);
		bar.setMaximum(AssetsManager.getMaximum());
		System.out.println("Assets count: " + AssetsManager.getMaximum());
		AssetsManager.onProgressChange(new Runnable() {

			@Override
			public void run() {
				bar.setValue(AssetsManager.getProgress());
			}
			
		});
		add(bar);
	}
	
	public void update(Interface win) {
		image.centerTo(win);
		bar.centerTo(win);
		bar.setY(bar.getY() + 125);
		super.update(win);
	}

}
