package operations;

import utils.Image;
import utils.Pixel;

/**
 * Created by Kalle Bornemark on 2015-12-29.
 */
public class Convolution {
    private int[][] kernel;

    public Convolution(int[][] kernel) {
        this.kernel = kernel;
    }

    public Image convolve(Image image) {
        Image out = new Image(image.getWidth(), image.getHeight());

        int width, height, min, max, sum = 0;

        // Summarize kernel
        for(int[] cols : kernel) {
            for(int i : cols) {
                sum += i;
            }
        }
        width = image.getWidth();
        height = image.getHeight();
        System.out.println("Width: " + width + ", Height: " + height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                min = -kernel.length / 2;
                max = kernel.length / 2;
                double value[] = new double[] {0, 0, 0};

                for (int kx = min; kx <= max; kx++) {
                    for (int ky = min; ky <= max; ky++) {
                        Pixel currentPixel = image.getPixel(x - kx, y - ky);
                        if (currentPixel != null) {
                            value[0] += kernel[kx - min][ky - min] * currentPixel.getR();
                            value[1] += kernel[kx - min][ky - min] * currentPixel.getG();
                            value[2] += kernel[kx - min][ky - min] * currentPixel.getB();
                        }
                    }
                }

                Pixel core = out.getPixel(x, y);
                core.setARGB(255, (int)(value[0] / sum), (int)(value[1] / sum), (int)(value[2] / sum));
            }
        }


        return out;
    }
}
