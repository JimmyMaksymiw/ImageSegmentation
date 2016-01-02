package test;

import java.io.IOException;

import operations.GaussianBlur;
import operations.Grayscale;
import operations.Sobel;
import segmentations.SegmentizeWithThreshold;
import utils.Image;

/**
 * Created by Kalle Bornemark on 2016-01-02.
 */
public class Test2Sobel {
    public static void main(String[] args) throws IOException {
        String folder = "tests/sobel";
        String fileName = "castle";
        Image image = new Image(folder, fileName, "jpg");

        // Grayscale
        image = Grayscale.applyGrayscale(image);
        image.saveImage(folder, fileName + "1_grayscale");

        // Gaussian blur
        image = GaussianBlur.applyGaussianBlur(image);
        image.saveImage(folder, fileName + "2_gaussian_blur");

        // Sobel
        int sobelThres = 50;    // Min and max pixel intensity (both ways)
        image = Sobel.applySobel(image, sobelThres);
        image.saveImage(folder, fileName + "3_sobel-" + sobelThres);

        // Segmentation
        int segThres = 130;      // Max pixel intensity
        int segMin = 10000;      // Min segment size
        int segMax = 100000;      // Max segment size
        image.setFilePath(folder);
        image = new SegmentizeWithThreshold(image, segThres, false, segMin, segMax).segmentize();
        image.saveImage(folder, fileName + "4_segmentize_thres-" + segThres);
    }
}
