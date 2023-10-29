package entities;

import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.CanMoveHere;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = DOWN;
	private boolean moving = false;
	private boolean left, up, right, down;
	private float playerSpeed = 1.5f;
	private int[][] lvlData;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 15 * Game.SCALE, 15 * Game.SCALE);

	}

	public void update() {
		
		updatePos();
		
		if(moving)
			updateAnimationTick();
		
		else
			aniIndex = 2;
		setAnimation();
		
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) hitbox.x, (int) hitbox.y, width, height, null);
		drawHitbox(g);
	}

	private void updateAnimationTick() {
		
		aniTick++;
		
		if (aniTick >= aniSpeed) {
			
			aniTick = 0;
			aniIndex++;
			
			if (aniIndex >= 4) {
				
				aniIndex = 0;
			}
		}
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (left)
			playerAction = LEFT;
		
		if(right)
			playerAction = RIGHT;
		
		if (up)
			playerAction = UP;
		
		if(down)
			playerAction = DOWN;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		if (!left && !right && !up && !down)
			return;

		float xSpeed = 0, ySpeed = 0;

		if (left && !right)
			xSpeed = -playerSpeed;
		else if (right && !left)
			xSpeed = playerSpeed;
		if (up && !down)
			ySpeed = -playerSpeed;
		else if (down && !up)
			ySpeed = playerSpeed;

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
			hitbox.y += ySpeed;
			moving = true;
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[4][5];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * Game.TILES_DEFAULT_SIZE, j * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);

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

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}