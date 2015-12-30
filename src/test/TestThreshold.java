package test;

import java.io.IOException;

import operations.GaussianBlur;
import operations.Grayscale;
import operations.Sobel;
import segmentations.Threshold;
import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestThreshold {
    public static void main(String[] args) throws IOException {

        // Choose file.
//        String fileName = "orange_flower";
        String fileName = "colors";

        // Load Image.
        Image image = new Image("resources/" + fileName + ".png");
//        Image image = new Image("testResults/" + fileName + ".jpg");

        image = new Threshold().segmentize(image);
        image.saveImage("testResults/" + fileName + "_threshold");

    }
}
