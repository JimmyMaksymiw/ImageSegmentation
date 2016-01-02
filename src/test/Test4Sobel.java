package test;

import java.io.IOException;

import operations.Sobel;
import segmentations.SegmentizeWithThreshold;
import utils.Image;

/**
 * Created by Kalle Bornemark on 2016-01-02.
 */
public class Test4Sobel {
    public static void main(String[] args) throws IOException {
        String folder = "tests/sobel";
        String fileName = "orange_flower";
        Image image = new Image(folder, fileName, "png");

        // Sobel
        int sobelThres = 20;    // Min and max pixel intensity (both ways)
        image = Sobel.applySobel(image, sobelThres);
        image.saveImage(folder, fileName + "_sobel-" + sobelThres);

        // Segmentation
        int segThres = 130;      // Max pixel intensity
        int segMin = 10000;      // Min segment size
        int segMax = 100000;      // Max segment size
        image.setFilePath(folder);
        image = new SegmentizeWithThreshold(image, segThres, false, segMin, segMax).segmentize();
        image.saveImage(folder, fileName + "_sobel-" + sobelThres + "_segmentize_thres-" + segThres);
    }
}
