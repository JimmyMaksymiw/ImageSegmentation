package test;

import operations.GaussianBlur;
import operations.Grayscale;
import operations.Sobel;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestCanny {
    public static void main(String[] args) throws IOException {

        // Choose file.
//        String fileName = "castle_gaussian_blur";
        String fileName = "valve";

        // Load Image.
        Image image = new Image("resources/" + fileName + ".png");
//        Image image = new Image("testResults/" + fileName + ".jpg");

        // Grayscale.
        image = Grayscale.applyGrayscale(image);
        image.saveImage("testResults/" + fileName + "_grayscale.png");

        // Gaussian blur.
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage("testResults/" + fileName + "_gaussian_blur.png");

        // Sobel.
        image = Sobel.applySobel(image);
        image.saveImage("testResults/" + fileName + "_sobel.png");

    }
}
