package lab4.figure;

import lab4.figure.common.Color;
import lab4.figure.common.Point;

public class Utils {
    private static boolean isNumericString(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isHexNumber(String strNum) {
        try {
            Integer.parseInt(strNum, 16);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static Point convertToPoint(String pointX, String pointY) throws Exception {
        if (!isNumericString(pointX) || !isNumericString(pointY)) {
            throw new Exception("Значение не является числом");
        }
        return new Point(Double.parseDouble(pointX), Double.parseDouble(pointY));
    }

    public static Color convertToColor(String color) throws Exception {
        if (color.length() != 7 || !isHexNumber(color.substring(1, 7))) {
            throw new Exception("Значение не является hex цветом");
        }
        return new Color(color);
    }

    public static double convertToNumber(String number) throws Exception {
        if (!isNumericString(number)) {
            throw new Exception("Значение не является числом");
        }
        return Double.parseDouble(number);
    }

}
