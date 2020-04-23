package lab4.figure.common;

public class Color {
    public int r;
    public int g;
    public int b;

    Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(String hex) {
        this.r = Integer.valueOf(hex.substring(1, 3), 16);
        this.g = Integer.valueOf(hex.substring(3, 5), 16);
        this.b = Integer.valueOf(hex.substring(5, 7), 16);
    }

    public String toStringRGB() {
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }

    public String toStringHex() {
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
