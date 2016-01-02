package test;

import operations.MotionBlur;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestMotionBlur {
    public static void main(String[] args) throws IOException {
        String fileName = "castle";
        Image image = new Image("resources/castle.jpg", fileName);
        image = MotionBlur.applyMotionBlur(image);
        image.saveImage("testResults/castle_motion_blur");
    }
}
