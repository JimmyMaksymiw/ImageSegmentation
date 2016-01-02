package test;

import java.io.IOException;

import segmentations.SegmentizeWithThreshold;
import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestThreshold {
    public static void main(String[] args) throws IOException {

        // Choose file.
//        String fileName = "orange_flower";
        String fileName = "colors";
//        String fileName = "castle";

        // Load Image.
        Image image = new Image("resources/" + fileName + ".png", fileName);
//        Image image = new Image("resources/" + fileName + ".jpg", fileName);

        int segThres = 120;
        int segMin = 1000;
        int segMax = 6000;
        image = new SegmentizeWithThreshold(image, segThres, true, segMin, segMax).segmentize();
        image.saveImage("testResults/" + fileName + "4_threshold_thres-" + segThres);
    }
}
