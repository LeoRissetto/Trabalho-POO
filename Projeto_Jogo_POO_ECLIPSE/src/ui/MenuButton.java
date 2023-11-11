package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.UI.Buttons.*;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;

public class MenuButton {
	
	private BufferedImage imgs;
	
	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		
		loadImgs();
	}

	private void loadImgs() {
		// TODO Auto-generated method stub
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		imgs = temp.getSubimage(0, 0, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs, Game.TILES_SIZE * (Game.TILES_IN_WIDTH - 10), Game.TILES_SIZE * (Game.TILES_IN_HEIGHT - 4), B_WIDTH, B_HEIGHT, null);
	}
}
