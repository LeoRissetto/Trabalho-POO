package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static objects.ObjectManager.checkCaixaHit;
import static objects.ObjectManager.checkCoracaoHit;
import static entities.EnemyManager.checkEnemyHit;
import gamestates.Playing;

public class Caixa extends GameObject{
    
    public Caixa(float x, float y) {
        super(x, y, Game.TILES_SIZE - 1, Game.TILES_SIZE - 1, CAIXA);
    }
    
    @Override
    public void update(int[][] lvlData){
        
        updateMove(lvlData);
    }
    
    private void updateMove(int[][] lvlData) {

	if(!checkCaixaHit(hitbox, Playing.getPlayer().getXSpeed(), Playing.getPlayer().getYSpeed()) && 
                checkBoxHit() && CanMoveHere(hitbox.x + Playing.getPlayer().getXSpeed(), hitbox.y + Playing.getPlayer().getYSpeed(), hitbox.width, hitbox.height, lvlData) &&
                !checkCoracaoHit(hitbox, Playing.getPlayer().getXSpeed(), Playing.getPlayer().getYSpeed())){
            if (!checkEnemyHit(hitbox, Playing.getPlayer().getXSpeed(), Playing.getPlayer().getYSpeed())) {
                hitbox.x += Playing.getPlayer().getXSpeed();
                hitbox.y += Playing.getPlayer().getYSpeed();
            }
        }
    }
    
    private boolean checkBoxHit() {
        
        return hitbox.intersects(Playing.getPlayer().getHitbox().x + Playing.getPlayer().getXSpeed(), Playing.getPlayer().getHitbox().y + Playing.getPlayer().getYSpeed(), Playing.getPlayer().getHitbox().width, Playing.getPlayer().getHitbox().height);
    }
}
