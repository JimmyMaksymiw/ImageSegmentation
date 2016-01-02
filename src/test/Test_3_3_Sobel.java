package test;

import operations.Sobel;
import segmentations.SegmentizeWithThreshold;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_3_3_Sobel {
    public static void main(String[] args) throws IOException {
        String fileName = "castle";
        Image image = new Image("tests/test3_sobel", fileName, "png");

        // Sobel
        int sobelThres = 20;    // Min and max pixel intensity (both ways)
        image = Sobel.applySobel(image, sobelThres);
        image.saveImage("tests/test3_sobel/test3.3", fileName + "_sobel-" + sobelThres);

        // Segmentation
        int segThres = 130;      // Max pixel intensity
        int segMin = 10000;      // Min segment size
        int segMax = 100000;      // Max segment size

        image = new SegmentizeWithThreshold(image, segThres, false, segMin, segMax).segmentize();
        image.saveImage("tests/test3_sobel/test3.3", fileName + "_sobel-" + sobelThres + "_segmentize_thres-" + segThres);
    }
}
