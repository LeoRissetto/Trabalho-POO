package utilz;

import entities.Enemy;
import objects.GameObject;

import entities.BixoRosa;
import entities.BixoVerde;
import entities.Caveira;
import entities.Snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;

import objects.Agua;
import objects.Bau;
import objects.Caixa;
import objects.Coracao;
import objects.Porta;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.Constants.ObjectConstants.*;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        
        if (!IsSolid(x, y, lvlData))
                if (!IsSolid(x + width, y + height, lvlData))
                        if (!IsSolid(x + width, y, lvlData))
                                if (!IsSolid(x, y + height, lvlData))
                                        return true;
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        
        if (x < 0 || x >= Game.GAME_WIDTH)
                return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
                return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        return !(value == 35 || value == 36 || value == 48 || value == 49);
    }

    public static int[][] GetLevelData(BufferedImage img) {

        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

        for (int j = 0; j < img.getHeight(); j++)
                for (int i = 0; i < img.getWidth(); i++) {
                        Color color = new Color(img.getRGB(i, j));
                        int value = color.getRed();
                        if (value >= 64)
                                value = 0;
                        lvlData[j][i] = value;
                }
        return lvlData;

    }

    public static ArrayList<? extends Enemy> GetEnemies(int enemyType, BufferedImage img) {

            ArrayList<Enemy> list = new ArrayList<>();
            for (int j = 0; j < img.getHeight(); j++)
                    for (int i = 0; i < img.getWidth(); i++) {
                        Color color = new Color(img.getRGB(i, j));

                        int value = color.getGreen();

                        if(value == enemyType)
                            switch(value) {

                                case SNAKE -> list.add(new Snake(i * Game.TILES_SIZE, j * Game.TILES_SIZE));

                                case CAVEIRA -> list.add(new Caveira(i * Game.TILES_SIZE, j * Game.TILES_SIZE));

                                case BIXO_ROSA -> {
                                    switch (color.getBlue()) {
                                        case 4 -> list.add(new BixoRosa(i * Game.TILES_SIZE, j * Game.TILES_SIZE, DOWN));
                                        case 1 -> list.add(new BixoRosa(i * Game.TILES_SIZE, j * Game.TILES_SIZE, LEFT));
                                        case 2 -> list.add(new BixoRosa(i * Game.TILES_SIZE, j * Game.TILES_SIZE, UP));
                                        default -> list.add(new BixoRosa(i * Game.TILES_SIZE, j * Game.TILES_SIZE, RIGHT));
                                    }
                                }

                                case BIXO_VERDE -> list.add(new BixoVerde(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
                            }
                    }
            return list;
    }
    
    public static ArrayList<? extends GameObject> GetObjects(int objectType, BufferedImage img) {

            ArrayList<GameObject> list = new ArrayList<>();
            for (int j = 0; j < img.getHeight(); j++)
                    for (int i = 0; i < img.getWidth(); i++) {
                        Color color = new Color(img.getRGB(i, j));

                        int value = color.getBlue();

                        if(value == objectType)
                            switch(value) {

                                case CORACAO -> {
                                    if(color.getGreen() == 255)
                                        list.add(new Coracao(i * Game.TILES_SIZE, j * Game.TILES_SIZE,false));
                                    else
                                        list.add(new Coracao(i * Game.TILES_SIZE, j * Game.TILES_SIZE,true));
                                }
                                

                                case AGUA -> list.add(new Agua(i * Game.TILES_SIZE, j * Game.TILES_SIZE));

                                case PORTA -> list.add(new Porta(i * Game.TILES_SIZE, j * Game.TILES_SIZE));

                                case BAU -> list.add(new Bau(i * Game.TILES_SIZE, j * Game.TILES_SIZE));

                                case CAIXA -> list.add(new Caixa(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
                            }
                    }
            return list;
    }

    public static Point GetPlayerSpawn(BufferedImage img) {
        
            for (int j = 0; j < img.getHeight(); j++)
                for (int i = 0; i < img.getWidth(); i++) {
                    Color color = new Color(img.getRGB(i, j));
                    int value = color.getGreen();
                    if (value == 100)
                            return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
                }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }
}