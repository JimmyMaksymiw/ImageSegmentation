package test;

import java.io.IOException;

import operations.GaussianBlur;
import operations.Grayscale;
import operations.Sobel;
import segmentations.SegmentizeWithThreshold;
import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_3_1_Sobel {
    public static void main(String[] args) throws IOException {
        String fileName = "castle";
        Image image = new Image("tests/test3_sobel", fileName, "png");

        // Sobel
        int sobelThres = 100;    // Min and max pixel intensity (both ways)
        image = Sobel.applySobel(image, sobelThres);
        image.saveImage("tests/test3_sobel/test3.1", fileName + "_sobel-" + sobelThres);

        // Segmentation
        int segThres = 80;      // Max pixel intensity
        int segMin = 10000;      // Min segment size
        int segMax = 100000;      // Max segment size

        image = new SegmentizeWithThreshold(image, segThres, false, segMin, segMax).segmentize();
        image.saveImage("tests/test3_sobel/test3.1", fileName + "_sobel-" + sobelThres + "_segmentize_thres-" + segThres);
    }
}
