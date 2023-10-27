package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Caveira;
import entities.Snake;
import entities.BixoRosa;
import entities.BixoVerde;
import main.Game;
import static utilz.Constants.EnemyConstants.*;

public class LoadSave {

	public static final String PLAYER_ATLAS = "lolo_spritesheet.png";
	public static final String LEVEL_ATLAS = "spritesheet-6.png";
	public static final String LEVEL_ONE_DATA = "levelBase2enemy.png";
	public static final String MENU_BUTTONS = "playButton.jpg";
	public static final String MENU_BACKGROUND = "MenuScreen.png";
	public static final String CAVEIRA_SPRITE = "caveira.png";
	public static final String SNAKE_SPRITE = "snake.png";
	public static final String BIXOROSA_SPRITE = "bixoRosa.png";
	public static final String BIXOVERDE_SPRITE = "bixoVerde.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static ArrayList<Caveira> GetCrabs() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Caveira> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == CAVEIRA)
					list.add(new Caveira(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}
	
	public static ArrayList<Snake> GetSnakes() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Snake> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == SNAKE)
					list.add(new Snake(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}
	
	public static ArrayList<BixoRosa> GetBixosRosa() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<BixoRosa> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == BIXOR)
					list.add(new BixoRosa(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}
	
	public static ArrayList<BixoVerde> GetBixosVerde() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<BixoVerde> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == BIXOV)
					list.add(new BixoVerde(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}

	public static int[][] GetLevelData() {
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 64)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}