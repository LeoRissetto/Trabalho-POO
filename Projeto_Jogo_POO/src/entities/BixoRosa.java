package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;

import gamestates.Playing;
import main.Game;

public class BixoRosa extends Enemy {

	public BixoRosa(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BIXO_ROSA);
		tileY = (int) (hitbox.y / Game.TILES_SIZE);
		tileX = (int) (hitbox.x / Game.TILES_SIZE);
		state = MOVING;
		walkDir = LEFT;
	}
	
	public void update() {
		
		shoot();
	}
	
	private void shoot() {
		
		switch(walkDir) {
		
		case UP:
			if(tileX == Playing.getPlayer().tileX) {
				if(tileY > Playing.getPlayer().tileY) {
					System.out.println("Atirou");
				}
			}
			break;
			
		case DOWN:
			if(tileX == Playing.getPlayer().tileX) {
				if(tileY < Playing.getPlayer().tileY) {
					System.out.println("Atirou");
				}
			}
			break;
			
		case LEFT:
			if(tileY == Playing.getPlayer().tileY) {
				if(tileX > Playing.getPlayer().tileX) {
					System.out.println("Atirou");
				}
			}
			break;
			
		case RIGHT:
			if(tileY == Playing.getPlayer().tileY) {
				if(tileX < Playing.getPlayer().tileX) {
					System.out.println("Atirou");
				}
			}
			break;
		}
	}	
}