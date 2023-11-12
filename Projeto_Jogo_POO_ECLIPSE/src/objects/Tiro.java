package objects;

import static entities.EnemyManager.checkBola;
import static utilz.Constants.ObjectConstants.*;

public class Tiro extends Projectile{
    
    public Tiro(float x, float y, int aniIndex) {
        super(x, y, TIRO);
        this.aniIndex = aniIndex;
    }   

    @Override
    public void update(int[][] lvlData) {
        
        updatePos();
        checkBola(this);
    }
}
