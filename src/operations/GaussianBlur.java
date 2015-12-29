package operations;

import utils.Image;

/**
 * Created by Kalle Bornemark on 2015-12-29.
 */
public class GaussianBlur {
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

        Convolution c = new Convolution(kernel);
        return c.convolve(image);
    }
}
