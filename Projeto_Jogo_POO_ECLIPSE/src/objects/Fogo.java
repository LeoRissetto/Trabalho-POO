package objects;

import gamestates.Playing;
import static utilz.Constants.ObjectConstants.*;

public class Fogo extends Projectile{
    
    public Fogo(float x, float y, int aniIndex) {
        super(x, y, FOGO);
        this.aniIndex = aniIndex;
    }

    @Override
    public void update(int[][] lvlData) {
        
        if(isActive()) {
            updatePos();
            if(checkHitPlayer(getHitbox(), 0, 0)) {
                Playing.getPlayer().setAlive(false);
            }
        }
    }
}
