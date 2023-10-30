package entities;

import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.*;

import gamestates.Playing;
import main.Game;

public class BixoRosa extends Enemy {
	
	private int enemyAction = MOVING;

	public BixoRosa(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BIXO_ROSA);
	}
	
	public void update() {
		lookLolo();
	}
	
	private void lookLolo() {
		
		if(x > Playing.getPlayer().getHitbox().x)
			if(y > Playing.getPlayer().getHitbox().y)
				aniIndex = 1;
			else
				aniIndex = 0;
		else
			if(y > Playing.getPlayer().getHitbox().y)
				aniIndex = 2;
			else
				aniIndex = 3;
	}

	public int getEnemyAction() {
		return enemyAction;
	}	
	
}