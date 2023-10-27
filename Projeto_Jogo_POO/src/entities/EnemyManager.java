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
	private BufferedImage[][] crabbyArr;
	private ArrayList<Crabby> crabbies = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		crabbies = LoadSave.GetCrabs();

	}

	public void update(int[][] lvlData) {
		for (Crabby c : crabbies)
			c.update(lvlData);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
	}

	private void drawCrabs(Graphics g, int xLvlOffset) {
		for (Crabby c : crabbies) {
			g.drawImage(crabbyArr[0][0], (int) c.getHitbox().x - xLvlOffset - 0, (int) c.getHitbox().y - 0, CRABBY_WIDTH,
					CRABBY_HEIGHT, null);
			c.drawHitbox(g);
		}

	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[1][3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArr.length; j++)
			for (int i = 0; i < crabbyArr[j].length; i++)
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
	}
}
