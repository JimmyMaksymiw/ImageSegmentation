package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * @author Jimmy Maksymiw
 */
public class Image {
    private Pixel[][] pixels;

    public Image (String filePath) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(filePath));
            pixels = newImage(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveImage(BufferedImage image/*, String fileName */) {
        // TODO: parameter imageformat ?
        // TODO: selected file path ?
        // TODO: selected filename ?
        try {
            ImageIO.write(image, "PNG", new File("resources/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pixel[][] newImage(BufferedImage image) {
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
        
        Pixel[][] img = new Pixel[height][width];

        if (hasAlphaChannel){
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                img[row][col] = new Pixel(argb);
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                img[row][col] = new Pixel(argb);
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return img;
    }

    public BufferedImage createImageFromIntArray(Pixel[][] image) {
        int height = image.length;
        int width = image[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, image[y][x].getARGB());
            }
        }
        return bufferedImage;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public static void main(String[] args) {
        Image image = new Image("resources/orange_flower.jpg");
        BufferedImage bufferedImage = image.createImageFromIntArray(image.getPixels());
        image.saveImage(bufferedImage);
    }
}
