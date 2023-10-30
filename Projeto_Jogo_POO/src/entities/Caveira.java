package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.*;

import main.Game;

public class Caveira extends Enemy {
	
	private float walkSpeed = 0.35f * Game.SCALE;
	private int walkDir = LEFT;

	public Caveira(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, CAVEIRA);
		enemyState = MOVING;
	}
	
	public void update(int[][] lvlData) {
		
		updateMove(lvlData);
		
		if(enemyState == MOVING)
			updateAnimationTick();
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
	
	private void updateMove(int[][] lvlData) {
		
		switch (enemyState) {
		
			case IDLE:
				break;
				
			case MOVING:
				
				float xSpeed = 0;
				
				if (walkDir == LEFT)
					xSpeed = -walkSpeed;
				else
					xSpeed = walkSpeed;

				if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
				    hitbox.x += xSpeed;
				else
				    changeWalkDir();
		}
	}

	private void changeWalkDir() {
		
		if (walkDir == LEFT)
			walkDir = RIGHT;
		
		else
			walkDir = LEFT;
	}
	
}