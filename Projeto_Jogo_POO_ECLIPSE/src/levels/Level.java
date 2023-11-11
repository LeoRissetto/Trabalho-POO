package levels;

import entities.BixoRosa;
import entities.BixoVerde;
import entities.Caveira;
import entities.Snake;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Agua;
import objects.Bau;
import objects.Caixa;
import objects.Coracao;
import objects.Porta;
import static utilz.HelpMethods.GetLevelData;
import static utilz.HelpMethods.*;

public class Level {

        private final BufferedImage img;
	private int[][] lvlData;
        
        private ArrayList<Snake> snakes;
	private ArrayList<Caveira> caveiras;
	private ArrayList<BixoVerde> bixosVerde;
	private ArrayList<BixoRosa> bixosRosa;
        
        private ArrayList<Coracao> coracoes;
        private ArrayList<Agua> aguas;
        private ArrayList<Porta> portas;
        private ArrayList<Bau> baus;
        private ArrayList<Caixa> caixas;
        
        private Point playerSpawn;

	public Level(BufferedImage img) {
		this.img = img;
                createLevelData();
                createEnemies();
                createObjects();
                calcPlayerSpawn();
	}
        
        private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

	public int[][] getLevelData() {
		return lvlData;
	}

    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    private void createEnemies() {
        
        snakes = GetSnakes(img);
	caveiras = GetCaveiras(img);
	bixosVerde = GetBixosVerde(img);
	bixosRosa = GetBixosRosa(img);
    }

    private void createObjects() {
        
        coracoes = GetCoracoes(img);
        aguas = GetAguas(img);
        caixas = GetCaixas(img);
        portas = GetPortas(img);
        baus = GetBaus(img);
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public ArrayList<Caveira> getCaveiras() {
        return caveiras;
    }

    public ArrayList<BixoVerde> getBixosVerde() {
        return bixosVerde;
    }

    public ArrayList<BixoRosa> getBixosRosa() {
        return bixosRosa;
    }

    public ArrayList<Coracao> getCoracoes() {
        return coracoes;
    }

    public ArrayList<Agua> getAguas() {
        return aguas;
    }

    public ArrayList<Porta> getPortas() {
        return portas;
    }

    public ArrayList<Bau> getBaus() {
        return baus;
    }

    public ArrayList<Caixa> getCaixas() {
        return caixas;
    }
    
    public Point getPlayerSpawn() {
	return playerSpawn;
    }

}