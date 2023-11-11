package objects;

import main.Game;
import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

public class Agua extends GameObject{
    
    public Agua(float x, float y) {
        super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, AGUA);
    }
    
    public void update(){
        
        updateAnimationTick();
    }
    
    private void updateAnimationTick() {
		
		aniTick++;
		
		if (aniTick >= ANI_SPEED + 50) {
			
			aniTick = 0;
			aniIndex++;
			
			if (aniIndex >= 6) {
				
				aniIndex = 0;
			}
		}
	}
}
