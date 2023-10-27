package entities;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
import entities.Entity;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] caveiraArr;
	private BufferedImage[][] snakeArr;
	private BufferedImage[][] bixoRosaArr;
	private BufferedImage[][] bixoVerdeArr;
	
	private ArrayList<Caveira> caveiras = new ArrayList<>();
	private ArrayList<Snake> snakes = new ArrayList<>();
	private ArrayList<BixoRosa> bixosRosa = new ArrayList<>();
	private ArrayList<BixoVerde> bixosVerde = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		caveiras = LoadSave.GetCrabs();
		snakes = LoadSave.GetSnakes();
		bixosRosa = LoadSave.GetBixosRosa();
		bixosVerde = LoadSave.GetBixosVerde();

	}

	public void update(int[][] lvlData) {
		for (Caveira c : caveiras)
			c.update(lvlData);
		for (Snake s : snakes)
			s.update(lvlData);
		for (BixoRosa r : bixosRosa)
			r.update(lvlData);
		for (BixoVerde v : bixosVerde)
			v.update(lvlData);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
		drawsnakes(g, xLvlOffset);
		drawRosas(g, xLvlOffset);
		drawVerde(g, xLvlOffset);
	}

	private void drawVerde(Graphics g, int xLvlOffset) {
		for (BixoVerde v : bixosVerde) {
			g.drawImage(bixoVerdeArr[v.getEnemyState()][v.getAniIndex()], (int) v.getHitbox().x - xLvlOffset - 0, (int) v.getHitbox().y - 0, ENEMY_WIDTH,
					ENEMY_HEIGHT, null);
			v.drawHitbox(g);
		}
		
	}

	private void drawRosas(Graphics g, int xLvlOffset) {
		for (BixoRosa r : bixosRosa) {
			g.drawImage(bixoRosaArr[r.getEnemyState()][r.getAniIndex()], (int) r.getHitbox().x - xLvlOffset - 0, (int) r.getHitbox().y - 0, ENEMY_WIDTH,
					ENEMY_HEIGHT, null);
			r.drawHitbox(g);
		}
		
	}

	private void drawsnakes(Graphics g, int xLvlOffset) {
		for (Snake s : snakes) {
			g.drawImage(snakeArr[0][s.getAniIndex()], (int) s.getHitbox().x - xLvlOffset - 0, (int) s.getHitbox().y - 0, ENEMY_WIDTH,
					ENEMY_HEIGHT, null);
			s.drawHitbox(g);
		}
		
	}

	private void drawCrabs(Graphics g, int xLvlOffset) {
		for (Caveira c : caveiras) {
			g.drawImage(caveiraArr[0][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - 0, (int) c.getHitbox().y - 0, ENEMY_WIDTH,
					ENEMY_HEIGHT, null);
			c.drawHitbox(g);
		}

	}

	private void loadEnemyImgs() {
		caveiraArr = new BufferedImage[1][3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CAVEIRA_SPRITE);
		for (int j = 0; j < caveiraArr.length; j++)
			for (int i = 0; i < caveiraArr[j].length; i++)
				caveiraArr[j][i] = temp.getSubimage(i * ENEMY_WIDTH_DEFAULT, j * ENEMY_HEIGHT_DEFAULT, ENEMY_WIDTH_DEFAULT, ENEMY_HEIGHT_DEFAULT);
		
		snakeArr = new BufferedImage[1][4];
		BufferedImage temps = LoadSave.GetSpriteAtlas(LoadSave.SNAKE_SPRITE);
		for (int j = 0; j < snakeArr.length; j++)
			for (int i = 0; i < snakeArr[j].length; i++)
				snakeArr[j][i] = temps.getSubimage(i * ENEMY_WIDTH_DEFAULT, j * ENEMY_HEIGHT_DEFAULT, ENEMY_WIDTH_DEFAULT, ENEMY_HEIGHT_DEFAULT);
		
		bixoRosaArr = new BufferedImage[2][4];
		BufferedImage tempr = LoadSave.GetSpriteAtlas(LoadSave.BIXOROSA_SPRITE);
		for (int j = 0; j < bixoRosaArr.length; j++)
			for (int i = 0; i < bixoRosaArr[j].length; i++)
				bixoRosaArr[j][i] = tempr.getSubimage(i * ENEMY_WIDTH_DEFAULT, j * ENEMY_HEIGHT_DEFAULT, ENEMY_WIDTH_DEFAULT, ENEMY_HEIGHT_DEFAULT);
		
		bixoVerdeArr = new BufferedImage[4][4];
		BufferedImage tempv = LoadSave.GetSpriteAtlas(LoadSave.BIXOVERDE_SPRITE);
		for (int j = 0; j < bixoVerdeArr.length; j++)
			for (int i = 0; i < bixoVerdeArr[j].length; i++)
				bixoVerdeArr[j][i] = tempv.getSubimage(i * ENEMY_WIDTH_DEFAULT, j * ENEMY_HEIGHT_DEFAULT, ENEMY_WIDTH_DEFAULT, ENEMY_HEIGHT_DEFAULT);
	}
}
