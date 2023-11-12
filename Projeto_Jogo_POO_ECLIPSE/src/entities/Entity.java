package entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected int state;
    protected int aniIndex, aniTick;
    protected int walkDir;
    protected boolean isAlive;

    protected float walkSpeed;

    protected int tileX, tileY;

    public Entity(float x, float y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            isAlive = true;
    }

    protected void drawHitbox(Graphics g) {
            // For debugging the hitbox
            g.setColor(Color.PINK);
            g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);

    }

    protected void initHitbox(float width, float height) {
            hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
            return hitbox;
    }

    public int getAniIndex() {
            return aniIndex;
    }

    public int getState() {
            return state;
    }

    public int getWalkDir() {
            return walkDir;
    }
	
}