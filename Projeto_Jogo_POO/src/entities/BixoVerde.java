package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class BixoVerde extends Enemy {

	public BixoVerde(float x, float y) {
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, BIXOV);
		initHitbox(x, y, (int) (15 * Game.SCALE), (int) (15 * Game.SCALE));

	}

}