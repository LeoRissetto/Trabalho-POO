package objects;

import gamestates.Playing;
import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Coracao extends GameObject{
    
        private final boolean poder;

	public Coracao(float x, float y, boolean poder) {
		
		super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, CORACAO);
                this.poder = poder;
	}
	
        @Override
	public void update(int[][] lvlData) {
            
            if(isCollected() && active){
                active = false;
                if(poder)
                    Playing.getPlayer().tiros = 2;
            }
	}
	
        private boolean isCollected() {
            
            return Playing.getPlayer().getHitbox().contains(hitbox.getCenterX(), hitbox.getCenterY());
        }
}
