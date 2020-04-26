package lab4.shapes.common;

import lab4.shapes.Utils;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x:" + Utils.doubleToString(x) + " y:" + Utils.doubleToString(y);
    }
}
