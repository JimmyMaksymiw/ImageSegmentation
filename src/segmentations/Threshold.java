package segmentations;

import java.util.Arrays;
import java.util.LinkedList;
import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Threshold {
    private final int THRESHOLD = 115;
    private final int VISISTED = 0x00FF0000;
    private final int NOT_VISITED = 0x00000000;
    private LinkedList<Candidate> candidates;
    private int[][] visited;
    private Image image;
    private Image destImage;
    private int r;
    private int g;
    private int b;

    private class Candidate {
        int x;
        int y;

        public Candidate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Image segmentize(Image image) {
        this.image = image;
        int width = image.getWidth();
        int height = image.getHeight();

        // Create containers
        visited = new int[height][width];
        candidates = new LinkedList<>();
        destImage = new Image(width, height);

        // Fill visited array with NOT_VISITED
        for (int[] row : visited) {
            Arrays.fill(row, NOT_VISITED);
        }

        // Loop through all pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (visited[y][x] == NOT_VISITED) {
                    // Extract pixel's colors
                    r = image.getPixel(x, y).getR();
                    g = image.getPixel(x, y).getG();
                    b = image.getPixel(x, y).getB();

                    // Add pixel to candidates
                    candidates.add(new Candidate(x, y));

                    // Find neighboring pixels
                    flood();
                }
            }
        }
        return destImage;
    }

    public void flood() {
        // Loop while there's still candidates
        while (candidates.size() > 0) {

            // Remove the first candidate
            Candidate c = candidates.removeFirst();
            int x = c.x;
            int y = c.y;

            // Check if candidate is inside image
            if ((x >= 0) && (x < image.getWidth()) && (y >= 0) && (y < image.getHeight())) {

                // Check if current candidate isn't visited yet
                if (visited[y][x] == NOT_VISITED) {

                    // Get colors from candidate
                    int r = image.getPixel(x, y).getR();
                    int g = image.getPixel(x, y).getG();
                    int b = image.getPixel(x, y).getB();

                    // Calculate differences in rgb values
                    int rDiff = Math.abs(r - this.r);
                    int gDiff = Math.abs(g - this.g);
                    int bDiff = Math.abs(b - this.b);

                    // Check if colors are under threshold
                    if (rDiff <= THRESHOLD && gDiff <= THRESHOLD && bDiff <= THRESHOLD) {

                        // Add candidate to the destination image
                        destImage.getPixel(x, y).setARGB(255, this.r, this.g, this.b);

                        // Mark candidate as visited
                        visited[y][x] = VISISTED;

                        // Add neighboring pixels as candidates
                        candidates.add(new Candidate(x - 1, y - 1));
                        candidates.add(new Candidate(x, y - 1));
                        candidates.add(new Candidate(x + 1, y - 1));
                        candidates.add(new Candidate(x - 1, y));
                        candidates.add(new Candidate(x + 1, y));
                        candidates.add(new Candidate(x - 1, y + 1));
                        candidates.add(new Candidate(x, y + 1));
                        candidates.add(new Candidate(x + 1, y + 1));
                    }

                }
            }
        }
    }
}
