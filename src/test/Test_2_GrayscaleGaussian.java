package test;

import java.io.IOException;

import operations.GaussianBlur;
import operations.Grayscale;
import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_2_GrayscaleGaussian {
    public static void main(String[] args) throws IOException {
        String folder = "tests/test2_grayscale_gaussian";
        String fileName = "castle";
        Image image = new Image("tests/test2_grayscale_gaussian", fileName, "jpg");

        // Grayscale
        image = Grayscale.applyGrayscale(image);
        image.saveImage(folder, fileName + "1_grayscale");

        // Gaussian blur
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage(folder, fileName + "2_gaussian_blur");
    }
}
