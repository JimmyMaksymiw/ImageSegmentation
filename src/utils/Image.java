package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Image {
    private Pixel[][] pixels;
    private String fileName;
    private String filePath;

    public Image(String filePath, String fileName, String extension) throws IOException {
        this.filePath = filePath;
        this.fileName = fileName;
        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new File(filePath + "/" + fileName + "." + extension));
        pixels = newImage(bufferedImage);
    }
    public Image(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public Image(int width, int height, String filePath, String filename) {
        this.filePath = filePath;
        this.fileName = filename;
        pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new Pixel();
            }
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getWidth() {
        return pixels[0].length;
    }

    public int getHeight() {
        return pixels.length;
    }

    public Pixel getPixel(int x, int y) {
        if (!(x < 0 || x >= getWidth() || y < 0 || y >= getHeight())) {
            return pixels[y][x];
        }
        return null;
    }

    public void saveImage(String location, String fileName) {
        try {
            File file = new File(location);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            BufferedImage bufferedImage = getBufferedImage();
            ImageIO.write(bufferedImage, "png", new File(location + "/" + fileName + ".png"));
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

    public BufferedImage getBufferedImage() {
        int height = pixels.length;
        int width = pixels[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, pixels[y][x].getARGB());
            }
        }
        return bufferedImage;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }
}
