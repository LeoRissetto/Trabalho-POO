package entities;

import static utilz.Constants.Directions.*;

import static utilz.Constants.ANI_SPEED;
import static utilz.HelpMethods.CanMoveHere;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static entities.EnemyManager.checkEnemyHit;
import static objects.ObjectManager.checkCaixaHit;
import static objects.ObjectManager.checkBolaHit;
import java.awt.Point;

import main.Game;
import utilz.LoadSave;
import static utilz.LoadSave.GetSpriteMatrix;

public class Player extends Entity {
	
	private BufferedImage[][] animations;
	private boolean moving = false;
	private boolean left, up, right, down;
        private float xSpeed, ySpeed;
	private int[][] lvlData;
        private final float xDrawOffset = (float) (1.7 * Game.SCALE);
        private final float yDrawOffset = (float) (1.5 * Game.SCALE);
        public int vidas, tiros;

	public Player(float x, float y, int width, int height) {
            
            super(x, y, width, height);
            
            loadAnimations();
            
            initHitbox(12 * Game.SCALE, 12 * Game.SCALE);
            tileY = (int) (hitbox.y / Game.TILES_SIZE);
            tileX = (int) (hitbox.x / Game.TILES_SIZE);
            
            walkDir = DOWN;
            this.walkSpeed = 0.35f * Game.SCALE;
            
            vidas = 5;
            tiros = 0;

	}

	public void update() {
		
            updatePos();

            if(moving) {

                    updateAnimationTick();
                    tileY = (int) (hitbox.y / Game.TILES_SIZE);
                    tileX = (int) (hitbox.x / Game.TILES_SIZE);
            }	

            else
                    aniIndex = 2;
            setAnimation();
		
	}

	public void render(Graphics g) {
            g.drawImage(animations[aniIndex][walkDir], (int) ( hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
            //drawHitbox(g);
	}

	private void updateAnimationTick() {
		
            aniTick++;

            if (aniTick >= ANI_SPEED) {

                    aniTick = 0;
                    aniIndex++;

                    if (aniIndex >= 4) {

                            aniIndex = 0;
                    }
            }
	}

	private void setAnimation() {
            int startAni = walkDir;

            if (left)
                    walkDir = LEFT;

            if(right)
                    walkDir = RIGHT;

            if (up)
                    walkDir = UP;

            if(down)
                    walkDir = DOWN;

            if (startAni != walkDir)
                    resetAniTick();
	}
        
        public void setSpawn(Point spawn) {
            
            this.x = spawn.x;
            this.y = spawn.y;
            hitbox.x = x;
            hitbox.y = y;
	}

	private void resetAniTick() {
            
            aniTick = 0;
            aniIndex = 0;
	}

	private void updatePos() {
            
            moving = false;
            if (!left && !right && !up && !down)
                    return;

            xSpeed = 0;
            ySpeed = 0;

            if (left && !right) {
                    xSpeed = -walkSpeed;
                    ySpeed = 0;
            }
            else if (right && !left) {
                    xSpeed = walkSpeed;
                    ySpeed = 0;
            }

            if (up && !down) {
                    ySpeed = -walkSpeed;
                    xSpeed = 0;
            }
            else if (down && !up) {
                    ySpeed = walkSpeed;
                    xSpeed = 0;
            }

            if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData) && 
                    !checkEnemyHit(hitbox, xSpeed, ySpeed) && !checkCaixaHit(hitbox, xSpeed, ySpeed) && !checkBolaHit(hitbox, xSpeed, ySpeed)) {
                    hitbox.x += xSpeed;
                    hitbox.y += ySpeed;
                    moving = true;
            }
	}

	private void loadAnimations() {
            
            animations = GetSpriteMatrix(animations, 5, 4, LoadSave.PLAYER_ATLAS);
        }

	public void loadLvlData(int[][] lvlData) {
            
            this.lvlData = lvlData;
	}

	public void resetDirBooleans() {
            
            left = false;
            right = false;
            up = false;
            down = false;
	}

	public void setLeft(boolean left) {
            
            this.left = left;
	}

	public void setUp(boolean up) {
            
            this.up = up;
	}

	public void setRight(boolean right) {
            
            this.right = right;
	}

	public void setDown(boolean down) {
            
            this.down = down;
	}

	public boolean isAlive() {
		
            return isAlive;
	}

	public void setAlive(boolean isAlive) {
            
            this.isAlive = isAlive;
            if(!isAlive) {
                vidas--;
                System.out.println("Vida: "+vidas);
            }
	}
        
        public float getXSpeed() {
            
            return xSpeed;
        }
        
        public float getYSpeed() {
            
            return ySpeed;
        }
        
        public void resetAll() {
            
            resetDirBooleans();
            moving = false;

            hitbox.x = x;
            hitbox.y = y;
	}
}