package operations;

import java.awt.Color;
import java.util.Random;

import utils.Image;
import utils.Pixel;

/**
 * This class is used to create a new Image object with random colors at random pixels.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class CreateImage {
    public static void main(String[] args) {
        String folder = "resources";
        String filename = "test";
        Random random = new Random();
        Image smallImage = new Image(800, 500, folder, filename);
        Pixel[][] pixel = smallImage.getPixels();
        for (int i = 0; i < smallImage.getHeight(); i++) {
            for (int j = 0; j < smallImage.getWidth(); j++) {
                int r = random.nextInt(3);
                if (r == 0) {
                    pixel[i][j].setARGB(Color.BLUE.getRGB());
                } else if (r == 1) {
                    pixel[i][j].setARGB(Color.GREEN.getRGB());
                } else {
                    pixel[i][j].setARGB(Color.YELLOW.getRGB());
                }
            }
        }
        smallImage.saveImage("resources", filename);
    }
}
