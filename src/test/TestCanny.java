package test;

import operations.GaussianBlur;
import operations.Grayscale;
import operations.Sobel;
import segmentations.Threshold;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestCanny {
    public static void main(String[] args) throws IOException {

        // Choose file.
//        String fileName = "orange_flower";
        String fileName = "castle";

        // Load Image.
        Image image = new Image("resources/" + fileName + ".jpg");
//        Image image = new Image("testResults/" + fileName + ".jpg");

        // Grayscale.
        image = Grayscale.applyGrayscale(image);
        image.saveImage("testResults/" + fileName + "1_grayscale");

        // Gaussian blur.
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage("testResults/" + fileName + "2_gaussian_blur");

        // Sobel.
        image = Sobel.applySobel(image);
        image.saveImage("testResults/" + fileName + "3_sobel");

        image = new Threshold().segmentize(image);
        image.saveImage("testResults/" + fileName + "4_threshold");

    }
}
