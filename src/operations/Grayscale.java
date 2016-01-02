package operations;

import utils.Image;
import utils.Pixel;

/**
 * This class is used to transform a image to grayscale.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Grayscale {

    /**
     * Apply the grayscale to the provided image.
     * @param image The image grayscale should be applied to.
     * @return The new image with in grayscale.
     */
    public static Image applyGrayscale(Image image) {
        Pixel[][] pixels = image.getPixels();

        int average;
        Pixel current;
        for (Pixel[] pixel : pixels) {
            for (Pixel aPixel : pixel) {
                current = aPixel;
                average = (current.getR() + current.getG() + current.getB()) / 3;
                current.setARGB(255, average, average, average);
            }
        }

        return image;
    }
}
