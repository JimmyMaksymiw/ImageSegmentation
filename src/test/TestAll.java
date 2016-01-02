package test;

import operations.GaussianBlur;
import operations.Grayscale;
import operations.Sobel;
import segmentations.SegmentizeWithThreshold;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestAll {
    public static void main(String[] args) throws IOException {

        // Choose file
//        String fileName = "orange_flower";
        String fileName = "castle";
//        String fileName = "colors";

        // Load Image
//        Image image = new Image("resources/" + fileName + ".png", fileName);
        Image image = new Image("resources", fileName, "jpg");
//        Image image = new Image("testResults/" + fileName + ".jpg", fileName);

        // Grayscale
        image = Grayscale.applyGrayscale(image);
        image.saveImage("testResults", fileName + "1_grayscale");

        // Gaussian blur
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage("testResults", fileName + "2_gaussian_blur");

        // Sobel
        int sobelThres = 50;    // Min and max pixel intensity (both ways)
        image = Sobel.applySobel(image, sobelThres);
        image.saveImage("testResults", fileName + "3_sobel-" + sobelThres);

        // Segmentation
        int segThres = 130;     // Max pixel intensity
        int segMin = 10000;      // Min segment size
        int segMax = 30000;      // Max segment size
        image = new SegmentizeWithThreshold(image, segThres, false, segMin, segMax).segmentize();
        image.saveImage("testResults", fileName + "4_segmentize_thres-" + segThres);

    }
}
