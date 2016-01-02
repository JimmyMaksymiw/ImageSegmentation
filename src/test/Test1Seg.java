package test;

import java.io.IOException;

import segmentations.SegmentizeWithThreshold;
import utils.Image;

/**
 * Created by Kalle Bornemark on 2016-01-02.
 */
public class Test1Seg {
    public static void main(String[] args) throws IOException {
        String folder = "tests/segmentize";
        String fileName = "colors";
        Image image = new Image("tests/segmentize", fileName, "jpg");

        int segThres = 120;
        int segMin = 1000;
        int segMax = 6000;
        image = new SegmentizeWithThreshold(image, segThres, true, segMin, segMax).segmentize();
        image.saveImage(folder, fileName + "_segmentize_thres-" + segThres);
    }
}
