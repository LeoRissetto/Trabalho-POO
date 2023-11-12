package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import main.Game;
import utilz.LoadSave;

public class EnemyManager {

	private BufferedImage[] snakeArr, caveiraArr;
	private BufferedImage[][] bixoVerdeArr, bixoRosaArr;
	private static ArrayList<Snake> snakes = new ArrayList<>();
	private static ArrayList<Caveira> caveiras = new ArrayList<>();
	private static ArrayList<BixoVerde> bixosVerde = new ArrayList<>();
	private static ArrayList<BixoRosa> bixosRosa = new ArrayList<>();

	public EnemyManager(Playing playing) {
		
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		
		snakes = level.getSnakes();
		caveiras = level.getCaveiras();
		bixosVerde = level.getBixosVerde();
		bixosRosa = level.getBixosRosa();
	}

	public void update(int[][] lvlData) {
		
		for (Snake s : snakes)
                    if(s.isActive())
			s.update();
		
		for (Caveira c : caveiras)
                    if(c.isActive())
			c.update(lvlData);
		
		for (BixoVerde bv : bixosVerde)
                    if(bv.isActive())
			bv.update(lvlData);
		
		for (BixoRosa br : bixosRosa)
                    if(br.isActive())
			br.update();
	}

	public void draw(Graphics g) {
		
		drawSnakes(g);
		drawCaveiras(g);
		drawBixosVerde(g);
		drawBixosRosa(g);
	}

	private void drawSnakes(Graphics g) {
		
		for (Snake s : snakes) {
                    if(s.isActive())
			g.drawImage(snakeArr[s.getAniIndex()], (int) s.getHitbox().x, (int) s.getHitbox().y, s.width, s.height, null);
			//s.drawHitbox(g);
		}
	}
	
	private void drawCaveiras(Graphics g) {
		
		for (Caveira c : caveiras) {
                    if(c.isActive())
			g.drawImage(caveiraArr[c.getAniIndex()], (int) c.getHitbox().x, (int) c.getHitbox().y, c.width, c.height, null);
			//c.drawHitbox(g);
		}
	}
	
	private void drawBixosVerde(Graphics g) {
		
		for (BixoVerde bv : bixosVerde) {
                    if(bv.isActive())
			g.drawImage(bixoVerdeArr[bv.getAniIndex()][bv.getWalkDir()], (int) bv.getHitbox().x, (int) bv.getHitbox().y, bv.width, bv.height, null);
			//bv.drawHitbox(g);
		}
	}
	
	private void drawBixosRosa(Graphics g) {
		
		for (BixoRosa br : bixosRosa) {
                    if(br.isActive())
			g.drawImage(bixoRosaArr[br.getWalkDir()][br.getState()], (int) br.getHitbox().x, (int) br.getHitbox().y, br.width, br.height, null);
			//br.drawHitbox(g);
		}
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
	
	public static boolean checkEnemyHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
		boolean temp = false;
		
		for (Snake s : snakes)
                    if(s.isActive())
			if(s.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
				temp = true;
                
                for (Caveira c : caveiras)
                    if(c.isActive())
			if(c.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
				temp = true;
		
		for (BixoVerde bv : bixosVerde)
                    if(bv.isActive())
			if(bv.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
				temp = true;
		
		for (BixoRosa br : bixosRosa)
                    if(br.isActive())
			if(br.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
				temp = true;
		
		return temp;
	}

    public void resetAllEnemies() {
        
        for (Snake s : snakes)
            s.resetEnemy();
		
	for (Caveira c : caveiras)
            c.resetEnemy();
		
	for (BixoVerde bv : bixosVerde)
            bv.resetEnemy();
		
	for (BixoRosa br : bixosRosa)
            br.resetEnemy();
    }
    
    public void killAllEnemies() {
        
        for (Snake s : snakes)
            s.setActive(false);
		
	for (Caveira c : caveiras)
            c.setActive(false);
		
	for (BixoVerde bv : bixosVerde)
            bv.setActive(false);
		
	for (BixoRosa br : bixosRosa)
            br.setActive(false);
    }
	
}