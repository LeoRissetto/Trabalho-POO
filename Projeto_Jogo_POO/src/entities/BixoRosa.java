package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class BixoRosa extends Enemy {

	public BixoRosa(float x, float y) {
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, BIXOR);
		initHitbox(x, y, (int) (15 * Game.SCALE), (int) (15 * Game.SCALE));

	}

}