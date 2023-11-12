package utilz;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import main.Game;


public class LoadSave {

	public static final String PLAYER_ATLAS = "lolo_spritesheet.png";
        
	public static final String LEVEL_ATLAS = "textures.png";
        
	public static final String MENU_BUTTONS = "playButton.jpg";
	public static final String MENU_BACKGROUND = "menuScreen.png";
        
	public static final String SNAKE_SPRITE = "snake.png";
	public static final String CAVEIRA_SPRITE = "caveira.png";
	public static final String BIXO_VERDE_SPRITE = "bixoVerde.png";
	public static final String BIXO_ROSA_SPRITE = "bixoRosa.png";
        
        public static final String CORACAO_IMG = "coracao.png";
        public static final String AGUA_SPRITE = "agua.png";
        public static final String CAIXA_IMG = "caixa.png";
        public static final String BAU_SPRITE = "baus.png";
        public static final String PORTA_SPRITE = "porta.png";
        public static final String BOLA_IMG = "bola.png";
        public static final String FOGO_SPRITE = "fogo.png";
        public static final String TIRO_SPRITE = "tiro.png";
         

	public static BufferedImage GetSpriteAtlas(String fileName) {
            
            BufferedImage img = null;
            InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
            try {
                    img = ImageIO.read(is);

            } catch (IOException e) {
            } finally {
                    try {
                            is.close();
                    } catch (IOException e) {
                    }
            }
            return img;
	}
	
	public static BufferedImage[] GetAllLevels() {
            
            URL url = LoadSave.class.getResource("/lvls");
            File file = null;

            try {
                    file = new File(url.toURI());
            } catch (URISyntaxException e) {
            }

            File[] files = file.listFiles();
            File[] filesSorted = new File[files.length];

            for (int i = 0; i < filesSorted.length; i++)
                    for (File file1 : files) {
                    if (file1.getName().equals((i + 1) + ".png")) {
                        filesSorted[i] = file1;
                    }
                }

            BufferedImage[] imgs = new BufferedImage[filesSorted.length];

            for (int i = 0; i < imgs.length; i++)
                    try {
                            imgs[i] = ImageIO.read(filesSorted[i]);
                    } catch (IOException e) {
                    }

            return imgs;
	}
        
        public static BufferedImage[] GetSpriteArray(BufferedImage[] array, int index, int x, int y, String filename) {
            
            array = new BufferedImage[index];
            BufferedImage temp = GetSpriteAtlas(filename);
            for(int j = 0; j < array.length; j++) {
                array[j] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE * x, j * Game.TILES_DEFAULT_SIZE * y, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
            }
            
            return array;
        }
        
        public static BufferedImage[][] GetSpriteMatrix(BufferedImage[][] matrix, int indexX, int indexY, String filename) {
            
            matrix = new BufferedImage[indexX][indexY];
            BufferedImage temp = GetSpriteAtlas(filename);
            for(int j = 0; j < matrix.length; j++) {
                for(int i = 0; i < matrix[j].length; i++)
                    matrix[j][i] = temp.getSubimage(j * Game.TILES_DEFAULT_SIZE, i * Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE, Game.TILES_DEFAULT_SIZE);
            }
            
            return matrix;
        }
        
    public static void WriteToFile(int numeroParaEscrever) {
        // Obtenha o caminho do arquivo usando getResource
        String filePath = LoadSave.class.getResource("/TextFile.txt").getPath();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve o número no arquivo
            writer.write(Integer.toString(numeroParaEscrever));
        } catch (IOException e) {
            // Trate a exceção de maneira significativa para o seu aplicativo
            e.printStackTrace();
        }
    }

    public static int ReadFromFile() {
        int numeroLido = 0;  // Valor padrão, pode ser ajustado conforme necessário

        try (Scanner sc = new Scanner(LoadSave.class.getResourceAsStream("/TextFile.txt"))) {
            if (sc.hasNextLine()) {
                String linha = sc.nextLine();
                numeroLido = Integer.parseInt(linha.trim());
            }
        } catch (NumberFormatException e) {
            // Trate a exceção de maneira significativa para o seu aplicativo
            e.printStackTrace();
        }

        if (numeroLido > 3 || numeroLido <= 0) {
            return 0;
        }
        return numeroLido;
    }
}