package operations;

import utils.Image;

/**
 * This class represent a motion blur filter.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class MotionBlur {

    /**
     * Apply the motion blur filter to the provided image.
     * @param image The image motion blur filter should be applied to.
     * @return The new image with the motion blur filter applied to it.
     */
    public static Image applyMotionBlur(Image image) {
        int[][] kernel = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1}
        };

        // Convolve with motion blur kernel
        Convolution c = new Convolution(kernel);
        // Return the new image
        return c.convolve(image);
    }
}
