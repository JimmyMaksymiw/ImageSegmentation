package operations;

import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Sobel {

    public static Image applySobel(Image image){
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






        return image;
    }
}
