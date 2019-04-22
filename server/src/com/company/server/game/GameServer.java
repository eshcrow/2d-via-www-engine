package com.company.server.game;

import com.company.helpers.Log;
import com.company.server.http.HttpServer;
import com.company.player.Players;

public class GameServer implements Runnable {

    public Boolean started = false;
    public Players players;
    private HttpServer http;

    public GameServer () {

    }

    public void run () {
        this.started = true;
        Log.success("Game Server started.");
    }

    public void setHttp (HttpServer http) {
        this.http = http;
    }

    public void stop () {
        this.started = false;
        this.http.stop();
        Log.warning("Game Server stopped.");
    }

}
