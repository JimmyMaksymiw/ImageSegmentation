package test;

import java.io.IOException;

import segmentations.SegmentizeWithThreshold;
import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_1_3_Seg {
    public static void main(String[] args) throws IOException {
        String fileName = "colors";
        Image image = new Image("tests/test1_segmentize", fileName, "jpg");

        int segThres = 100;
        int segMin = 1000;
        int segMax = 6000;
        image.setFilePath("tests/test1_segmentize/test1.3");
        image = new SegmentizeWithThreshold(image, segThres, true, segMin, segMax).segmentize();
        image.saveImage("tests/test1_segmentize/test1.3", fileName + "_segmentize_thres-" + segThres);
    }
}
