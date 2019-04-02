package com.company;

import com.company.server.HttpServer;

public class Main {

    protected static int port = 666;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(port);
        new Thread(httpServer).start();

    }
}
