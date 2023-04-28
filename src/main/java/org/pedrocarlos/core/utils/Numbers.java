package org.pedrocarlos.core.utils;

import java.util.regex.Pattern;

public class Numbers {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        return pattern.matcher(number).matches();
    }

    public static boolean isZero(String number) {
        if(number.length() == 0) return false;

        for(char digit : number.toCharArray()) {
            if (digit != '0') return false;
        }

        return true;
    }

    public static boolean isNegative(String strNum) {
        return strNum.charAt(0) == '-';
    }
}