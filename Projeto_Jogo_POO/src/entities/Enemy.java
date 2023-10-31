package entities;

import java.awt.geom.Rectangle2D;

import gamestates.Playing;
import main.Game;

public abstract class Enemy extends Entity {
	
	protected int enemyType;

	public Enemy(float x, float y, int width, int height, int enemyType) {
		
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(width, height);
		this.walkSpeed = 0.35f * Game.SCALE;
	}
	
	protected boolean checkHitPlayer(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
		if(Playing.getPlayer().getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
			return true;
		else
			return false;
	}
	
}