package objects;

import entities.Player;
import gamestates.Playing;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import levels.Level;
import main.Game;
import utilz.LoadSave;

public class ObjectManager {
    
    private final Playing playing;
    private BufferedImage coracao, caixa, bola;
    private BufferedImage[] aguaArr, portaArr;
    private BufferedImage[][] bauArr, fogoArr;
    private static ArrayList<Coracao> coracoes = new ArrayList<>();
    private static ArrayList<Agua> aguas = new ArrayList<>();
    private static ArrayList<Porta> portas = new ArrayList<>();
    private static ArrayList<Bau> baus = new ArrayList<>();
    private static ArrayList<Caixa> caixas = new ArrayList<>();
    private static ArrayList<Bola> bolas = new ArrayList<>();
    private static ArrayList<Fogo> fogos = new ArrayList<>();
    
    
    public ObjectManager(Playing playing) {
        
        this.playing = playing;
	loadObjectImgs();
    }
    
    public void loadObjects(Level level) {
		
	coracoes = level.getCoracoes();
        aguas = level.getAguas();
        caixas = level.getCaixas();
        portas = level.getPortas();
        baus = level.getBaus();
        bolas.clear();
        fogos.clear();
    }
    
    public void update(int[][] lvlData) {
		
	for (Coracao c : coracoes)
            c.update();
        
        for (Agua a : aguas)
            a.update();
        
        for (Bau b : baus)
            b.update();
        
        for (Caixa cx : caixas) 
           cx.update(lvlData);
        
        for (Porta p : portas) {
            
            p.update();
            if(p.getAniIndex() == 1)
                if(p.hitbox.intersects(Playing.getPlayer().getHitbox().x + Playing.getPlayer().getXSpeed(), Playing.getPlayer().getHitbox().y + Playing.getPlayer().getYSpeed(), Playing.getPlayer().getHitbox().width, Playing.getPlayer().getHitbox().height)) {
                    playing.setLevelCompleted(true);
            }
        }
        
        updateBolas(lvlData, Playing.getPlayer());
        updateFogos(lvlData, Playing.getPlayer());
    }
    
    public void draw(Graphics g) {
		
	drawCoracoes(g);
        drawAguas(g);
        drawCaixas(g);
        drawPortas(g);
        drawBaus(g);
        drawFogos(g);
        drawBolas(g);
    }
    
    private void drawCoracoes(Graphics g) {
		
	for (Coracao c : coracoes) {
            if(c.isActive())
                g.drawImage(coracao, (int) c.getHitbox().x, (int) c.getHitbox().y, c.width, c.height, null);
            //c.drawHitbox(g);
	}
    }
    
    private void drawAguas(Graphics g) {
		
	for (Agua a : aguas) {
            if(a.isActive())
            g.drawImage(aguaArr[a.getAniIndex()], (int) a.getHitbox().x, (int) a.getHitbox().y, a.width, a.height, null);
            //a.drawHitbox(g);
	}
    }
    
    private void drawCaixas(Graphics g) {
		
	for (Caixa cx : caixas) {
            if(cx.isActive())
                g.drawImage(caixa, (int) cx.getHitbox().x, (int) cx.getHitbox().y, cx.width, cx.height, null);
            //cx.drawHitbox(g);
	}
    }
    
    private void drawPortas(Graphics g) {
		
	for (Porta p : portas) {
            if(p.isActive())
                g.drawImage(portaArr[p.getAniIndex()], (int) p.getHitbox().x, (int) p.getHitbox().y, p.width, p.height, null);
            //p.drawHitbox(g);
	}
    }
    
    private void drawBaus(Graphics g) {
		
	for (Bau b : baus) {
            if(b.isActive())
                g.drawImage(bauArr[b.getAniIndex()][1], (int) b.getHitbox().x, (int) b.getHitbox().y, b.width, b.height, null);
                //b.drawHitbox(g);
	}
    }
    
    private void loadObjectImgs() {
		
	coracao = LoadSave.GetSpriteAtlas(LoadSave.CORACAO_IMG);
        
        caixa = LoadSave.GetSpriteAtlas(LoadSave.CAIXA_IMG);
        
        bola = LoadSave.GetSpriteAtlas(LoadSave.BOLA_IMG);
        
        aguaArr = new BufferedImage[6];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.AGUA_SPRITE);
        for(int j = 0; j < aguaArr.length; j++)
            aguaArr[j] = temp.getSubimage(0, j * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
        
        portaArr = new BufferedImage[2];
	temp = LoadSave.GetSpriteAtlas(LoadSave.PORTA_SPRITE);
            for (int j = 0; j < portaArr.length; j++)
		portaArr[j] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, 0, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
		
        bauArr = new BufferedImage[3][2];
	temp = LoadSave.GetSpriteAtlas(LoadSave.BAU_SPRITE);
            for (int j = 0; j < bauArr.length; j++)
                for(int i = 0; i < bauArr[j].length; i++)
                    bauArr[j][i] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, i * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
            
        fogoArr = new BufferedImage[4][2];
	temp = LoadSave.GetSpriteAtlas(LoadSave.FOGO_SPRITE);
            for (int j = 0; j < fogoArr.length; j++)
		for(int i = 0; i < fogoArr[j].length; i++)
                    fogoArr[j][i] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, i * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
		
    }

    public void resetAllObjects() {
        
        for (Coracao c : coracoes)
            c.resetObject();
        
        for (Agua a : aguas)
            a.resetObject();
        
        for (Bau b : baus)
            b.resetObject();
        
        for (Caixa cx : caixas) 
           cx.resetObject();
        
        for (Porta p : portas)
            p.resetObject();
    }
    
    public static boolean openChest() {
        for(Coracao c : coracoes)
            if(c.isActive())
                return false;
        return true;
    }
    
    public static boolean openDoor() {
        for(Bau b : baus)
            if(b.getAniIndex()== 2)
                return true;
        return false;
    }
    
    public static boolean checkCaixaHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        boolean temp = false;
        
        for (Caixa cx : caixas)
           if(hitbox != cx.hitbox)
            if(cx.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
               temp = true;
        return temp;
    }
    
    public static boolean checkCoracaoHit(Rectangle2D hitbox, float xSpeed, float ySpeed) {
        
        boolean temp = false;
        
        for (Coracao c : coracoes)
            if(c.getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight()))
               temp = true;
        return temp;
    }

    private void updateBolas(int[][] lvlData, Player player) {
        for(Bola b : bolas)
            b.updatePos();
    }
    
    public static void addBola(float x, float y, int direction) {
        
        bolas.add(new Bola(x, y, direction));
    }

    private void updateFogos(int[][] lvlData, Player player) {
        
        for(Fogo f : fogos)
            if(f.isActive()) {
                f.updatePos();
                if(checkHitPlayer(f.getHitbox(), 0, 0))
                        Playing.getPlayer().setAlive(false);
            }
        
    }
    
    public static void addFogo(float x, float y, int direction) {
        
        fogos.add(new Fogo(x, y, direction));
    }

    private void drawFogos(Graphics g) {
        
        for(Fogo f : fogos)
            if(f.isActive())
                g.drawImage(fogoArr[3][0], (int) f.getHitbox().x, (int) f.getHitbox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    }
    
    protected boolean checkHitPlayer(Rectangle2D hitbox, float xSpeed, float ySpeed) {
		
        return Playing.getPlayer().getHitbox().intersects(hitbox.getX() + xSpeed, hitbox.getY() + ySpeed, hitbox.getWidth(), hitbox.getHeight());
    }

    private void drawBolas(Graphics g) {
        
        for(Bola b : bolas)
            if(b.isActive())
                g.drawImage(bola, (int) b.getHitbox().x, (int) b.getHitbox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    }
    
}
