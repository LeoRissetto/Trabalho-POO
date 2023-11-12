/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;


import static entities.EnemyManager.checkBola;
import static utilz.Constants.ObjectConstants.*;
/**
 *
 * @author leorissetto
 */
public class Tiro extends Projectile{
    
    public Tiro(float x, float y, int aniIndex) {
        super(x, y, TIRO);
        this.aniIndex = aniIndex;
    }   

    @Override
    public void update(int[][] lvlData) {
        
        updatePos();
        checkBola(this);
    }
}
