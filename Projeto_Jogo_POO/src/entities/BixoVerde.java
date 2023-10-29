package entities;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.UP;
import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class BixoVerde extends Enemy {
	
	private int enemyAction;
	private boolean left, right, up, down;
	private boolean moving = false;

	public BixoVerde(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, CAVEIRA);
	}
	
	public void update() {
		
		updateAnimationTick();
		setAnimation();
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
	
	private void setAnimation() {
		
		int startAni = enemyAction;

		if (left)
			enemyAction = LEFT;
		
		if(right)
			enemyAction = RIGHT;
		
		if (up)
			enemyAction = UP;
		
		if(down)
			enemyAction = DOWN;

		if (startAni != enemyAction)
			resetAniTick();
	}
	
	private void resetAniTick() {
		
		aniTick = 0;
		aniIndex = 0;
	}

	public int getEnemyAction() {
		
		return enemyAction;
	}
	
}