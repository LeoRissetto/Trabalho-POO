/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;


import static objects.ObjectManager.checkTiroHit;
import static utilz.Constants.ObjectConstants.*;
/**
 *
 * @author leorissetto
 */
public class Bola extends Projectile{
    
    public boolean hit = false;
    
    public Bola(float x, float y, int aniIndex) {
        super(x, y, BOLA);
        this.aniIndex = aniIndex;
    }

    @Override
    public void update(int[][] lvlData) {
        
        if(!hit) {
            //b.setDirection();
            updateMove(lvlData);
        }
        else
            updatePos();

        if(checkTiroHit(this))
            hit = true;    
    }
    
}
