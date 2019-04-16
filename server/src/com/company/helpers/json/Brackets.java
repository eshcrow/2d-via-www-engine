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

public abstract class Brackets {

    /**
     * @param value Json object or JS array.
     * @return Boolean true if value starts with "{" or "[".
     */
    protected Boolean check (String value) {
        return value.startsWith("{") || value.startsWith("[");
    }

}
