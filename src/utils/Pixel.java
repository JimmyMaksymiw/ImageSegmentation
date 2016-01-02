package utils;

/**
 * This class represents a single pixel with the ARGB color space.
 * @author Jimmy Maksymiw & Kalle Bornemark
 */
public class Pixel {
    private int a;
    private int r;
    private int g;
    private int b;
    private boolean visited;

    /**
     * Creates a new Pixel and sets the field values r, g, b and a with the provided values.
     * @param a The value of alpha.
     * @param r The value of red.
     * @param g The value of green.
     * @param b The value of blue.
     */
    public Pixel(int a, int r, int g, int b) {
        this(false);
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Pixel(int argb) {
        this((argb >> 24) & 0xFF, (argb >> 16) & 0xFF, (argb >> 8) & 0xFF, argb & 0xFF);
    }

    public Pixel() {
        this(255, 255, 255, 255);
    }

    public Pixel(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returs the value of alpha.
     * @return The value of alpha.
     */
    public int getA() {
        return a;
    }

    /**
     * Returs the value of red.
     * @return The value of red.
     */
    public int getR() {
        return r;
    }

    /**
     * Returs the value of green.
     * @return The value of green.
     */
    public int getG() {
        return g;
    }

    /**
     * Returs the value of red.
     * @return The value of red.
     */
    public int getB() {
        return b;
    }

    /**
     * returns the ARGB color space as a int value.
     * @return the ARGB color space as a int value.
     */
    public int getARGB() {
        int argb = 0;

        argb += (a & 0xFF) << 24;
        argb += (r & 0xFF) << 16;
        argb += (g & 0xFF) << 8;
        argb += b & 0xFF;

        return argb;
    }

    /**
     * Extracts and sets field values r, g, b and a as separate colors from the provided ARGB-value.
     * @param argb The provided ARGB-value.
     */
    public void setARGB(int argb) {
        this.a = (argb >> 24) & 0xFF;
        this.r = (argb >> 16) & 0xFF;
        this.g = (argb >> 8) & 0xFF;
        this.b = argb & 0xFF;
    }

    /**
     * Sets field values r, g, b and a from the provided values.
     * @param a The value of alpha.
     * @param r The value of red.
     * @param g The value of green.
     * @param b The value of blue.
     */
    public void setARGB(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Returns true if this pixel is visited.
     * @return true if visited otherwise false.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the boolean value visited to the provided value.
     * @param visited true if visited otherwise false.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Pixel{" +
                ", a=" + a +
                ", r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }
}
