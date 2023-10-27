package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Crabby extends Enemy {

	public Crabby(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
		initHitbox(x, y, (int) (15 * Game.SCALE), (int) (15 * Game.SCALE));

	}

}