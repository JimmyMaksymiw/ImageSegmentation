package operations;

import java.awt.Color;
import java.util.Random;

import utils.Image;
import utils.Pixel;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class CreateImage {
    public static void main(String[] args) {
        Random random = new Random();
        Image smallImage = new Image(800, 500);
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
        smallImage.saveImage("resources/medium.jpg");
    }
}
