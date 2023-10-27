package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Snake extends Enemy {

	public Snake(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, SNAKE);
		initHitbox(x, y, (int) (15 * Game.SCALE), (int) (15 * Game.SCALE));

	}

}