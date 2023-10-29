package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Caveira extends Enemy {

	public Caveira(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, CAVEIRA);
	}
	
	public void update() {
		updateAnimationTick();
	}
	
	private void updateAnimationTick() {
		
		aniTick++;
		
		if (aniTick >= aniSpeed) {
			
			aniTick = 0;
			aniIndex++;
			
			if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
				
				aniIndex = 0;
			}
		}
	}
	
}