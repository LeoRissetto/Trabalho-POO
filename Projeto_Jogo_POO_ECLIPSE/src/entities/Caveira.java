package entities;

import static utilz.Constants.EnemyConstants.*;


import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.*;

import static objects.ObjectManager.checkCaixaHit;
import static objects.ObjectManager.openChest;

import gamestates.Playing;

import static utilz.Constants.ANI_SPEED;

import main.Game;

public class Caveira extends Enemy {

    public Caveira(float x, float y) {

        super(x, y, Game.TILES_SIZE, Game.TILES_SIZE-1, CAVEIRA);
        state = IDLE;
        walkDir = LEFT;
    }

    public void update(int[][] lvlData) {

        updateMove(lvlData);

        if(openChest())
            state = MOVING;
        else
            state = IDLE;

        if(state == MOVING) {

            if(checkHitPlayer(hitbox, 0, 0)) 
                Playing.getPlayer().setAlive(false);
            updateAnimationTick();
        }
    }

    private void updateAnimationTick() {

        aniTick++;

        if (aniTick >= ANI_SPEED) {

                aniTick = 0;
                aniIndex++;

                if (aniIndex >= GetSpriteAmount(enemyType, state)) {

                        aniIndex = 0;
                }
        }
    }

    private void updateMove(int[][] lvlData) {

        switch (state) {

                case IDLE -> {
                }

                case MOVING -> {
                    float xSpeed = 0;

                    if (walkDir == LEFT)
                        xSpeed = -walkSpeed;
                    else
                        xSpeed = walkSpeed;

                    if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData) && !checkCaixaHit(hitbox, xSpeed, 0))
                        hitbox.x += xSpeed;
                    else
                        changeWalkDir();
                }
        }
    }

    private void changeWalkDir() {

        if (walkDir == LEFT)
                walkDir = RIGHT;

        else
                walkDir = LEFT;
    }	
}