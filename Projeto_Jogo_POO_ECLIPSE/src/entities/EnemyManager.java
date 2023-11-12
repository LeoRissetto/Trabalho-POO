package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;

import static objects.ObjectManager.addBola;
import objects.Tiro;

import static utilz.Constants.EnemyConstants.*;
import static utilz.LoadSave.GetSpriteArray;
import static utilz.LoadSave.GetSpriteMatrix;

public class EnemyManager {

	private BufferedImage[] snakeArr, caveiraArr;
	private BufferedImage[][] bixoVerdeArr, bixoRosaArr;
        
	private static ArrayList<? extends Enemy> snakes, caveiras, bixosVerde, bixosRosa;

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
		
		updateEnemy(snakes, lvlData);
                updateEnemy(caveiras, lvlData);
                updateEnemy(bixosVerde, lvlData);
                updateEnemy(bixosRosa, lvlData);
	}
        
        private void updateEnemy(ArrayList<? extends Enemy> array, int[][] lvlData) {
            
            for (Enemy a : array)
                if(a.isActive())
                    a.update(lvlData);
        }

	public void draw(Graphics g) {
		
		drawEnemy(snakes, g);
                drawEnemy(caveiras, g);
                drawEnemy(bixosVerde, g);
                drawEnemy(bixosRosa, g);
	}

	private void drawEnemy(ArrayList<? extends Enemy> array, Graphics g) { 
            
            for (Enemy a : array) {
                if(a.isActive())
                    switch(a.enemyType) {
                        
                        case SNAKE -> g.drawImage(snakeArr[a.getAniIndex()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);
                            
                        case CAVEIRA -> g.drawImage(caveiraArr[a.getAniIndex()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);
                            
                        case BIXO_VERDE -> g.drawImage(bixoVerdeArr[a.getAniIndex()][a.getWalkDir()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);
                            
                        case BIXO_ROSA -> g.drawImage(bixoRosaArr[a.getWalkDir()][a.getState()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);             
                    }
                    a.drawHitbox(g);
            }
	}
	
	private void loadEnemyImgs() {
            
                snakeArr = GetSpriteArray(snakeArr, 4, 1, 0, LoadSave.SNAKE_SPRITE);
                caveiraArr = GetSpriteArray(caveiraArr, 3, 1, 0, LoadSave.CAVEIRA_SPRITE);            
                bixoVerdeArr = GetSpriteMatrix(bixoVerdeArr, 4, 4, LoadSave.BIXO_VERDE_SPRITE);    
                bixoRosaArr = GetSpriteMatrix(bixoRosaArr, 4, 2, LoadSave.BIXO_ROSA_SPRITE);
	}
	
	public static boolean checkEnemyHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
		return checkHit(snakes, hitbox, xSpeed, ySpeed) | checkHit(caveiras, hitbox, xSpeed, ySpeed)
                        | checkHit(bixosVerde, hitbox, xSpeed, ySpeed) | checkHit(bixosRosa, hitbox, xSpeed, ySpeed);
	}
        
        private static boolean checkHit(ArrayList<? extends Enemy> array, Rectangle2D hitbox, float xSpeed, float ySpeed) {
            
            boolean temp = false;
            
            for(Enemy a : array)
                if(a.isActive())
                    if(a.hitbox.intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
                        temp = true;
            
            return temp;        
        }

        public void resetAllEnemies() {

            resetEnemy(snakes);
            resetEnemy(caveiras);
            resetEnemy(bixosVerde);
            resetEnemy(bixosRosa);
        }
        
        private void resetEnemy(ArrayList<? extends Enemy> array) {
            for (Enemy a : array)
                if(a.isActive())
                    a.resetEnemy();
        }

        public void killAllEnemies() {

            killEnemy(snakes);
            killEnemy(caveiras);
            killEnemy(bixosVerde);
            killEnemy(bixosRosa);
        }
        
        private void killEnemy(ArrayList<? extends Enemy> array) {
            for (Enemy a : array)
                if(a.isActive())
                    a.setActive(false);
        }

        public static boolean checkBola(Tiro t) {

            boolean temp = false;

            for (Enemy s : snakes)
                if(s.isActive())
                    if(s.getHitbox().intersects(t.getHitbox())){
                        t.setActive(false);
                        addBola(s.hitbox.x, s.hitbox.y, s.walkDir);
                        s.setActive(false);
                        temp = true;
                    }


            for (Enemy c : caveiras)
                if(c.isActive())
                    if(c.getHitbox().intersects(t.getHitbox())){
                        t.setActive(false);
                        addBola(c.hitbox.x, c.hitbox.y, c.walkDir);
                        c.setActive(false);
                        temp = true;
                    }

            return temp;
        }
}