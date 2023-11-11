package utilz;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;


public class LoadSave {

	public static final String PLAYER_ATLAS = "lolo_spritesheet.png";
	public static final String LEVEL_ATLAS = "textures.png";
	public static final String LEVEL_ONE_DATA = "levelBase.png";
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
        
}