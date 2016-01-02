package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

/**
 * This class represent a image with its associated data.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Image {
    private Pixel[][] pixels;
    private String fileName;
    private String filePath;

    /**
     * Loads and creates a new Image-object from a image with the provided name and the selected file path.
     * example: new Image("tests/test1_segmentize", "colors", "jpg");
     * @param filePath The location of the image to be read.
     * @param fileName The name of the image.
     * @param extension the extension of the file.
     * @throws IOException
     */
    public Image(String filePath, String fileName, String extension) throws IOException {
        this.filePath = filePath;
        this.fileName = fileName;
        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new File(filePath + "/" + fileName + "." + extension));
        pixels = newImage(bufferedImage);
    }

    /**
     * Creates a new Image-object with the provided two dimensional Pixel-array.
     * @param pixels The Pixel-array.
     */
    public Image(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    /**
     * Create a new Image-object with the size from the provided width and height.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param filePath The location of the image.
     * @param fileName The name of the image.
     */
    public Image(int width, int height, String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new Pixel();
            }
        }
    }

    /**
     * Sets the location of the image to the provided value.
     * @param filePath The location of the image.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the name of the image.
     * @return The name of the image.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the location of the image.
     * @return The location of the image.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns the width of the image.
     * @return The width of the image.
     */
    public int getWidth() {
        return pixels[0].length;
    }

    /**
     * Returns the height of the image.
     * @return The height of the image.
     */
    public int getHeight() {
        return pixels.length;
    }

    /**
     * Returns the selected Pixel-object in the x and y directions provided.
     * @param x The selected x-direction.
     * @param y The selected y-direction.
     * @return The selected Pixel-object
     */
    public Pixel getPixel(int x, int y) {
        if (!(x < 0 || x >= getWidth() || y < 0 || y >= getHeight())) {
            return pixels[y][x];
        }
        return null;
    }

    /**
     * Saves the image-object as a .png image on the selected location and with the chosen name.
     * @param location The location of the image.
     * @param fileName The name of the image.
     */
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

    /**
     * Creates a new 2D Pixel-array as represents the image.
     * Loops through the entire BufferedImage and extracts the colors from it and sets the same Pixel in the array to this color.
     * @param image The loaded BufferedImage.
     * @return A 2D Pixel-array that represents the BufferedImage.
     */
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

    /**
     * Creates a new BufferedImage-object from the field value pixels.
     * @return A new BufferedImage-object.
     */
    private BufferedImage getBufferedImage() {
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

    /**
     * Returns the 2D Pixel-array.
     * @return The 2D Pixel-array.
     */
    public Pixel[][] getPixels() {
        return pixels;
    }
}
