package utilz;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.BixoRosa;
import entities.BixoVerde;
import entities.Caveira;
import entities.Snake;
import main.Game;
import static utilz.Constants.EnemyConstants.*;

public class LoadSave {

	public static final String PLAYER_ATLAS = "lolo_spritesheet.png";
	public static final String LEVEL_ATLAS = "textures.png";
	public static final String LEVEL_ONE_DATA = "levelBase.png";
	public static final String MENU_BUTTONS = "playButton.jpg";
	public static final String MENU_BACKGROUND = "menuScreen.png";
	public static final String SNAKE_SPRITE = "snake.png";
	public static final String CAVEIRA_SPRITE = "caveira.png";
	public static final String BIXO_VERDE_SPRITE = "bixoVerde.png";
	public static final String BIXO_ROSA_SPRITE = "bixoRosa.png";

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
	
	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++) {
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];		
			}
		
		for(File f : files)
			System.out.println("file: " + f.getName());

		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
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
	
	public static ArrayList<Caveira> GetCaveiras() {
		
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
	
	public static ArrayList<BixoVerde> GetBixosVerde() {
		
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<BixoVerde> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == BIXO_VERDE)
					list.add(new BixoVerde(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
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
				if (value == BIXO_ROSA)
					list.add(new BixoRosa(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}

}