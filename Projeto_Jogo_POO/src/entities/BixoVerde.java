package entities;

import static utilz.Constants.Directions.*;
import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;

import main.Game;

public class BixoVerde extends Enemy {

	public BixoVerde(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BIXO_VERDE);
		state = MOVING;
		walkDir = UP;
	}
	
	public void update(int[][] lvlData) {
		
		updateMove(lvlData);
		
		if(state == MOVING)
			updateAnimationTick();
		else
			sleeping();
	}

	private void updateAnimationTick() {
		
		aniTick++;
		
		if (aniTick >= ANI_SPEED) {
			
			aniTick = 0;
			aniIndex++;
			
			if (aniIndex >= GetSpriteAmount(enemyType, state)) {
				
				aniIndex = 0;
			}
		}
	}
	
	private void sleeping() {
		
		aniTick++;
		
		if (aniTick >= ANI_SPEED + 100) {
			
			aniTick = 0;
			aniIndex++;
			
			if (aniIndex >= GetSpriteAmount(enemyType, state)) {
				
				aniIndex = 2;
			}
		}
	}
	
	private void updateMove(int[][] lvlData) {
		
		switch (state) {
		
			case IDLE:
				break;
				
			case MOVING:
				
				float xSpeed = 0;
				float ySpeed = 0;
				
				if (walkDir == LEFT)
					xSpeed = -walkSpeed;
				
				else if(walkDir == RIGHT)
					xSpeed = walkSpeed;
				
				else if(walkDir == UP)
					ySpeed = -walkSpeed;
				
				else
					ySpeed = walkSpeed;

				if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
					
					hitbox.x += xSpeed;
					hitbox.y += ySpeed;
				}
			
				else
				    changeWalkDir();
		}
	}

	private void changeWalkDir() {
		
		if (walkDir == LEFT)
			walkDir = RIGHT;
		
		else if(walkDir == RIGHT)
			walkDir = LEFT;
		
		else if(walkDir == DOWN)
			walkDir = UP;
		
		else
			walkDir = DOWN;
	}
	
}