package test;

import operations.GaussianBlur;
import operations.Grayscale;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestCanny {
    public static void main(String[] args) throws IOException {

        // Choose file.
        String fileName = "castle";

        // Load Image.
        Image image = new Image("resources/" + fileName + ".jpg");

        // Grayscale.
        image = Grayscale.toGrayscale(image);
        image.saveImage("testResults/" + fileName + "_grayscale.png");

        // Gaussian blur.
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage("testResults/" + fileName + "_gaussian_blur.jpg");

        // Sobel

    }
}
