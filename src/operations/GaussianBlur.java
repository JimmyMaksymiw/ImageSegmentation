package operations;

import utils.Image;

/**
 * Created by Kalle Bornemark on 2015-12-29.
 */
public class GaussianBlur {
    public static Image applyGaussianBlur(Image image) {
        int[][] kernel = {
                {1, 4, 7, 4, 1},
                {4, 16, 26, 16, 4},
                {7, 26, 41, 26, 7},
                {4, 16, 26, 16, 4},
                {1, 4, 7, 4, 1},
        };

        Convolution c = new Convolution(kernel);
        return c.convolve(image);
    }
}
