/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import gamestates.Playing;
import static utilz.Constants.ObjectConstants.*;
/**
 *
 * @author leorissetto
 */
public class Fogo extends Projectile{
    
    public Fogo(float x, float y, int aniIndex) {
        super(x, y, FOGO);
        this.aniIndex = aniIndex;
    }

    @Override
    public void update(int[][] lvlData) {
        
        if(isActive()) {
            updatePos();
            if(checkHitPlayer(getHitbox(), 0, 0)) {
                Playing.getPlayer().setAlive(false);
            }
        }
    }
}
