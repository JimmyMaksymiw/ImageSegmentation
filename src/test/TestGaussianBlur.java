package test;

import operations.GaussianBlur;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestGaussianBlur {
    public static void main(String[] args) throws IOException {
        String fileName = "castle";
//        Image image = new Image("resources/medium.jpg");
        Image image = new Image("resources", fileName, "jpg");
        image = GaussianBlur.applyGaussianBlur(image);
//        image.saveImage("testResults/gaussian_test_medium5");
        image.saveImage("testResults", fileName + "_gaussianblur");
    }
}
