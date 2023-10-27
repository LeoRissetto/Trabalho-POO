package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constants.Directions.*;

import main.Game;

public abstract class Enemy extends Entity {
	private int aniIndex, enemyState, enemyType;
	private int aniTick, aniSpeed = 25;
	private float walkSpeed = 0.35f * Game.SCALE;
	private int walkDir = LEFT;

	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);

	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
				aniIndex = 0;
			}
		}
	}

	public void update(int[][] lvlData) {
		if(enemyType == 0) {
			updateMove(lvlData);
		}
		updateAnimationTick();

	}

	private void updateMove(int[][] lvlData) {
		switch (enemyState) {
		case IDLE:
			enemyState = RUNNING;
			break;
		case RUNNING:
			float xSpeed = 0;

			if (walkDir == LEFT)
				xSpeed = -walkSpeed;
			else
				xSpeed = walkSpeed;

			if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			    hitbox.x += xSpeed;
			} else {
			    changeWalkDir();
			}
		}
	}


	private void changeWalkDir() {
		if (walkDir == LEFT)
			walkDir = RIGHT;
		else
			walkDir = LEFT;

	}

	public int getAniIndex() {
		return aniIndex;
	}

	public int getEnemyState() {
		return enemyState;
	}

}
