package test;

import java.io.IOException;

import operations.GaussianBlur;
import operations.Grayscale;
import utils.Image;

/**
 * Created by Kalle Bornemark on 2016-01-02.
 */
public class Test2GrayscaleGaussian {
    public static void main(String[] args) throws IOException {
        String folder = "tests/grayscale_gaussian";
        String fileName = "orange_flower";
        Image image = new Image(folder, fileName, "jpg");

        // Grayscale
        image = Grayscale.applyGrayscale(image);
        image.saveImage(folder, fileName + "1_grayscale");

        // Gaussian blur
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage(folder, fileName + "2_gaussian_blur");
    }
}
