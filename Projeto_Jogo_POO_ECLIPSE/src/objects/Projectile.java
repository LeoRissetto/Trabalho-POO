package objects;

import static entities.EnemyManager.checkEnemyHit;

import gamestates.Playing;
import main.Game;

import static objects.ObjectManager.checkCaixaHit;
import static objects.ObjectManager.checkCoracaoHit;

import static utilz.Constants.Directions.*;

public abstract class Projectile extends GameObject{
    
    protected float xSpeed = 0, ySpeed = 0;
    
    public Projectile(float x, float y, int objectType) {
        
        super(x, y, Game.TILES_SIZE - 7, Game.TILES_SIZE - 7, objectType);
    }
    
    public void updatePos() {
        
        switch(aniIndex) {
            case UP -> {
                hitbox.y -= 0.60f * Game.SCALE;
                ySpeed = -0.60f * Game.SCALE;
            }    
            case DOWN -> {
                hitbox.y += 0.60f * Game.SCALE;
                ySpeed = 0.60f * Game.SCALE;
            }
                
            case LEFT -> {
                hitbox.x -= 0.60f * Game.SCALE;
                xSpeed = -0.60f * Game.SCALE;
            }
                
            case RIGHT -> {
                hitbox.x += 0.60f * Game.SCALE;
                xSpeed = -0.60f * Game.SCALE;
            }
        }
    }
    
    public void updateMove(int[][] lvlData) {

	if(!checkCaixaHit(hitbox, Playing.getPlayer().getXSpeed(), Playing.getPlayer().getYSpeed()) && CanMoveHere(hitbox.x + Playing.getPlayer().getXSpeed(), hitbox.y + Playing.getPlayer().getYSpeed(), hitbox.width, hitbox.height, lvlData) &&
                checkBoxHit() && !checkCoracaoHit(hitbox, Playing.getPlayer().getXSpeed(), Playing.getPlayer().getYSpeed())){
            if (!checkEnemyHit(hitbox, Playing.getPlayer().getXSpeed(), Playing.getPlayer().getYSpeed())) {
                hitbox.x += Playing.getPlayer().getXSpeed();
                hitbox.y += Playing.getPlayer().getYSpeed();
            } else {
            }
        }
    }
    
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        
        if (!IsSolid(x, y, lvlData))
                if (!IsSolid(x + width, y + height, lvlData))
                        if (!IsSolid(x + width, y, lvlData))
                                if (!IsSolid(x, y + height, lvlData))
                                        return true;
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        
        if (x < 0 || x >= Game.GAME_WIDTH)
                return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
                return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        return !(value == 35 || value == 36 || value == 48 || value == 49 || value == 2 || value == 14 || value == 15);
    }
    
    private boolean checkBoxHit() {
        
        return hitbox.intersects(Playing.getPlayer().getHitbox().x + Playing.getPlayer().getXSpeed(), Playing.getPlayer().getHitbox().y + Playing.getPlayer().getYSpeed(), Playing.getPlayer().getHitbox().width, Playing.getPlayer().getHitbox().height);
    }
    
    public void setPos(float x, float y) {
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void setActive(boolean active) {
        this.active = active; 
    }

    @Override
    public abstract void update(int[][] lvlData); 
}
