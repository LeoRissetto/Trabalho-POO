package objects;

import gamestates.Playing;
import static utilz.Constants.ObjectConstants.*;
import static objects.ObjectManager.checkCaixaHit;

public class Fogo extends Projectile{
    
    public Fogo(float x, float y, int aniIndex) {
        super(x, y, FOGO);
        this.aniIndex = aniIndex;
    }

    @Override
    public void update(int[][] lvlData) {
        
        if(isActive()) {
            if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData) && !checkCaixaHit(hitbox, xSpeed, 0))
                updatePos();
            else
                active = false;
            
            if(checkHitPlayer(getHitbox(), 0, 0)) {
                Playing.getPlayer().setAlive(false);
            }
        }
    }
}
