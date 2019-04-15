package com.company.helpers;

import java.util.List;
import com.company.helpers.Str;

public class Json {

    private List<String> nodes;
    private String key = "";
    private String value = "";

    public Json object (String key, String value) {
        this.key = key;
        this.value = value;

        return this;
    }

    public Json array (String value) {
        this.value = value;

        return this;
    }

    public Json push () {
        String replacePattern;

        if (!this.key.equals("") &&
            !this.value.equals("")) {
            if (this.checkBreckat())
                replacePattern = "\":key\"::value";
            else
                replacePattern = "\":key\":\":value\"";
        } else {
            if (this.checkBreckat())
                replacePattern = ":value";
            else
                replacePattern = "\":value\"";
        }
        // FIX: null pointer except?????
        this.nodes.add(Str.replace(
                replacePattern,
                new String[][] {
                        {"key", this.key},
                        {"value", this.value}
                }
        ));

        this.key = "";
        this.value = "";

        return this;
    }

    /**
     * @return String with values closed in array.
     */
//    public String getArray () {
//        String data = Str.replace(
//                "[:nodes]",
//                new String[][] {
//                        {"nodes", this.nodes}
//                }
//        );
//        this.destroyNodes();
//
//        return data;
//    }

    /**
     * @return String with values closed in JSON object.
     */
    public String getJson () {
        String data = Str.replace(
                "{:nodes}",
                new String[][] {
                        {"nodes", String.join(",", this.nodes)}
                }
        );
        this.destroyNodes();

        return data;
    }

    private Boolean checkBreckat () { return this.value.startsWith("{") || this.value.startsWith("["); }

    /**
     * Destroy current nodes.
     */
    private void destroyNodes () {
        this.nodes = null;
    }

}
