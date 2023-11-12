package entities;

import static utilz.Constants.EnemyConstants.*;

import gamestates.Playing;
import main.Game;

public class Snake extends Enemy {

	public Snake(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, SNAKE);
	}
	
        @Override
	public void update(int[][] lvlData) {
		lookLolo();
	}
	
	private void lookLolo() {
		
		if(x > Playing.getPlayer().getHitbox().x)
			if(y > Playing.getPlayer().getHitbox().y)
				aniIndex = 0;
			else
				aniIndex = 1;
		else
			if(y > Playing.getPlayer().getHitbox().y)
				aniIndex = 3;
			else
				aniIndex = 2;
	}
	
}