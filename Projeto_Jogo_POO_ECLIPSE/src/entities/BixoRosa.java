package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static objects.ObjectManager.openChest;

import gamestates.Playing;
import main.Game;
import static objects.ObjectManager.addFogo;

public class BixoRosa extends Enemy {

	public BixoRosa(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BIXO_ROSA);
		tileY = (int) (hitbox.y / Game.TILES_SIZE);
		tileX = (int) (hitbox.x / Game.TILES_SIZE);
		state = IDLE;
		walkDir = RIGHT;
	}
	
	public void update() {
            
            if(openChest())
                state = MOVING;
            else
                state = MOVING;
                
            if(state == MOVING)
                shoot();
	}
	
	private void shoot() {
		
		switch(walkDir) {
		
		case UP -> {
                    if(Playing.getPlayer().getHitbox().intersects(x, 0, width, y))
                        addFogo(hitbox.x, hitbox.y, walkDir);
                }
			
		case DOWN -> {
                    if(Playing.getPlayer().getHitbox().intersects(x, y, width, Game.GAME_HEIGHT - y))
                        addFogo(hitbox.x, hitbox.y, walkDir);
                }
			
		case LEFT -> {
                    if(Playing.getPlayer().getHitbox().intersects(0, y, x, height))
                        addFogo(hitbox.x, hitbox.y, walkDir);
                }
			
		case RIGHT -> {
                    if(Playing.getPlayer().getHitbox().intersects(x, y, Game.GAME_WIDTH - x, height))
                        addFogo(hitbox.x, hitbox.y, walkDir);                }
            }
	}	
}