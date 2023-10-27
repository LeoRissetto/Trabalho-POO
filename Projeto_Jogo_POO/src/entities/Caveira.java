package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Caveira extends Enemy {

	public Caveira(float x, float y) {
		super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, CAVEIRA);
		initHitbox(x, y, (int) (15 * Game.SCALE), (int) (15 * Game.SCALE));

	}

}