package test;

import operations.Grayscale;
import utils.Image;

/**
 * Created by Kalle Bornemark on 2015-12-29.
 */
public class TestGrayscale {
    public static void main(String[] args) {
        Image image = new Image("resources/orange_flower.jpg");
        System.out.println("Width: " + image.getWidth() + "\nHeight: " + image.getHeight());
        image = Grayscale.toGrayscale(image);
        image.saveImage("testResults/flower_to_grayscale.png");
    }
}
