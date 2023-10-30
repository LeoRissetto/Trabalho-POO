package entities;

import main.Game;

public abstract class Enemy extends Entity {
	
	protected int enemyType;

	public Enemy(float x, float y, int width, int height, int enemyType) {
		
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(width, height);
		this.walkSpeed = 0.35f * Game.SCALE;
	}
	
}