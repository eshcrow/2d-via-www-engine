package com.company.helpers;

public class Log {

    public static String[][] fontColors = {
            {"BLACK", "40","30"},
            {"RED", "41", "31"},
            {"GREEN", "42", "32"},
            {"YELLOW", "43", "33"},
            {"BLUE", "44", "34"},
            {"PURPLE", "45", "35"},
            {"CYAN", "46", "", "36"},
            {"WHITE", "47", "37"},
            {"NONE", "0", "0"}
    };

    public static void sey (String text, String fontColor, String backgroundColor) {
        String[] color = getColor(fontColor) == null ? getColor("NONE") : getColor(fontColor);
        String[] bgColor = getColor(backgroundColor) == null ? getColor("NONE") : getColor(backgroundColor);

        System.out.println("\033[" + color[0] + ";" + bgColor[1] + ";1m" + text);
    }

    private static String[] getColor (String color) {
        for (String[] i : fontColors) {
            if (i[0].equals(color)) {
                String[] results = {i[2], i[1]};
                return results;
            }
        }

        return null;
    }

}
