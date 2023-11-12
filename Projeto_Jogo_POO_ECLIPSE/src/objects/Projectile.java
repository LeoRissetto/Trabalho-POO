package objects;

import static entities.EnemyManager.checkEnemyHit;

import gamestates.Playing;
import main.Game;

import static objects.ObjectManager.checkCaixaHit;
import static objects.ObjectManager.checkCoracaoHit;

import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.CanMoveHere;

public abstract class Projectile extends GameObject{
    
    public Projectile(float x, float y, int objectType) {
        
        super(x, y, Game.TILES_SIZE - 1, Game.TILES_SIZE - 1, objectType);
    }
    
    public void updatePos() {
        
        switch(aniIndex) {
            case UP -> hitbox.y -= 0.50f * Game.SCALE;
                
            case DOWN -> hitbox.y += 0.50f * Game.SCALE;
                
            case LEFT -> hitbox.x -= 0.50f * Game.SCALE;
                
            case RIGHT -> hitbox.x += 0.50f * Game.SCALE;
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
