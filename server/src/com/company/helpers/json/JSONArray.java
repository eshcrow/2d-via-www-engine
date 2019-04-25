/**
 * Very simple json objects encoder.
 * Created for only to Game Server services.
 *
 * 2.1 int support.
 *
 * @author  Simon Fox (Trzebu)
 * @url https://github.com/Trzebu/
 * @version 2.1
 * @since   2019-04-16
 */

package com.company.helpers.json;

import java.util.List;
import java.util.ArrayList;
import com.company.helpers.Str;

/**
 * @extends Brackets helper.
 * @implements JSONInterface.
 * Class to creating typical JS arrays.
 */
public class JSONArray extends Brackets implements JSONInterface {

    /**
     * @see com.company.helpers.json.JSONInterface
     */
    public List<String> nodes = new ArrayList();

    /**
     * @param value String value of the new json content.
     * @return this object if you want to get js array after adding new key.
     */
    public JSONArray add (String value) {
        String replacePattern = "\":value\"";

        if (this.check(value))
            replacePattern = ":value";

        this.nodes.add(Str.replace(
                replacePattern,
                new String[][] {
                        {"value", value}
                }
        ));

        return this;
    }

    /**
     * @param value Integer value of the new json content.
     * @return this object if you want to get js array after adding new key.
     */
    public JSONArray add (int value) {
        this.nodes.add(Str.replace(
                ":value",
                new String[][] {
                        {"value", "" + value}
                }
        ));

        return this;
    }

    /**
     * @see com.company.helpers.json.JSONInterface
     */
    public String get () {
        String result = Str.replace(
                "[:nodes]",
                new String[][] {
                        {"nodes", String.join(",", this.nodes)}
                }
        );
        this.nodes = null;
        return result;
    }

}
