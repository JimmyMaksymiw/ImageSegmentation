package test;

import operations.GaussianBlur;
import utils.Image;

/**
 * Created by Kalle Bornemark on 2015-12-29.
 */
public class TestGaussianBlur {
    public static void main(String[] args) {
        Image image = new Image("resources/medium.jpg");
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage("testResults/gaussian_test_medium.jpg");
    }
}
