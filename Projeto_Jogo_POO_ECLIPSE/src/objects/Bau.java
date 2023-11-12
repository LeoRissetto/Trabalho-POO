package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;
import static objects.ObjectManager.openChest;

public class Bau extends GameObject{
    
    public Bau(float x, float y) {
        super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BAU);
        aniIndex = 0;
    }
    
    @Override
    public void update(int[][] lvlData){
        if(openChest()) {
            if(aniIndex == 0)
                aniIndex = 1;
            if(checkHitPlayer(hitbox, 0, 0))
                if(aniIndex == 1)
                    aniIndex = 2;
        }
    }
}
