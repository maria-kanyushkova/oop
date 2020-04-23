package lab4.figure;

public class Color {
    public int r;
    public int g;
    public int b;

    Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String toStringRGB() {
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }

    public String toStringHex() {
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
