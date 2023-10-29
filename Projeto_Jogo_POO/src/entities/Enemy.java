package entities;

public abstract class Enemy extends Entity {
	protected int aniIndex, enemyState, enemyType;
	protected int aniTick, aniSpeed = 25;

	public Enemy(float x, float y, int width, int height, int enemyType) {
		
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
	}

	public int getAniIndex() {
		
		return aniIndex;
	}

	public int getEnemyState() {
		
		return enemyState;
	}
	
}