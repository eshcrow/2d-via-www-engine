package com.company.helpers;

import java.util.Date;

public class Log {

    public static Prop properties = new Prop("../server.properties");
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

    public static void info(String text) {
        if (properties.get("LOG_INFO").equals("true"))
            say(text, "", "", false);
    }

    public static void success (String text) {
        if (properties.get("LOG_SUCCESS").equals("true"))
            say(text, "GREEN", "", false);
    }

    public static void warning (String text) {
        if (properties.get("LOG_WARNINGS").equals("true"))
            say(text, "YELLOW", "", true);
    }

    public static void error (String text) {
        if (properties.get("LOG_ERRORS").equals("true"))
            say(text, "RED", "", true);
    }

    public static void say (
            String text,
            String fontColor,
            String backgroundColor,
            Boolean fontBold
    ) {
        String[] color = getColor(fontColor) == null ? getColor("NONE") : getColor(fontColor);
        String[] bgColor = getColor(backgroundColor) == null ? getColor("NONE") : getColor(backgroundColor);
        String bold = fontBold ? "1m" : "0m";
        String currentTime = new Date(System.currentTimeMillis()).toString();

        System.out.println(
                Str.replace(
                        "\033[:font_color;:bg_color;:font_weight[:time] - :text",
                        new String[][] {
                                {"font_color", color[0]},
                                {"bg_color", bgColor[1]},
                                {"font_weight", bold},
                                {"time", currentTime},
                                {"text", text}
                        }
                )
        );
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