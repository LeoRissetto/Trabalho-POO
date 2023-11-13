package objects;

import gamestates.Playing;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import levels.Level;

import main.Game;

import utilz.LoadSave;


import static utilz.Constants.ObjectConstants.*;

import static utilz.LoadSave.GetSpriteArray;
import static utilz.LoadSave.GetSpriteMatrix;

public class ObjectManager {
        
    private static Playing playing;
    private BufferedImage coracao, caixa, bola;
    private BufferedImage[] aguaArr, portaArr, tiroArr;
    private BufferedImage[][] bauArr, fogoArr;
    
    private static ArrayList<? extends GameObject> coracoes, aguas, portas, baus, caixas = new ArrayList<>();
    private static ArrayList<Tiro> tiros = new ArrayList<>();
    private static ArrayList<Fogo> fogos = new ArrayList<>();
    private static ArrayList<Bola> bolas = new ArrayList<>();
    
    public ObjectManager(Playing playing) {
        
        ObjectManager.playing = playing;
	loadObjectImgs();
    }
    
    public void loadObjects(Level level) {
		
	coracoes = level.getCoracoes();
        aguas = level.getAguas();
        caixas = level.getCaixas();
        portas = level.getPortas();
        baus = level.getBaus();
        baus = level.getBaus();
        bolas.clear();
        fogos.clear();
        tiros.clear();
    }
    
    public void update(int[][] lvlData) {
		
	updateObject(coracoes, lvlData);
        updateObject(caixas, lvlData);
        updateObject(baus, lvlData);
        updateObject(aguas, lvlData);
        updateObject(portas, lvlData);
        updateObject(bolas, lvlData);
        updateObject(tiros, lvlData);
        updateObject(fogos, lvlData);
    }
    
    private void updateObject(ArrayList<? extends GameObject> array, int[][] lvlData) {
        
        for (GameObject a : array) {
            if(a.isActive())
                a.update(lvlData);
        }
    }
    
    public void draw(Graphics g) {
		
	drawObject(coracoes, g);
        drawObject(aguas, g);
        drawObject(caixas, g);
        drawObject(baus, g);
        drawObject(portas, g);
        drawObject(fogos, g);
        drawObject(bolas, g);
        drawObject(tiros, g);
    }
    
    private void drawObject(ArrayList<? extends GameObject> array, Graphics g) {
        
        for (GameObject a : array) {
            if(a.isActive())
                switch(a.getObjectType()) {

                    case CORACAO -> g.drawImage(coracao, (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);

                    case AGUA -> g.drawImage(aguaArr[a.getAniIndex()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);

                    case PORTA -> g.drawImage(portaArr[a.getAniIndex()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);

                    case BAU -> g.drawImage(bauArr[a.getAniIndex()][1], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);

                    case CAIXA -> g.drawImage(caixa, (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);
                    
                    case FOGO -> g.drawImage(fogoArr[a.getAniIndex()][0], (int) (a.getHitbox().x - (3 * Game.SCALE)), (int) (a.getHitbox().y - (1.5 * Game.SCALE)), Game.TILES_SIZE, Game.TILES_SIZE, null);
                    
                    case TIRO -> g.drawImage(tiroArr[a.getAniIndex()], (int) (a.getHitbox().x - (3 * Game.SCALE)), (int) (a.getHitbox().y - (1.5 * Game.SCALE)), Game.TILES_SIZE, Game.TILES_SIZE, null);
                    
                    case BOLA -> g.drawImage(bola, (int) a.getHitbox().x, (int) a.getHitbox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
                //a.drawHitbox(g);
        }
    }
    
    private void loadObjectImgs() {
		
	coracao = LoadSave.GetSpriteAtlas(LoadSave.CORACAO_IMG);     
        caixa = LoadSave.GetSpriteAtlas(LoadSave.CAIXA_IMG);        
        bola = LoadSave.GetSpriteAtlas(LoadSave.BOLA_IMG);
        aguaArr = GetSpriteArray(aguaArr, 6, 0, 1, LoadSave.AGUA_SPRITE);
        portaArr = GetSpriteArray(aguaArr, 2, 1, 0, LoadSave.PORTA_SPRITE);            
        tiroArr = GetSpriteArray(tiroArr, 4, 1, 0, LoadSave.TIRO_SPRITE);
        bauArr = GetSpriteMatrix(bauArr, 3, 2, LoadSave.BAU_SPRITE);    
        fogoArr = GetSpriteMatrix(fogoArr, 4, 2, LoadSave.FOGO_SPRITE);	
    }

    public void resetAllObjects() {
        
        resetObject(coracoes);
        resetObject(caixas);
        resetObject(baus);
        resetObject(aguas);
        resetObject(portas);
        bolas.clear();
        tiros.clear();
        fogos.clear();
    }
    
    private void resetObject(ArrayList<? extends GameObject> array) {
        
        for (GameObject a : array) {
            a.resetObject();
        }
    }
    
    public static boolean openChest() {
        
        for(GameObject c : coracoes)
            if(c.isActive())
                return false;
        return true;
    }
    
    public static boolean openDoor() {
        
        for(GameObject b : baus)
            if(b.getAniIndex()== 2)
                return true;
        return false;
    }
    
    public static boolean checkCaixaHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        boolean temp = false;
            
        for(GameObject cx : caixas)
            if(cx.isActive())
                if(cx.getHitbox() != hitbox)
                    if(cx.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
                    temp = true;

        return temp;
    }
    
    public static boolean checkCoracaoHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        return checkHit(coracoes, hitbox, xSpeed, ySpeed);
    }
    
    private static boolean checkHit(ArrayList<? extends GameObject> array, Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        boolean temp = false;
            
        for(GameObject a : array)
            if(a.isActive())
                if(a.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
                    temp = true;

        return temp; 
    }
    
    public static boolean checkAguaHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        return checkHit(aguas, hitbox, xSpeed, ySpeed);
    }
    
    public static boolean checkBolaHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        return checkHit(bolas, hitbox, xSpeed, ySpeed);
    }
     
    public static boolean checkTiroHit(Bola bola) {
        
        boolean temp = false;
            
        for(Tiro t : tiros)
            if(t.isActive())
                if(t.getHitbox().intersects(bola.getHitbox().getX(), bola.getHitbox().getY(), bola.getHitbox().getWidth(), bola.getHitbox().getHeight())) {
                    temp = true;
                    bola.aniIndex = t.aniIndex;
                    t.setActive(false);
                }

        return temp; 
    }
    
    protected boolean checkHitPlayer(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
        return Playing.getPlayer().getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight());
    }
    
    public static void addBola(float x, float y, int aniIndex) {
        
        bolas.add(new Bola(x, y, aniIndex));
    }
    
    public static void addFogo(float x, float y, int aniIndex) {
        
        fogos.add(new Fogo(x, y, aniIndex));
    }
    
    public void addTiros(float x, float y, int aniIndex) {
        
        tiros.add(new Tiro(x, y, aniIndex));
    }

    public static Playing getPlaying() {
        return playing;
    }
}
