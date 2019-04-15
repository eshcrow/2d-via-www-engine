/**
 * The 2d via www Game Server.
 * Process all Client(Player) requests.
 * Supports everything what related with game world.
 *
 * @author  Simon Fox (Trzebu)
 * @url https://github.com/Trzebu/
 * @version 1.0
 * @since   2019-04-01
 */

package com.company;

import com.company.server.http.HttpServer;
import com.company.helpers.Prop;

public class Server {

    protected static final String PROPS_FILE = "../server.properties";

    public static void main(String[] args) {
        Prop properties = new Prop(PROPS_FILE);

        HttpServer httpServer = new HttpServer(properties.getAsInt("PORT"));
        new Thread(httpServer).start();
    }

}
