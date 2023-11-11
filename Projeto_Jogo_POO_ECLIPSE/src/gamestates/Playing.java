package gamestates;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import objects.ObjectManager;
import main.Game;

public class Playing extends State implements Statemethods {
	
	private static Player player;
	private LevelManager levelManager;
	private EnemyManager enemyManager;
        private ObjectManager objectManager;

	public Playing(Game game) {
		super(game);
		initClasses();
                loadStartLevel();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		enemyManager = new EnemyManager(this);
                objectManager = new ObjectManager(this);
		player = new Player(400, 300, (int) (16 * Game.SCALE), (int) (16 * Game.SCALE));
                player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());

	}

	@Override
	public void update() {
            
                if(!player.isAlive()) {
                    
                    levelManager.setLvlIndex(3);
                    setLevelCompleted(true);
                    player.setAlive(true);
                }
		
		levelManager.update();
		player.update();
		enemyManager.update(levelManager.getCurrentLevel().getLevelData());
                objectManager.update(levelManager.getCurrentLevel().getLevelData());
        }
        
	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		enemyManager.draw(g);
                objectManager.draw(g);
                player.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W -> player.setUp(true);
		case KeyEvent.VK_A -> player.setLeft(true);
		case KeyEvent.VK_S -> player.setDown(true);
		case KeyEvent.VK_D -> player.setRight(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			Gamestate.state = Gamestate.MENU;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W -> player.setUp(false);
		case KeyEvent.VK_A -> player.setLeft(false);
		case KeyEvent.VK_S -> player.setDown(false);
		case KeyEvent.VK_D -> player.setRight(false);
		}
		
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public static Player getPlayer() {
		return player;
	}

    private void loadStartLevel() {
        
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
        objectManager.loadObjects(levelManager.getCurrentLevel());
    }
    
    public void loadNextLevel() {
        
        resetAll();
        levelManager.loadNextLevel();
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }
    
    public void resetAll() {
        
	player.resetAll();
	enemyManager.resetAllEnemies();
        objectManager.resetAllObjects();
    }
    
    public void setLevelCompleted(boolean levelCompleted) {
        if(levelCompleted == true) {
            loadNextLevel();
        }
    }
    
    
}