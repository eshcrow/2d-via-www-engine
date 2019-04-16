/**
 * Very simple json objects encoder.
 * Created for only to Game Server services.
 *
 * @author  Simon Fox (Trzebu)
 * @url https://github.com/Trzebu/
 * @version 2.0
 * @since   2019-04-16
 */

package com.company.helpers.json;

import com.company.helpers.Str;
import java.util.ArrayList;
import java.util.List;

/**
 * @extends Brackets helper.
 * @implements JSONInterface.
 * Class to creating typical JS object.
 */
public class JSON extends Brackets implements JSONInterface {

    /**
     * @see com.company.helpers.json.JSONInterface
     */
    public List<String> nodes = new ArrayList();

    /**
     * @param key Name of the new json key.
     * @param value Value of the new json content.
     * @return this object if you want to get json string after adding new key.
     */
    public JSON push (String key, String value) {
        String replacePattern = "\":key\":\":value\"";

        if (this.check(value))
            replacePattern = "\":key\"::value";

        this.nodes.add(Str.replace(
                replacePattern,
                new String[][] {
                        {"key", key},
                        {"value", value}
                }
        ));

        return this;
    }

    /**
     * @see com.company.helpers.json.JSONInterface
     */
    public String get () {
        String result = Str.replace(
                "{:nodes}",
                new String[][] {
                        {"nodes", String.join(",", this.nodes)}
                }
        );
        this.nodes = null;
        return result;
    }

}
