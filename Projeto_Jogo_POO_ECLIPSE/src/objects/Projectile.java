package objects;

import java.awt.geom.Rectangle2D;
import main.Game;
import static utilz.Constants.Directions.*;

public class Projectile {
    
    private Rectangle2D.Float hitbox;
    private int direction;
    private boolean active = true;
    
    public Projectile(float x, float y, int direction) {
        
        hitbox = new Rectangle2D.Float(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
        this.direction = direction;
    }
    
    public void updatePos() {
        
        switch(direction) {
            case UP: 
                hitbox.y -= 0.50f * Game.SCALE;
                break;
                
            case DOWN:
                hitbox.y += 0.50f * Game.SCALE;
                break;
                
            case LEFT:
                hitbox.x -= 0.50f * Game.SCALE;
                break;
                
            case RIGHT:
                hitbox.x += 0.50f * Game.SCALE;
                break;
        }
    }
    
    public void setPos(float x, float y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active; 
    }
}
