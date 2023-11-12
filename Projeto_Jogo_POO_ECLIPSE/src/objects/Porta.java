package objects;

import gamestates.Playing;
import main.Game;
import static utilz.Constants.ObjectConstants.*;
import static objects.ObjectManager.openDoor;

public class Porta extends GameObject{
    
    public Porta(float x, float y) {
        super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, PORTA);
        aniIndex = 0;
    }
    
    @Override
    public void update(int[][] lvlData){
        if(openDoor()) {
            aniIndex = 1;
        }
        if(getAniIndex() == 1)
            if(hitbox.intersects(Playing.getPlayer().getHitbox().x + Playing.getPlayer().getXSpeed(), Playing.getPlayer().getHitbox().y + Playing.getPlayer().getYSpeed(), Playing.getPlayer().getHitbox().width, Playing.getPlayer().getHitbox().height))
                ObjectManager.getPlaying().setLevelCompleted(true);
    }
}
