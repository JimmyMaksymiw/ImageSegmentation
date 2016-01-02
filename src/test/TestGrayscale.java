package test;

import operations.Grayscale;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestGrayscale {
    public static void main(String[] args) throws IOException {
        String fileName = "colors";
        Image image = new Image("resources/colors.png", fileName);
        image = Grayscale.applyGrayscale(image);
        image.saveImage("testResults/flower_to_grayscale");
    }
}
