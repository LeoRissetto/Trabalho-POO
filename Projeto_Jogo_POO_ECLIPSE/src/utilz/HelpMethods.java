package utilz;

import entities.BixoRosa;
import entities.BixoVerde;
import entities.Caveira;
import entities.Snake;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Game;
import objects.Agua;
import objects.Bau;
import objects.Caixa;
import objects.Coracao;
import objects.Porta;
import static utilz.Constants.EnemyConstants.BIXO_ROSA;
import static utilz.Constants.EnemyConstants.BIXO_VERDE;
import static utilz.Constants.EnemyConstants.CAVEIRA;
import static utilz.Constants.EnemyConstants.SNAKE;
import static utilz.Constants.ObjectConstants.AGUA;
import static utilz.Constants.ObjectConstants.BAU;
import static utilz.Constants.ObjectConstants.CAIXA;
import static utilz.Constants.ObjectConstants.CORACAO;
import static utilz.Constants.ObjectConstants.PORTA;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsSolid(x, y, lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;

		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		int value = lvlData[(int) yIndex][(int) xIndex];

		return !(value == 35 || value == 36 || value == 48 || value == 49);
	}
        
        public static int[][] GetLevelData(BufferedImage img) {
            
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

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
        
        public static ArrayList<Snake> GetSnakes(BufferedImage img) {
		
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
	
	public static ArrayList<Caveira> GetCaveiras(BufferedImage img) {
		
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
	
	public static ArrayList<BixoVerde> GetBixosVerde(BufferedImage img) {
		
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
	
	public static ArrayList<BixoRosa> GetBixosRosa(BufferedImage img) {
		
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
        
        public static ArrayList<Coracao> GetCoracoes(BufferedImage img) {
		
		ArrayList<Coracao> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == CORACAO)
					list.add(new Coracao(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}
        
        public static ArrayList<Agua> GetAguas(BufferedImage img) {
		
		ArrayList<Agua> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == AGUA)
					list.add(new Agua(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}
        
        public static ArrayList<Caixa> GetCaixas(BufferedImage img) {
		
		ArrayList<Caixa> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == CAIXA)
					list.add(new Caixa(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}
        
        public static ArrayList<Porta> GetPortas(BufferedImage img) {
		
		ArrayList<Porta> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == PORTA)
					list.add(new Porta(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}
        
        public static ArrayList<Bau> GetBaus(BufferedImage img) {
		
		ArrayList<Bau> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == BAU)
					list.add(new Bau(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;
	}
        
        public static Point GetPlayerSpawn(BufferedImage img) {
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == 100)
					return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
			}
		return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
	}
	
}