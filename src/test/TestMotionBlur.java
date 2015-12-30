package test;

import operations.MotionBlur;
import utils.Image;

import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class TestMotionBlur {
    public static void main(String[] args) throws IOException {
        Image image = new Image("resources/castle.jpg");
        image = MotionBlur.applyMotionBlur(image);
        image.saveImage("testResults/castle_motion_blur.jpg");
    }
}
