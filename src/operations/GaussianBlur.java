package operations;

import utils.Image;

/**
 * This class represents the Gaussian function of blurring an image to reduce image noise and reduce detail.
 * It uses a kernel to reduce the image's high-frequency components.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class GaussianBlur {

    /**
     * Apply the Gaussian blur to the provided image.
     * @param image The image Gaussian blur should be applied to.
     * @return The new image with the Gaussian blur applied to it.
     */
    public static Image applyGaussianBlur(Image image) {
        /*int[][] kernel = {
                {1, 4, 7, 4, 1},
                {4, 16, 26, 16, 4},
                {7, 26, 41, 26, 7},
                {4, 16, 26, 16, 4},
                {1, 4, 7, 4, 1},
        };*/

        int[][] kernel = {
                {2, 4, 5, 4, 2},
                {4, 9, 12, 9, 4},
                {5, 12, 15, 12, 5},
                {4, 9, 12, 9, 4},
                {2, 4, 5, 4, 2}
        };

        /*int[][] kernel = {
                {1, 1, 1},
                {1, 10, 1},
                {1, 1, 1},
        };*/

        // Convolve with the Gaussian blur kernel
        Convolution c = new Convolution(kernel);
        // Return the new image
        return c.convolve(image);
    }
}
