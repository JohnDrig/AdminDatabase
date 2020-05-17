package paket;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public BufferedImage deleteIcon, plus;

	public ImageLoader() {
		try {
			deleteIcon = ImageIO.read(new File("images/icons8-löschen-30.png"));
			plus = ImageIO.read(new File("images/plus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
