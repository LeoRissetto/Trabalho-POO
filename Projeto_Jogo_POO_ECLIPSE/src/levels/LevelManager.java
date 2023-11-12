package levels;

import gamestates.Gamestate;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utilz.LoadSave;

public class LevelManager {

    private final Game game;
    
    private BufferedImage[] levelSprite;
    
    private final ArrayList<Level> levels;
    
    private int lvlIndex = 0;

    public LevelManager(Game game) {
        
        this.game = game;
        
        importOutsideSprites();
        
        levels = new ArrayList<>();
        
        buidAllLevels();
    }

    private void importOutsideSprites() {
        
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[64];
        for (int j = 0; j < 8; j++)
                for (int i = 0; i < 8; i++) {
                        int index = j * 8 + i;
                        levelSprite[index] = img.getSubimage(i * Game.TILES_DEFAULT_SIZE, j * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
                }
    }

    public void draw(Graphics g) {
        
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
                for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                        int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                        g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
            
            return levels.get(lvlIndex);
	}

    private void buidAllLevels() {
        
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        for(BufferedImage img : allLevels)
            levels.add(new Level(img));
    }

    public void loadNextLevel() {
        
        lvlIndex++;
        if(lvlIndex >= levels.size()) {
            lvlIndex = 0;
            Gamestate.state = Gamestate.MENU;
        }
        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    public void setLvlIndex(int lvlIndex) {
        
        this.lvlIndex = lvlIndex;
    }

    public int getLvlIndex() {
        
        return lvlIndex;
    }  
}