package entities;

import static utilz.Constants.EnemyConstants.*;

import gamestates.Playing;
import main.Game;

public class BixoRosa extends Enemy {

	public BixoRosa(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BIXO_ROSA);
		state = MOVING;
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
	
}