package objects;

import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {
    
    protected float x, y;
    protected int width, height;
    protected int objectType;
    protected Rectangle2D.Float hitbox;
    protected int aniIndex, aniTick;
    protected boolean active;
    
    public GameObject(float x, float y, int width, int height, int objectType) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
                this.objectType = objectType;
		active = false;
                initHitbox(width, height);
                active = true;
    }
    
    protected void drawHitbox(Graphics g) {
	// For debugging the hitbox
	g.setColor(Color.PINK);
	g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);

    }
    
    protected boolean checkHitPlayer(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
        return Playing.getPlayer().getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight());
    }

    private void initHitbox(float width, float height) {
	hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    
    public Rectangle2D.Float getHitbox() {
	return hitbox;
    }
    
    public int getAniIndex() {
	return aniIndex;
    }
    
    public void resetObject() {
        hitbox.x = x;
        hitbox.y = y;
        active = true;
        aniIndex = 0;
    }
    
    public boolean isActive() {
        return active;
    }
}
