package gamestates;

import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

	private final MenuButton[] buttons = new MenuButton[1];
        
        private int aniTick, aniIndex = 0;
	private final int aniSpeed = 25;
        
	private BufferedImage backgroundImg;
	private BufferedImage[][] animations;
        
	private int menuX, menuY, menuWidth, menuHeight;
        
	private int menuIndex;

	public Menu(Game game) {
            
            super(game);

            loadButtons();
            loadAnimations();
            loadBackground();

            menuIndex = 0;
	}

	private void loadAnimations() {

            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[4][5];
            for (int j = 0; j < animations.length; j++)
                    for (int i = 0; i < animations[j].length; i++)
                            animations[j][i] = img.getSubimage(i * Game.TILES_DEFAULT_SIZE, j * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);

	}

	private void loadBackground() {
            
            backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);

            menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
            menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);

            menuX = 0;
            menuY = 0;
	}

	private void loadButtons() {
            
            buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
	}
	
	@Override
	public void draw(Graphics g) {

            g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

            for (MenuButton mb : buttons)
                    mb.draw(g);

            if(menuIndex == 1)
                    g.drawImage(animations[0][aniIndex], (int) (60 * Game.SCALE), (int) (182 * Game.SCALE), (int) (12 * Game.SCALE), (int) (12 * Game.SCALE), null);

            if(menuIndex == 0)
                    g.drawImage(animations[0][aniIndex], (int) (60 * Game.SCALE), (int) (170 * Game.SCALE), (int) (12 * Game.SCALE), (int) (12 * Game.SCALE), null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
            if(e.getKeyCode() == KeyEvent.VK_UP)
                    menuIndex = 0;

            if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    menuIndex = 1;

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if(menuIndex == 0)
                            Gamestate.state = Gamestate.PLAYING;

                    else if(menuIndex == 1)
                            System.exit(0);
            }

	}
        
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
            // TODO Auto-generated method stub
            aniTick++;

            if (aniTick >= aniSpeed) {

                    aniTick = 0;
                    aniIndex++;

                    if (aniIndex >= 4) {

                            aniIndex = 0;
                    }
            }
	}
}