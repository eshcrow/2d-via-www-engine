package com.company;

import com.company.server.HttpServer;
import com.company.helpers.Prop;

public class GameServer {

    protected static final String PROPS_FILE = "../server.properties";

    public static void main(String[] args) {
        Prop properties = new Prop(PROPS_FILE);

        HttpServer httpServer = new HttpServer(properties.getAsInt("PORT"));
        new Thread(httpServer).start();
    }

}
