package test;

import operations.Sobel;
import segmentations.SegmentizeWithThreshold;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_4_2_Sobel {
    public static void main(String[] args) throws IOException {
        String fileName = "orange_flower";
        Image image = new Image("tests/test4_sobel", fileName, "png");

        // Sobel
        int sobelThres = 100;    // Min and max pixel intensity (both ways)
        image = Sobel.applySobel(image, sobelThres);
        image.saveImage("tests/test4_sobel/test4.2", fileName + "_sobel-" + sobelThres);

        // Segmentation
        int segThres = 70;      // Max pixel intensity
        int segMin = 10000;      // Min segment size
        int segMax = 100000;      // Max segment size
        image = new SegmentizeWithThreshold(image, segThres, false, segMin, segMax).segmentize();
        image.saveImage("tests/test4_sobel/test4.2", fileName + "_sobel-" + sobelThres + "_segmentize_thres-" + segThres);
    }
}
