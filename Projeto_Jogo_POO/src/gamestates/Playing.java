package gamestates;

import java.awt.Graphics;

import java.awt.event.KeyEvent;
import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(1 * Game.TILES_SIZE, 2 * Game.TILES_SIZE, (int) (16 * Game.SCALE), (int) (16 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());

	}

	@Override
	public void update() {
		levelManager.update();
		player.update();

	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			Gamestate.state = Gamestate.MENU;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		}
		
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

}