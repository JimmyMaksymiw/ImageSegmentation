package operations;

import utils.Image;
import utils.Pixel;

/**
 * This class represent a Sobel filter used for edge detection in an image.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Sobel {

    /**
     * Apply the Sobel filter to the provided image.
     * @param image The image Sobel filter should be applied to.
     * @param THRESHOLD Threshold that determines the refinement of edges.
     * @return The new image with the Sobel filter applied to it.
     */
    public static Image applySobel(Image image, final int THRESHOLD) {
        int[][] kernelX = {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };

        int[][] kernelY = {
                {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}
        };

        // Convolve with sobel kernels
        Image imageX = new Convolution(kernelX).convolve(image);
        Image imageY = new Convolution(kernelY).convolve(image);

        // New output image
        Image out = new Image(image.getWidth(), image.getHeight(), image.getFilePath(), image.getFileName());

        // Merge
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel p1 = imageX.getPixel(x, y);
                Pixel p2 = imageY.getPixel(x, y);

                int r = (int)Math.sqrt(p1.getR() * p1.getR() + p2.getR() * p2.getR());
                int g = (int)Math.sqrt(p1.getG() * p1.getG() + p2.getG() * p2.getG());
                int b = (int)Math.sqrt(p1.getB() * p1.getB() + p2.getB() * p2.getB());

                // Reduce noise.
                if (r > THRESHOLD && r < 255-THRESHOLD ) r = r <= 127 ? THRESHOLD : 255 - THRESHOLD;
                if (g > THRESHOLD && g < 255-THRESHOLD ) g = g <= 127 ? THRESHOLD : 255 - THRESHOLD;
                if (b > THRESHOLD && b < 255-THRESHOLD ) b = b <= 127 ? THRESHOLD : 255 - THRESHOLD;

                out.getPixel(x, y).setARGB(255, r, g, b);

            }
        }
        return out;
    }
}
