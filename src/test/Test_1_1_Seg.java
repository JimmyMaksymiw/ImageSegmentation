package test;

import segmentations.SegmentizeWithThreshold;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_1_1_Seg {
    public static void main(String[] args) throws IOException {
        String fileName = "colors";
        Image image = new Image("tests/test1_segmentize", fileName, "jpg");

        int segThres = 70;
        int segMin = 300;
        int segMax = 10000;
        image.setFilePath("tests/test1_segmentize/test1.1");
        image = new SegmentizeWithThreshold(image, segThres, true, segMin, segMax).segmentize();
        image.saveImage("tests/test1_segmentize/test1.1", fileName + "_segmentize_thres-" + segThres);
    }
}
