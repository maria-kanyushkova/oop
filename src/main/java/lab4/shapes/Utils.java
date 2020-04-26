package lab4.shapes;

import lab4.shapes.common.Point;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

public class Utils {
    private static boolean isHexNumber(String strNum) {
        try {
            Integer.parseInt(strNum, 16);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Point convertToPoint(String pointX, String pointY) throws Exception {
        if (!StringUtils.isNumeric(pointX) || !StringUtils.isNumeric(pointY)) {
            throw new Exception("Значение не является числом");
        }
        return new Point(Double.parseDouble(pointX), Double.parseDouble(pointY));
    }

    public static Color convertToColor(String color) throws Exception {
        if (color.length() != 7 || !isHexNumber(color.substring(1, 7))) {
            throw new Exception("Значение не является hex цветом");
        }
        int r = Integer.valueOf(color.substring(1, 3), 16);
        int g = Integer.valueOf(color.substring(3, 5), 16);
        int b = Integer.valueOf(color.substring(5, 7), 16);
        return new Color(r, g, b);
    }

    public static double convertToNumber(String number) throws Exception {
        if (!StringUtils.isNumeric(number)) {
            throw new Exception("Значение не является числом");
        }
        return Double.parseDouble(number);
    }

    public static String colorToString(Color color) {
        String hex = Integer.toHexString(color.getRGB()).substring(2);
        if (hex.length() < 6) {
            hex = "0" + hex;
        }
        return "#" + hex;
    }

    public static String doubleToString(double value) {
        return String.format("%.2f", value);
    }
}
