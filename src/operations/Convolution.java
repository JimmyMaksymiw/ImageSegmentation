package operations;

import utils.Image;
import utils.Pixel;

/**
 * This class i used to apply a kernel on to a image. It iterate through the image and convolve the provided kernel on the image.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Convolution {
    private int[][] kernel;

    /**
     * Sets the Kernel to the provided one.
     * @param kernel The provided kernel.
     */
    public Convolution(int[][] kernel) {
        this.kernel = kernel;
    }

    /**
     * Convolve the selected kernel with the provided image.
     * @param image the image to be processed.
     * @return The new image with the selected kernel applied to it.
     */
    public Image convolve(Image image) {
        if (kernel == null) return null;

        // The image to be returned.
        Image out = new Image(image.getWidth(), image.getHeight(), image.getFilePath(), image.getFileName());

        int width = image.getWidth();
        int height = image.getHeight();
        int min, max, sum = 0;

        // Summarize kernel
        for(int[] cols : kernel) {
            for(int i : cols) {
                sum += i;
            }
        }
        sum = sum < 1 ? 1 : sum;

        // Iterate through the image and convolve the kernel on the image.
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

        // return the new image.
        return out;
    }
}
