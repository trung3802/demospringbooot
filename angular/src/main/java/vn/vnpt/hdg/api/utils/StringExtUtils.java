package vn.vnpt.hdg.api.utils;

import org.springframework.util.StringUtils;


public class StringExtUtils {
    public static String removeRedundantSpace(String text) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }

        return text.trim().replaceAll("\\s+", " ");
    }

    public static String addLeadingToNumber(String padWith, int width, int number) {
        String formatted = String.format("%" + padWith + width + "d", number);
        System.out.println("formatted = " + formatted);
        return formatted;
    }

    public static String getSoChayHoSo(int number) {
        return addLeadingToNumber("0", 4, number);
    }

    public static String convertSnakeToCamel(String str) {
        StringBuilder builder
                = new StringBuilder(str);

        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '_') {
                builder.deleteCharAt(i);
                builder.replace(i, i + 1, String.valueOf(Character.toUpperCase(builder.charAt(i))));
            }
        }

        return builder.toString();
    }
    
    public static String capitalize(String text) {
    	return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
    

    public static void main(String[] args) {
        System.out.println(convertSnakeToCamel("abc_def_ghi"));
    }
}
