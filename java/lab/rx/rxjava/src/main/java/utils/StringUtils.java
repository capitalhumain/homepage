package utils;

public class StringUtils {
    public static String asciiToHex(String asciiStr) {
        char[] ascii = asciiStr.toCharArray();
        StringBuilder result = new StringBuilder();
        for(char ch : ascii) {
            result.append(Integer.toHexString((int)ch));
        }
        return result.toString();
    }
}
