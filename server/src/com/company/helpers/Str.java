package com.company.helpers;

public class Str {

    public static String replace (String text, String[][] patternsArrays) {

        for (String[] i : patternsArrays) {
            text = text.replace(":" + i[0], i[1]);
        }

        return text;
    }

}
