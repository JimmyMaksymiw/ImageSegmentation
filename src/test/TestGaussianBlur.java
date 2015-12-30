package test;

import operations.GaussianBlur;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestGaussianBlur {
    public static void main(String[] args) throws IOException {
//        Image image = new Image("resources/medium.jpg");
        Image image = new Image("resources/castle.jpg");
        image = GaussianBlur.applyGaussianBlur(image);
//        image.saveImage("testResults/gaussian_test_medium5.jpg");
        image.saveImage("testResults/castle_gaussian_blur.jpg");
    }
}
