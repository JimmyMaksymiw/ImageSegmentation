package segmentations;

import java.util.LinkedList;

import utils.Image;

/**
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class SegmentizeWithThreshold {
    private final int THRESHOLD;
    private LinkedList<Candidate> candidates;
    private Image image;
    private Image destImage;
    private int r;
    private int g;
    private int b;
    private boolean saveSegments;
    private int segCounter;
    private int segMin;
    private int segMax;

    private class Candidate {
        int x;
        int y;

        public Candidate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public SegmentizeWithThreshold(Image image, int threshold, boolean saveSegments, int segMin, int segMax) {
        this.THRESHOLD = threshold;
        this.image = image;
        this.saveSegments = saveSegments;
        this.segMin = segMin;
        this.segMax = segMax;
        segCounter = 0;

        // Create containers
        candidates = new LinkedList<>();
        destImage = new Image(image.getWidth(), image.getHeight(), image.getFilePath(), image.getFileName());

        // Fill visited array with NOT_VISITED
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                image.getPixel(x, y).setVisited(false);
            }
        }
    }

    public Image segmentize() {
        // Loop through all pixels
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                // If pixel isn't visited yet
                if (!image.getPixel(x, y).isVisited()) {

                    // Extract pixel's colors
                    r = image.getPixel(x, y).getR();
                    g = image.getPixel(x, y).getG();
                    b = image.getPixel(x, y).getB();

                    // Add pixel to candidates
                    candidates.add(new Candidate(x, y));

                    // Find neighboring pixels
                    findNeighbors();
                }
            }
        }
        return destImage;
    }

    private void findNeighbors() {
        // Segmentation image
        int innerCounter = 0;
        Image seg = null;
        if (saveSegments) {
            seg = new Image(image.getWidth(), image.getHeight(), image.getFilePath(), image.getFileName());
        }

        // Loop while there's still candidates
        while (candidates.size() > 0) {

            // Remove the first candidate
            Candidate c = candidates.removeFirst();
            int x = c.x;
            int y = c.y;

            // Check if candidate is inside image
            if ((x >= 0) && (x < image.getWidth()) && (y >= 0) && (y < image.getHeight())) {

                // Check if current candidate isn't visited yet
                if (!image.getPixel(x, y).isVisited()) {

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
                        innerCounter++;

                        // Add candidate to the destination image
                        destImage.getPixel(x, y).setARGB(255, this.r, this.g, this.b);

                        // Save segmentation image
                        if (saveSegments) {
                            seg.getPixel(x, y).setARGB(255, this.r, this.g, this.b);
                        }

                        // Mark candidate as visited
                        image.getPixel(x, y).setVisited(true);

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

        // Save segment image if save is on and if segment fulfills size requirement
        if (saveSegments && innerCounter >= segMin && innerCounter <= segMax) {
            // Create segment folder
            seg.saveImage(image.getFilePath() + "/" + seg.getFileName(), seg.getFileName() + "_seg-" + segCounter++ + "_size-" + segMin + "-to-" +
                    segMax);
        }
    }
}
