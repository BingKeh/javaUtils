package com.morhop.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class NumToZh {

    // final variables for looking up
    private final static String CHINESE_NUMERIC = "零壹贰叁肆伍陆柒捌玖";
    private final static String[] DIGITS = { "元拾佰仟万", "万拾佰仟", "亿拾佰仟", "万拾佰仟", "兆"};
    private final static String NEGATIVE_DIGITS = "分角";
    private final static String SPECIAL_UNITS = "元万亿";
    final static String UNIT = "角分整";

    
    /**
     * <p>Trim redundant '零' in given string</p>
     *
     * @param input string to be trim
     * @param fromIndex the index to start the trim
     * @return the string has been trim, only trim the first block in a single flow
     */
    private static String trimZero(@NotNull String input, int fromIndex) {
        int startIndex  = input.indexOf('零', fromIndex);

        // if there's no '零' in the string return the final output
        if (startIndex < 0) {
            return input.replaceAll(" ", "");
        }

        int stopIndex = startIndex;
        while (input.charAt(stopIndex) == '零') {
            stopIndex++;
        }
        char[] str = input.toCharArray();
        for (int i = startIndex; i < stopIndex; i++) {
            str[i] = ' ';
        }
        if (SPECIAL_UNITS.contains(input.charAt(stopIndex) + "")) {
            input = new String(str);
        } else {
            str[stopIndex - 1] = '零';
            input = new String(str);
        }

        return trimZero(input, stopIndex);
    }

    /**
     * <p>get the converted string of a single num at specified index</p>
     *
     * @param index the index of the single num
     * @param num the num to be converted
     * @return the converted string of the num, plus the unit
     */
    private static String getChinese(int index, byte num) {
        int outerIndex = index / 4;
        int innerIndex = index % 4;
        char c1, c2;
        c1 = CHINESE_NUMERIC.charAt(num);
        if (!(index < 0)) {
            c2 = DIGITS[outerIndex].charAt(innerIndex);
            if (num == 0) {
                if (innerIndex == 0) {
                    c1 = ' ';
                } else {
                    c2 = ' ';
                }
            }
        } else {
            c2 = NEGATIVE_DIGITS.charAt(-innerIndex);
        }
        return c1 + "" + c2 ;
    }

    /**
     *
     * @param money money to be converted
     * @return the converted string
     */
    public static String getCh(double money) {
        String output = "";
        StringBuilder builder = new StringBuilder(output);
        long integer = (long) money;
        List<Byte> numList = new ArrayList<>();
        byte b;
        int index = 0;
        while (integer / 10 != 0) {
            b = (byte) (integer % 10);
            numList.add(b);
            builder.insert(0, getChinese(index, b));
            integer = integer / 10;
            index++;
        }
        b = (byte) (integer % 10);
        numList.add(b);
        builder.insert(0, getChinese(index, b));

        short decimal = (short) ((money - integer) * 10);
        output = builder.toString().replace(" ", "");
        output = trimZero(output, 0);
        return output;
    }
}
