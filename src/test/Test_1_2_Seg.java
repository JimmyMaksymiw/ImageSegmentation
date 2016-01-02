package test;

import segmentations.SegmentizeWithThreshold;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Test_1_2_Seg {
    public static void main(String[] args) throws IOException {
        String fileName = "colors";
        Image image = new Image("tests/test1_segmentize", fileName, "jpg");

        int segThres = 120;
        int segMin = 2000;
        int segMax = 8000;
        image.setFilePath("tests/test1_segmentize/test1.2");
        image = new SegmentizeWithThreshold(image, segThres, true, segMin, segMax).segmentize();
        image.saveImage("tests/test1_segmentize/test1.2", fileName + "_segmentize_thres-" + segThres);
    }
}
