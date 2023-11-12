package objects;

import static entities.EnemyManager.checkBola;
import static objects.ObjectManager.checkCaixaHit;
import static objects.Projectile.CanMoveHere;
import static utilz.Constants.ObjectConstants.*;

public class Tiro extends Projectile{
    
    public Tiro(float x, float y, int aniIndex) {
        super(x, y, TIRO);
        this.aniIndex = aniIndex;
    }   

    @Override
    public void update(int[][] lvlData) {
        
        if(active) {
            if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData) && !checkCaixaHit(hitbox, xSpeed, 0))
                updatePos();
            else
                active = false;
            
            checkBola(this);
        }
    }
}
