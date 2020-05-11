package common;

public class StringUtils {
    public static boolean isStringNumber(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int stringToInteger(String number) throws Exception {
        if (!StringUtils.isStringNumber(number)) {
            throw new Exception("Illegal value");
        }
        return Integer.parseInt(number);
    }
}
