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

import java.util.ArrayList;
import java.util.List;

public interface JSONInterface {

    /**
     * this contain all json variables.
     * @value new list object.
     */
    List<String> nodes = new ArrayList();

    /**
     * This method concatenates all values in nodes and close it in brackets.
     * After that this delete all values from nodes.
     * @return String closed json object.
     */
    String get();

}
