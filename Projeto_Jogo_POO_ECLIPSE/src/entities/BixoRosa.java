package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static objects.ObjectManager.openChest;

import gamestates.Playing;
import main.Game;
import static objects.ObjectManager.addFogo;
import static utilz.Constants.ANI_SPEED;

public class BixoRosa extends Enemy {

    public BixoRosa(float x, float y, int walkDir) {

            super(x, y, Game.TILES_SIZE, Game.TILES_SIZE, BIXO_ROSA);
            tileY = (int) (hitbox.y / Game.TILES_SIZE);
            tileX = (int) (hitbox.x / Game.TILES_SIZE);
            state = IDLE;
            this.walkDir = walkDir;
    }

    @Override
    public void update(int[][] lvlData) {

        if(openChest())
            state = MOVING;
        else
            state = IDLE;

        if(state == MOVING)
            shoot();
    }

    private void shoot() {

            switch(walkDir) {

            case UP -> {
                if(Playing.getPlayer().getHitbox().intersects(x, 0, width, y))
                    updateAnimationTick();
            }

            case DOWN -> {
                if(Playing.getPlayer().getHitbox().intersects(x, y, width, Game.GAME_HEIGHT - y))
                    updateAnimationTick();
            }

            case LEFT -> {
                if(Playing.getPlayer().getHitbox().intersects(0, y, x, height))
                    updateAnimationTick();
            }

            case RIGHT -> {
                if(Playing.getPlayer().getHitbox().intersects(x, y, Game.GAME_WIDTH - x, height))
                    updateAnimationTick();               
            }
        }
    }

    private void updateAnimationTick() {

            aniTick++;

            if (aniTick >= ANI_SPEED + 15) {

                    aniTick = 0;
                    aniIndex++;

                    if (aniIndex >= 5) {

                            aniIndex = 0;
                            addFogo(hitbox.x, hitbox.y, walkDir); 
                    }
            }
    }
}