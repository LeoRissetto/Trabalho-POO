package objects;

import gamestates.Playing;
import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Coracao extends GameObject{

	public Coracao(float x, float y) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, CORACAO);
	}
	
	public void update() {
            
            if(isCollected() && active == true){
                active = false;
            }
	}
	
        private boolean isCollected() {
            
            return Playing.getPlayer().getHitbox().contains(hitbox.getCenterX(), hitbox.getCenterY());
        }
}
