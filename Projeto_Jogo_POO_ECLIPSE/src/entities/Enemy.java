package entities;

import java.awt.geom.Rectangle2D;

import gamestates.Playing;
import main.Game;

public abstract class Enemy extends Entity {
	
	protected int enemyType;
        protected boolean active;

	public Enemy(float x, float y, int width, int height, int enemyType) {
		
            super(x, y, width, height);
            
            this.enemyType = enemyType;
            this.walkSpeed = 0.35f * Game.SCALE;
            
            initHitbox((float) (width), (float) (height));
            
            active = true;
	}
	
	protected boolean checkHitPlayer(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
            return Playing.getPlayer().getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight());
	}
        
        public void resetEnemy() {
            
            hitbox.x = x;
            hitbox.y = y;
            active = true;
	}
        
        public boolean isActive() {
            
            return active;
        }

        public void setActive(boolean active) {
            
            this.active = active;
        }
        
        public abstract void update(int[][] lvlData);
}