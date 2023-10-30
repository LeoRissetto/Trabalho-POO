package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class EnemyManager {

	private BufferedImage[] snakeArr, caveiraArr;
	private BufferedImage[][] bixoVerdeArr, bixoRosaArr;
	private ArrayList<Snake> snakes = new ArrayList<>();
	private ArrayList<Caveira> caveiras = new ArrayList<>();
	private ArrayList<BixoVerde> bixosVerde = new ArrayList<>();
	private ArrayList<BixoRosa> bixosRosa = new ArrayList<>();

	public EnemyManager(Playing playing) {
		
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		
		snakes = LoadSave.GetSnakes();
		caveiras = LoadSave.GetCaveiras();
		bixosVerde = LoadSave.GetBixosVerde();
		bixosRosa = LoadSave.GetBixosRosa();
	}

	public void update(int[][] lvlData) {
		
		for (Snake s : snakes)
			s.update();
		
		for (Caveira c : caveiras)
			c.update(lvlData);
		
		for (BixoVerde bv : bixosVerde)
			bv.update(lvlData);
		
		for (BixoRosa br : bixosRosa)
			br.update();
	}

	public void draw(Graphics g) {
		
		drawSnakes(g);
		drawCaveiras(g);
		drawBixosVerde(g);
		drawBixosRosa(g);
	}

	private void drawSnakes(Graphics g) {
		
		for (Snake s : snakes)
			g.drawImage(snakeArr[s.getAniIndex()], (int) s.getHitbox().x, (int) s.getHitbox().y, s.width, s.height, null);
	}
	
	private void drawCaveiras(Graphics g) {
		
		for (Caveira c : caveiras)
			g.drawImage(caveiraArr[c.getAniIndex()], (int) c.getHitbox().x, (int) c.getHitbox().y, c.width, c.height, null);
	}
	
	private void drawBixosVerde(Graphics g) {
		
		for (BixoVerde bv : bixosVerde)
			g.drawImage(bixoVerdeArr[bv.getAniIndex()][bv.getWalkDir()], (int) bv.getHitbox().x, (int) bv.getHitbox().y, bv.width, bv.height, null);
	}
	
	private void drawBixosRosa(Graphics g) {
		
		for (BixoRosa br : bixosRosa)
			g.drawImage(bixoRosaArr[br.getAniIndex()][br.getEnemyAction()], (int) br.getHitbox().x, (int) br.getHitbox().y, br.width, br.height, null);
	}
	
	private void loadEnemyImgs() {
		
		snakeArr = new BufferedImage[4];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SNAKE_SPRITE);
		for (int j = 0; j < snakeArr.length; j++)
			snakeArr[j] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, 0, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
		
		caveiraArr = new BufferedImage[3];
		temp = LoadSave.GetSpriteAtlas(LoadSave.CAVEIRA_SPRITE);
		for (int j = 0; j < caveiraArr.length; j++)
			caveiraArr[j] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, 0, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
		
		bixoVerdeArr = new BufferedImage[4][4];
		temp = LoadSave.GetSpriteAtlas(LoadSave.BIXO_VERDE_SPRITE);
		for (int j = 0; j < bixoVerdeArr.length; j++)
			for(int i = 0; i < bixoVerdeArr[j].length; i++)
				bixoVerdeArr[j][i] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, i * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
		
		bixoRosaArr = new BufferedImage[4][2];
		temp = LoadSave.GetSpriteAtlas(LoadSave.BIXO_ROSA_SPRITE);
		for (int j = 0; j < bixoRosaArr.length; j++)
			for(int i = 0; i < bixoRosaArr[j].length; i++)
				bixoRosaArr[j][i] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, i * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
		
	}
	
}