package objects;

import static objects.ObjectManager.checkTiroHit;
import static utilz.Constants.ObjectConstants.*;

public class Bola extends Projectile{
    
    public boolean hit = false;
    
    public Bola(float x, float y, int aniIndex) {
        super(x, y, BOLA);
        this.aniIndex = aniIndex;
    }

    @Override
    public void update(int[][] lvlData) {
        
        if(!hit) {
            //b.setDirection();
            updateMove(lvlData);
        }
        else
            updatePos();

        if(checkTiroHit(this))
            hit = true;    
    }
    
}
