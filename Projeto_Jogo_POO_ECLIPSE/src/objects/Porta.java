package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;
import static objects.ObjectManager.openDoor;

public class Porta extends GameObject{
    
    public Porta(float x, float y) {
        super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, PORTA);
        aniIndex = 1;
    }
    
    public void update(){
        if(openDoor()) {
            aniIndex = 1;
        }
    }
}
