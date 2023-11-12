package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.GameObject;
import entities.Enemy;

import static utilz.HelpMethods.GetLevelData;
import static utilz.HelpMethods.*;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObjectConstants.*;


public class Level {

        private final BufferedImage img;
	private int[][] lvlData;
        
        private ArrayList<? extends Enemy> snakes, caveiras, bixosVerde, bixosRosa;
        
        private ArrayList<? extends GameObject> coracoes, aguas, portas, baus, caixas;
        
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

            snakes = GetEnemies(SNAKE,img);
            caveiras = GetEnemies(CAVEIRA,img);
            bixosVerde = GetEnemies(BIXO_VERDE,img);
            bixosRosa = GetEnemies(BIXO_ROSA,img);
        }

        private void createObjects() {

            coracoes = GetObjects(CORACAO, img);
            aguas = GetObjects(AGUA, img);
            portas = GetObjects(PORTA, img);
            baus = GetObjects(BAU, img);
            caixas = GetObjects(CAIXA, img);
        }

        public ArrayList<? extends Enemy> getSnakes() {
            
            return snakes;
        }

        public ArrayList<? extends Enemy> getCaveiras() {
            
            return caveiras;
        }

        public ArrayList<? extends Enemy> getBixosVerde() {
            
            return bixosVerde;
        }

        public ArrayList<? extends Enemy> getBixosRosa() {
            
            return bixosRosa;
        }
        
        public ArrayList<? extends GameObject> getCoracoes() {
            return coracoes;
        }

        public ArrayList<? extends GameObject> getAguas() {
            return aguas;
        }

        public ArrayList<? extends GameObject> getPortas() {
            return portas;
        }

        public ArrayList<? extends GameObject> getBaus() {
            return baus;
        }

        public ArrayList<? extends GameObject> getCaixas() {
            return caixas;
        }

        public Point getPlayerSpawn() {
            return playerSpawn;
        }
}