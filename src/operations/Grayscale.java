package operations;

import utils.Image;
import utils.Pixel;

/**
 * Created by Kalle Bornemark on 2015-12-29.
 */
public class Grayscale {
    public static Image toGrayscale(Image image) {
        Pixel[][] pixels = image.getPixels();

        int average;
        Pixel current;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                current = pixels[i][j];
                average = (current.getR() + current.getG() + current.getB()) / 3;
                current.setARGB(255, average, average, average);
            }
        }

        return image;
    }
}
